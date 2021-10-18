package com.zyt.service.impl.payServiceImpl;

import com.alipay.api.AlipayApiException;
import com.zyt.config.AlipayConfig;
import com.zyt.entity.Order;
import com.zyt.entity.OrderItems;
import com.zyt.entity.VipMember;
import com.zyt.entity.vo.PayVo;
import com.zyt.mapper.OrderItemMapper;
import com.zyt.service.memberService.ViPMemberShipManagerService;
import com.zyt.service.orderService.OrderManagerService;
import com.zyt.service.payService.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.payServiceImpl.PayOrderService
 * @ClassName: Impl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付管理服务层实现模块
 * @Date: 12:30 2021/3/4
 * @Version: 1.0
 */
@Service(value = "payOrderService")
public class PayOrderServiceImpl implements PayOrderService {

    @Autowired
    private AlipayConfig alipayConfig;

    @Autowired
    private OrderManagerService orderManagerService;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ViPMemberShipManagerService viPMemberShipManagerService;

    /**
     * @Method: pay
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理订单支付
     * @Return: void
     * @Exception:
     * @Date: 2021/3/4 12:33
     * @Param: * @param orderSn
     * @Return: void
     */
    @Override
    public String pay(String orderSn) throws AlipayApiException {
        //根据订单编号获得订单详细信息
        Order order = this.orderManagerService.getOrderPayInfoByOrderSn(orderSn);
        //封装订单信息
        PayVo payVo = new PayVo();
        payVo.setOut_trade_no(orderSn);
        String subject = "";
        List<OrderItems> orderItemsList = this.orderItemMapper.getOrderItemsPayInfoByOrderSn(orderSn) != null ? this.orderItemMapper.getOrderItemsPayInfoByOrderSn(orderSn) : null;
        for (int i = 0; i < orderItemsList.size(); i++) {
            subject += (i == 0) ? orderItemsList.get(i).getProd_name() : "|"+orderItemsList.get(i).getProd_name();
        }
        payVo.setSubject(subject);
        //判断今天是否是该用户的生日
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(date);
        System.out.println("格式化后的日期：" + dateNowStr);
        String subStrBirth = "%"+dateNowStr.substring(5,10)+"%";
        //通过订单查询到当前用户的用户名
        String memeberName = this.orderManagerService.getUserMemberNameOfCurrOrderSn(orderSn);
        //通过会员名和生日日期获得当前会员信息
        VipMember vipMember = this.viPMemberShipManagerService.getVipMemberInfoOfCurrMemeberName(subStrBirth,memeberName);
        if (vipMember != null){
            //当前是该用户的生日 减免10元
            payVo.setTotal_amount(String.valueOf(order.getPay_amount() - 10));
        }
        payVo.setTotal_amount(String.valueOf(order.getPay_amount()));
        payVo.setBody("即将准备支付当前订单");
        return  this.alipayConfig.pay(payVo) != null ? this.alipayConfig.pay(payVo) : null;
    }
}
