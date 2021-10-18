package com.zyt.service.impl.orderService.impl;

import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.zyt.constant.OrderServerConstant;
import com.zyt.entity.*;
import com.zyt.entity.orderEnum.OrderStatusEnum;
import com.zyt.entity.to.mq.OrderInfoTo;
import com.zyt.entity.vo.*;
import com.zyt.exception.NoStockException;
import com.zyt.mapper.*;
import com.zyt.service.orderService.OrderManagerService;
import com.zyt.service.paymentService.PayMentInfoService;
import com.zyt.service.stockService.StockManagerService;
import com.zyt.service.storeProfitService.StoreProfitInfoManagerService;
import com.zyt.utils.DateUtils;
import com.zyt.utils.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;
import org.omg.CORBA.TIMEOUT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.orderService.impl
 * @ClassName: OrderManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单管理服务层实现模块
 * @Date: 16:14 2021/2/25
 * @Version: 1.0
 */
@Service(value = "orderManagerService")
public class OrderManagerServiceImpl implements OrderManagerService {

    private Logger logger = LoggerFactory.getLogger(OrderManagerServiceImpl.class);

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private VipMemberMapper vipMemberMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private StockManagerService stockManagerService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private PayMentInfoService payMentInfoService;

    @Autowired
    private StoreProfitInfoManagerService profitInfoManagerService;


    /**
     * @Method: setAntiDuplicationTokenOfCurrentUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:生成防重复提交服务处
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/2/25 19:45
     * @Param: * @param userName
     * @Return: java.lang.String
     */
    @Override
    @Transactional
    public String setAntiDuplicationTokenOfCurrentUser(String userName) {
        //获得当前用户的id
        int user_Id = this.vipMemberMapper.getUserIdByUserName(userName);
        //生成令牌
        String order_token = UUID.randomUUID().toString().replaceAll("-","");
        //将令牌写进redis缓存
        this.redisUtil.set(OrderServerConstant.USER_ORDER_TOKEN_PREFIX+user_Id,order_token,30, TimeUnit.MINUTES);
        return order_token;

    }

    /**
     * @Method: getSubmitOrderInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:个人订单详情管理模块
     * @Return: int
     * @Exception:
     * @Date: 2021/2/26 10:46
     * @Param: * @param loginUserName
     * @param leaveMessage
     * @param delivery
     * @param packingCharge
     * @param delayAddress
     * @param acceptUser
     * @param contactNum
     * @param shouldPayPrice
     * @param paymentMethods
     * @param isUseCouple
     * @param integralValue
     * @param isUseIntegral
     * @param coupleId
     * @Return: int
     */
    @Override
    @Transactional
    public SubmitOrderResponseTo getSubmitOrderInfos(String loginUserName,String antiDuplicationToken,String leaveMessage, String delivery, String packingCharge, String delayAddress, String acceptUser, String contactNum, String shouldPayPrice, String paymentMethods, Boolean isUseCouple, String integralValue, Boolean isUseIntegral, String coupleId) {
        SubmitOrderResponseTo responseTo  = new SubmitOrderResponseTo();
        //获得用户id
        int user_id = this.vipMemberMapper.getUserIdByUserName(loginUserName);
        //验证令牌[令牌的对比必须保证原子性]
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";
        String redisToken = this.redisUtil.get(OrderServerConstant.USER_ORDER_TOKEN_PREFIX+user_id);
//        if (redisToken != null && antiDuplicationToken.equals(redisToken)){
//            //令牌验证通过
//            this.redisUtil.del(OrderServerConstant.USER_ORDER_TOKEN_PREFIX+user_id);
//        }else{
//            //不通过
//            return  null;
//        }
        //原子验证令牌和删除令牌
        Long result = (Long) this.redisTemplate.execute(new DefaultRedisScript<Long>(script,Long.class),Arrays.asList(OrderServerConstant.USER_ORDER_TOKEN_PREFIX+user_id),antiDuplicationToken);
        if(result == 0){
            //验证失败
            logger.info("令牌验证失败");
            responseTo.setCode(1);
            return  responseTo;
        }else{
            logger.info("令牌验证成功");
           //令牌验证成功 创建订单
            OrderCreateTo order = orderAndOrderItemCreate(loginUserName,antiDuplicationToken,leaveMessage,delivery,packingCharge,delayAddress,acceptUser,contactNum,shouldPayPrice,paymentMethods,isUseCouple,integralValue,isUseIntegral,coupleId);
            logger.info("第一步");
            if(Math.abs(order.getOrder().getPay_amount()-Double.parseDouble(shouldPayPrice)*1.00) < 0.01){
                 logger.info("第二步");
                 //金额对比成功  保存订单信息和订单项信息
                 saveOrder(order);
                 //修改个人用户的成长值和积分
                 this.vipMemberMapper.updateGrouthAndInternalOfCuurentUser(order.getOrder().getMember_id(),order.getOrder().getGrowth(),order.getOrder().getIntegration());
                //锁库存只要有异常就会滚  根据订单进行锁库存
                //获得订单号
                String orderSn = order.getOrder().getOrder_sn();
                //封装想要的订单信息
                List<OrderItemLockVo> locks = order.getOrderItemsList().stream().map(item->{
                    OrderItemLockVo orderItemLockVo = new OrderItemLockVo();
                    orderItemLockVo.setProdId(item.getProd_id());
                    orderItemLockVo.setLockCount(item.getProd_quantity());
                    return orderItemLockVo;
                }).collect(Collectors.toList());
                //TODO 锁定库存操作
                Boolean flag = this.stockManagerService.orderLockStock(orderSn,locks);
                logger.info("完成锁库存操作");
                if(flag == true){
                    responseTo.setCode(0);
                    responseTo.setOrder(order.getOrder());
                    //TODO 订单下单成功则发送消息到消息队列
                    this.rabbitTemplate.convertAndSend("order-event-exchange","order.create.order",order.getOrder());
                    return responseTo;
                }else{
                    throw  new NoStockException();
                }
            }else{
                responseTo.setCode(2);
                return responseTo;
            }
        }

    }

    /**
     * @Method: saveOrder
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:保存订单和订单项数据到数据库
     * @Return: void
     * @Exception:
     * @Date: 2021/2/27 15:58
     * @Param: * @param order
     * @Return: void
     */
    private void saveOrder(OrderCreateTo order) {
        //获得当前订单
        Order orderInfo  = order.getOrder();
        logger.info("获得当前所有的订单信息:"+orderInfo.toString());
        this.orderMapper.saveOrderInfo(orderInfo);
        //获得当前所有订单项
        List<OrderItems> orderItems = order.getOrderItemsList();
        logger.info("获得当前所有的订单项信息:"+orderItems.toString());
        this.orderItemMapper.saveOrderItemInfos(orderItems);
    }

    /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:构建当前用户名下订单
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 12:53
      * @Param: * @param null
      * @Return:
      */
    private OrderCreateTo orderAndOrderItemCreate(String loginUserName,String antiDuplicationToken, String leaveMessage, String delivery, String packingCharge, String delayAddress, String acceptUser, String contactNum, String shouldPayPrice, String paymentMethods, Boolean isUseCouple, String integralValue, Boolean isUseIntegral, String coupleId){
        OrderCreateTo orderCreateTo = new OrderCreateTo();
        Order order = new Order();
        //生成用户id
        int user_Id  = this.vipMemberMapper.getUserIdByUserName(loginUserName);
        order.setMember_id(user_Id);
        //生成用户名
        order.setMember_username(loginUserName);
        //生成订单号
        String order_Sn = String.valueOf(LocalDateTime.now().getSecond()+IdWorker.getId());
        order.setOrder_sn(order_Sn);
        //获取收货地址信息
         String province = delayAddress.substring(0,3);
         String city = delayAddress.substring(3,6);
         String region = delayAddress.substring(6,9);
         //生成省 市 区
         order.setReceiver_province(province);
         order.setReceiver_city(city);
         order.setReceiver_region(region);
         //生成详细地址
         order.setReceiver_detail_address(delayAddress);
         //生成收货人姓名 + 联系方式
        String userName = this.vipMemberMapper.getUserNameByUserId(user_Id);
        order.setReceiver_name(userName);
        order.setReceiver_phone(contactNum);
        //创建订单项目
        int count = this.cartItemMapper.getCartItemInfoCountsOfIsCheck(true,user_Id);
        Double  packageFree =  Double.valueOf(packingCharge)  * count;
        //创建运费
        order.setFreight_amount(Double.valueOf(delivery)*1.00);
        //创建用户留言信息
        order.setNote(leaveMessage);
        List<CartItem> cartItems = this.cartItemMapper.getIsCheckedProdinfoOfCurrUser(user_Id,true);
        //创建订单项
        List<OrderItems> orderItemsList = null;
        if (cartItems != null && cartItems.size() > 0){
            orderItemsList = cartItems.stream().map(cartItem -> {
                OrderItems orderItems = new OrderItems();
                //订单项关联订单信息
                orderItems.setOrder_sn(order_Sn);
                //生成商品编号
                orderItems.setProd_id(cartItem.getProdId());
                //生成商品名称
                orderItems.setProd_name(cartItem.getProdName());
                //生成商品图片信息
                orderItems.setProd_pic(cartItem.getProdImg());
                //生成商品品牌
                int brandId = this.productMapper.getCurrentProdBrandIdByBrandName(cartItem.getProdName());
                String brandName = this.brandMapper.getCurrentBrandNameByBrandId(brandId);
                orderItems.setProd_brand(brandName);
                //生成当前商品的购买数量
                orderItems.setProd_quantity(cartItem.getCount());
                //所获成长值,积分
                int grouth = this.productMapper.getGrouthOfCurrentProductByProdId(cartItem.getProdId());
                int internal = this.productMapper.getInternalOfCurrentProductByProdId(cartItem.getProdId());
                orderItems.setGift_growth(grouth);
                orderItems.setGift_integration(internal);
                //计算商品属性和单价
                orderItems.setProd_status(cartItem.getProdStatus());
                orderItems.setProd_price(cartItem.getProdPrice());
                return orderItems;
            }).collect(Collectors.toList());
        }else{
            return  null;
        }
        //计算总金额
        int totalPrice = 0;
        int total  = 0;
        //积分+成长值
        int gift = 0;
        int grouth = 0;
        for (OrderItems orderItems : orderItemsList){
            total += orderItems.getProd_price() * orderItems.getProd_quantity();
            grouth +=orderItems.getGift_growth();
            gift +=orderItems.getGift_integration();
        }
        totalPrice +=total;
        //若积分不为零且优惠券不为空
       if(isUseCouple == true && isUseIntegral == true){
           if(integralValue != null){
               //总价减去积分
               if(Integer.parseInt(integralValue) % 10 == 0){
                   int getIntegralValue = getIntegralValue(Integer.parseInt(integralValue));
                   totalPrice = total - getIntegralValue;
               }else{
                   totalPrice = total;
               }
           }
           else if(coupleId != null){
               //查询当前优惠券优惠的金额  总价减去优惠券
               int coupleValue = 0;
               totalPrice = totalPrice - coupleValue;
           }else{
               //减去积分抵挡的值

               //减去优惠券抵用金额
           }
       }
        order.setTotal_amount(totalPrice * 1.00);
        Double payAmount = Double.valueOf(totalPrice)+packageFree+Double.parseDouble(delivery);
        logger.info("应支付金额为:"+payAmount);
        order.setPay_amount(payAmount);
        //设置支付方式
        order.setPay_type(paymentMethods);
        //设置订单状态
        order.setOrder_status(OrderStatusEnum.CREATE_NEW.getCode());
        //设置自动确认收货时间(3h)
        order.setAuto_confirm_time(3);
        //设置总积分  总成长值
        order.setIntegration(gift);
        order.setGrowth(grouth);
        //设置订单是否删除状态
        order.setDelete_status(0);
        //设置订单创建时间
        order.setCreate_time(new Date());
        // 修改时间
        Date date = null;
        try {
           date =  DateUtils.stringDate((DateUtils.dateString(new Date(),"yyyy-MM-dd HH:mm:ss")),"yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            e.printStackTrace();
        }
        order.setModify_time(date);
        //设置订单确认状态  默认未确认
        order.setConfirm_status(0);
        orderCreateTo.setOrder(order);
        orderCreateTo.setOrderItemsList(orderItemsList);
        return orderCreateTo;
    }

    private int getIntegralValue(int integral){
        switch (integral){
            case 100:return 1;
            case 200:return 2;
            case 300:return 3;
            case 400:return 4;
            case 500:return 5;
            case 600:return 6;
            case 700:return 7;
            case 800:return 8;
            case 900:return 9;
            case 1000:return 10;
        }
        return 0;
    }

    /**
     * @Method: getOrderStatusOfCurrOrderSn
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据订单号查询当前订单信息
     * @Return: int
     * @Exception:
     * @Date: 2021/3/3 14:23
     * @Param: * @param orderSn
     * @Return: int
     */
    @Override
    public int getOrderStatusOfCurrOrderSn(String orderSn) {
        return this.orderMapper.getOrderStatusOfCurrOrderSn(orderSn);
    }

    /**
     * @Method: closeOrder
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理关单业务
     * @Return: void
     * @Exception:
     * @Date: 2021/3/3 16:27
     * @Param: * @param order
     * @Return: void
     */
    @Override
    public void closeOrder(Order order) {
          //查询当前订单的最新状态
          Order orderInfo = this.orderMapper.getOrderInfoByOrderId(order.getOrder_id());
          //若数据库存储的订单状态为已提交
          if (orderInfo.getOrder_status().equals(OrderStatusEnum.CREATE_NEW.getCode())) {
              this.orderMapper.updateOrderInfoByOrderById(order.getOrder_id(), OrderStatusEnum.CANCLED.getCode());
              //给rabbit发送一个关单成功消息
              OrderInfoTo orderInfoTo = new OrderInfoTo();
              BeanUtils.copyProperties(orderInfo,orderInfoTo);
              //TODO 防止因为服务器网络中断而导致消息没成功发送出去
              try {
                  //TODO  保证每一条消息都会发送出去 每一个消息都需要做好日志记录(即就是在数据库创建一个下消息表用于保存消息)
                  //TODO 定期扫描数据库 若有未发送的消息进行发送
                 this.rabbitTemplate.convertAndSend("order-event-exchange","order.release.other",orderInfoTo);
              }catch (Exception e){
             }
          }

    }

    /**
     * @Method: getOrderPayInfoByOrderSn
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理获得订单支付的信息
     * @Return: com.zyt.entity.Order
     * @Exception:
     * @Date: 2021/3/4 12:37
     * @Param: * @param orderSn
     * @Return: com.zyt.entity.Order
     */
    @Override
    public Order getOrderPayInfoByOrderSn(String orderSn) {
        return this.orderMapper.getOrderPayInfoByOrderSn(orderSn) != null ? this.orderMapper.getOrderPayInfoByOrderSn(orderSn) :null;
    }

    /**
     * @Method: handleFinishedPayResult
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理订单完成支付后的订单信息
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/3/4 14:12
     * @Param: * @param payAsyncVo
     * @Return: java.lang.String
     */
    @Override
    public String handleFinishedPayResult(PayAsyncVo payAsyncVo) {
        //保存支付宝交易数据到数据库表中
        this.payMentInfoService.savePayMentInfo(payAsyncVo);
        //处理店铺收益
        this.profitInfoManagerService.handleStoreProfitInfo(payAsyncVo.getOut_trade_no());
        //修改订单状态
        if(payAsyncVo.getTrade_status().equals("TRADE_SUCCESS") ||  payAsyncVo.getTrade_status().equals("TRADE_FINISHED")){
            String outTradeNo = payAsyncVo.getOut_trade_no();
            this.orderMapper.handleUpdateOrderStatus(outTradeNo,OrderStatusEnum.PAYED.getCode());
        }
        logger.info("保存数据了");
        return "success";

    }

    /**
     * @Method: getOrderSnAndOrderTotalAccountOfCurrentUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:封装支付页订单数据
     * @Return: com.zyt.entity.vo.OrderSnAndOrderTotalAccountVo
     * @Exception:
     * @Date: 2021/3/4 15:38
     * @Param: * @param userName
     * @Return: com.zyt.entity.vo.OrderSnAndOrderTotalAccountVo
     */
    @Override
    public OrderSnAndOrderTotalAccountVo getOrderSnAndOrderTotalAccountOfCurrentUser(String userName) {

        Order order = this.orderMapper.getOrderInfoOfCurrentUser(userName,OrderStatusEnum.CREATE_NEW.getCode());
        logger.info("订单信息为:"+order.toString());
        OrderSnAndOrderTotalAccountVo orderSnAndOrderTotalAccountVo = new OrderSnAndOrderTotalAccountVo();
        orderSnAndOrderTotalAccountVo.setOrderSn(order.getOrder_sn());
        orderSnAndOrderTotalAccountVo.setTotalAccount(order.getPay_amount());
        return orderSnAndOrderTotalAccountVo;
    }

    /**
     * @Method: getUserMemberNameOfCurrOrderSn
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前订单的用户收货人昵称信息
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/3/5 15:07
     * @Param: * @param orderSn
     * @Return: java.lang.String
     */
    @Override
    public String getUserMemberNameOfCurrOrderSn(String orderSn) {

        return this.orderMapper.getUserMemberNameOfCurrOrderSn(orderSn) != null ? this.orderMapper.getUserMemberNameOfCurrOrderSn(orderSn) : null;
    }


}
