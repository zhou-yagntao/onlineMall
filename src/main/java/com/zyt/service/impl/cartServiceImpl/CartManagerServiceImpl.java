package com.zyt.service.impl.cartServiceImpl;

import com.zyt.entity.Cart;
import com.zyt.entity.CartItem;
import com.zyt.entity.vo.CartItemsInfoTo;
import com.zyt.entity.vo.DifferentStatusPriceTo;
import com.zyt.mapper.CartItemMapper;
import com.zyt.mapper.ProductMapper;
import com.zyt.mapper.StoreMapper;
import com.zyt.mapper.VipMemberMapper;
import com.zyt.service.cartService.CartManagerService;
import org.elasticsearch.common.recycler.Recycler;
import org.omg.PortableInterceptor.INACTIVE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.cartServiceImpl
 * @ClassName: CartManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 购物车管理服务层实现层
 * @Date: 20:40 2021/2/20
 * @Version: 1.0
 */
@Service(value = "cartManagerService")
public class CartManagerServiceImpl implements CartManagerService {

    private Logger logger = LoggerFactory.getLogger(CartManagerServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private VipMemberMapper vipMemberMapper;

    @Autowired
    private CartItemMapper cartItemMapper;

    @Autowired
    private StoreMapper storeMapper;

    /**
     * @Method: GetCurrentProductPrice
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:后的商品不同属性的价格
     * @Return: int
     * @Exception:
     * @Date: 2021/2/24 14:29
     * @Param: * @param prodId
     * @Return: int
     */
    @Override
    public DifferentStatusPriceTo GetCurrentProductPrice(String prodId) {

        DifferentStatusPriceTo differentStatusPriceTo = new DifferentStatusPriceTo();
        Integer productId = Integer.parseInt(prodId);
        String productPrice = this.productMapper.getCurrentProductPrice(productId).replaceAll("元","");
        if (productPrice == null){ return  new DifferentStatusPriceTo();}
        differentStatusPriceTo.setSmallPrice(Integer.parseInt(productPrice.substring(0,2)));
        differentStatusPriceTo.setMidPrice(Integer.parseInt(productPrice.substring(3,5)));
        differentStatusPriceTo.setBigPrice(Integer.parseInt(productPrice.substring(6)));
        return differentStatusPriceTo;
    }

    /**
     * @Method: addProductToCart
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:加入购物信息到数据库
     * @Return: int
     * @Exception:
     * @Date: 2021/2/24 18:13
     * @Param: * @param prodId
     * @param storeName
     * @param prodImg
     * @param prodName
     * @param userLoginName
     * @param productStatus
     * @param purchaseCounts
     * @param priceTotalPrice
     * @Return: int
     */
    @Override
    @Transactional
    public int addProductToCart(String prodId, String storeName, String prodImg, String prodName, String userLoginName, String productStatus, String purchaseCounts, String priceTotalPrice) {
        //根据用户名查询用户id
        Integer userId = this.vipMemberMapper.getUserIdByUserName(userLoginName);

        int user_id = 0;
        if (userId == null){
            user_id = 0;
        }else{
            user_id = userId.intValue();
        }
        //封装购物车数据信息
        CartItem cartItem = new CartItem();
        //封装当前用户id
        cartItem.setUserId(user_id);
        //封装当前用户购物车信息--商品编号+名称
        cartItem.setProdId(Integer.parseInt(prodId));
        cartItem.setProdName(prodName);
        //封装店铺信息
        cartItem.setStoreName(storeName);
        //分装商品图片信息
        cartItem.setProdImg(prodImg);
        //封装购买商品的数量+属性+总价+单价信息
        cartItem.setProdStatus(productStatus);
        String price = this.productMapper.getCurrentProductPrice(Integer.parseInt(prodId));
        int product_price;
//        if(price == null){
//            product_price = 0;
//        }
//        product_price =  productStatus.equals("小杯") ? Integer.parseInt(price.replaceAll("元","").substring(0,2))
//                        :productStatus.equals("中杯") ? Integer.parseInt(price.replaceAll("元","").substring(3,5))
//                        :productStatus.equals("大杯") ? Integer.parseInt(price.replaceAll("元","").substring(6))
//                        :Integer.parseInt("0");
        if ("小杯".equals(productStatus)){
            product_price = Integer.parseInt(price.replaceAll("元","").substring(0,2));
        }
        else if("中杯".equals(productStatus)){
            product_price = Integer.parseInt(price.replaceAll("元","").substring(3,5));
        }else{
            product_price = Integer.parseInt(price.replaceAll("元","").substring(6));
        }
        cartItem.setProdPrice(product_price);
        cartItem.setCount(Integer.parseInt(purchaseCounts));
        cartItem.setTotalPrice(Integer.parseInt(priceTotalPrice));
        logger.info("封装的购物项信息为:"+cartItem.toString());
        int result = this.cartItemMapper.addProductToCart(cartItem);
        return result > 0 ? result : 0;
    }

    /**
     * @Method: GetCartItemInfoOfCurrUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询个人名下购物信息
     * @Return: java.util.List<com.zyt.entity.CartItem>
     * @Exception:
     * @Date: 2021/2/24 18:12
     * @Param: * @param userName
     * @Return: java.util.List<com.zyt.entity.CartItem>
     */
    @Override
    public List<CartItemsInfoTo> GetCartItemInfoOfCurrUser(String userName) {
        //根据用户名查询当前用户的id
        Integer user_id = this.vipMemberMapper.getUserIdByUserName(userName);
        int userId =  user_id != null ? user_id.intValue() : null;
        //查询购物车项目信息
        List<CartItem> cartItemInfos = this.cartItemMapper.getCartItemInfoOfCurrUser(userId);
        //封装我们需要的数据结构
        List<CartItemsInfoTo> cartItemsInfoTos =  cartItemInfos.stream().map(cartItem -> {
             CartItemsInfoTo cartItemsInfoTo  = new CartItemsInfoTo();
             cartItemsInfoTo.setCartItemId(cartItem.getCartItemId());
             cartItemsInfoTo.setCheck(cartItem.getCheck());
             cartItemsInfoTo.setCount(cartItem.getCount());
             cartItemsInfoTo.setProdId(cartItem.getProdId());
             cartItemsInfoTo.setProdName(cartItem.getProdName());
             cartItemsInfoTo.setProdImg(cartItem.getProdImg());
             cartItemsInfoTo.setProdPrice(cartItem.getProdPrice());
             cartItemsInfoTo.setProdStatus(cartItem.getProdStatus());
             cartItemsInfoTo.setTotalPrice(cartItem.getTotalPrice());
             cartItemsInfoTo.setUserId(cartItem.getUserId());
             cartItemsInfoTo.setStoreName(cartItem.getStoreName());
             //根据店铺名称查询店铺图片信息
             String storeImg = this.storeMapper.getStoreImgByStoreName(cartItem.getStoreName());
             cartItemsInfoTo.setStoreImg(storeImg);
             return  cartItemsInfoTo;
         }).collect(Collectors.toList());
        if (cartItemsInfoTos == null && cartItemsInfoTos.size() == 0){
            return  null;
        }
        return  cartItemsInfoTos;
    }

    /**
     * @Method: UpdateCartItemInfoOfCurrentProdIdAndUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据用户id+商品id查询商品信息
     * @Return: int
     * @Exception:
     * @Date: 2021/2/24 19:29
     * @Param: * @param prodId
     * @param loginUserName
     * @Return: int
     */
    @Override
    @Transactional
    public int UpdateCartItemInfoOfCurrentProdIdAndUser(String prodId, String loginUserName) {
        //根据用户名查询用户id
        Integer user_Id  = this.vipMemberMapper.getUserIdByUserName(loginUserName);
        int userId = 0;
        if (user_Id == null){
            userId = 0;
        }else{
            userId = user_Id.intValue();
        }
        int prod_Id = Integer.parseInt(prodId);
        int result = this.cartItemMapper.updateCartItemInfoOfCurrentProdIdAndUser(userId,prod_Id,false);
        if(result > 0){
//            //查询到已经选中的商品信息
//             int count = this.cartItemMapper.getCartItemInfoCountsOfIsCheck(true,userId);
//             int totalPrice = this.cartItemMapper.getCartItemInfoTotalPriceOfIsCheck(true,userId);
//             Cart cart = new Cart();
//             cart.setCountNum(count);
//             cart.setTotalAmount(totalPrice);
//             return  cart;
            return  1;
        }else{
            return  0;
        }
    }

    /**
     * @Method: GetCheckProdCountsAndTotalPrice
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 获得当前用户名下已经选中的商品数量和价格
     * @Return: com.zyt.entity.Cart
     * @Exception:
     * @Date: 2021/2/24 20:42
     * @Param: * @param loginUserName
     * @Return: com.zyt.entity.Cart
     */
    @Override
    public Cart GetCheckProdCountsAndTotalPrice(String loginUserName) {

        //根据用户名查询用户id
        Integer user_Id  = this.vipMemberMapper.getUserIdByUserName(loginUserName);
        int userId = 0;
        if (user_Id == null){
            userId = 0;
        }else{
            userId = user_Id.intValue();
        }
        Boolean isCheck  = true;
        int count = this.cartItemMapper.getCartItemInfoCountsOfIsCheck(isCheck,userId);
        int totalPrice = this.cartItemMapper.getCartItemInfoTotalPriceOfIsCheck(isCheck,userId);
        Cart cart = new Cart();
        cart.setCountNum(count);
        cart.setTotalAmount(totalPrice);
        return cart != null ? cart : null;
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改当前商品的数量 价格
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 10:46
      * @Param: * @param null
      * @Return:
      */
    @Override
    @Transactional
    public int UpdateCurrCheckProdTotalPrice(String cartId, String count, String prodPrice) {
        Integer CartId = Integer.parseInt(cartId);
        Integer Count = Integer.parseInt(count);
        //计算修改后的商品总价格
        Integer ProdPrirse = Integer.parseInt(prodPrice);
        int totalPrice = Count *  ProdPrirse;
        logger.info("传入的参数为:"+CartId+"\t"+Count+"\t"+totalPrice);
        int result = this.cartItemMapper.updateCurrCheckProdCountAndTotalPrice(CartId,Count,totalPrice);
        return result > 0 ? result : 0;
    }

    @Override
    public List<CartItem> GetIsCheckedProdinfoOfCurrUser(String userName, Boolean isCheck) {
        Integer user_Id = this.vipMemberMapper.getUserIdByUserName(userName);
        int userId = user_Id != null ? user_Id.intValue() : 0;
        return this.cartItemMapper.getIsCheckedProdinfoOfCurrUser(userId,isCheck) != null ?
                this.cartItemMapper.getIsCheckedProdinfoOfCurrUser(userId,isCheck) :
                null;
    }

    @Override
    public int getIsCheckedProdTotalPriceOfCurrUser(String userName, Boolean isCheck) {
        Integer user_Id = this.vipMemberMapper.getUserIdByUserName(userName);
        int userId = user_Id != null ? user_Id.intValue() : 0;
        return this.cartItemMapper.getIsCheckedProdTotalPriceOfCurrUser(userId,isCheck);
    }
}
