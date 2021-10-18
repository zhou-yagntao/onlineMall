package com.zyt.controller.cartMoudle;


import com.zyt.entity.Cart;
import com.zyt.entity.CartItem;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.vo.CartItemsInfoTo;
import com.zyt.entity.vo.DifferentStatusPriceTo;
import com.zyt.service.impl.cartServiceImpl.CartManagerServiceImpl;
import javafx.scene.chart.ValueAxis;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.cartMoudle
 * @ClassName: CartManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 购物车管理控制层
 * @Date: 18:32 2021/2/20
 * @Version: 1.0
 */
@Controller
@RequestMapping("/cartManager")
public class CartManagerController {

    private Logger logger = LoggerFactory.getLogger(CartManagerController.class);


    @Autowired
    private CartManagerServiceImpl cartManagerService;

    /**
     * @Method: EnterCartListPage
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:跳转到购物车列表页
     * @Return: void
     * @Exception:
     * @Date: 2021/2/22 18:31
     * @Param: * @param request
     * @param
     * @Return: void
     * 浏览器会为用户分配一个key用来标识用户，并设置过期时间
     * 若第一次使用购物车功能  会临时给一个用户身份信息
     * 浏览器以后保存都会带上这个cookie
     *
     * 若登录 => session
     * 未登录 => 使用cookie中自带的key信息
     * 注:第一次如果没有临时用户，帮忙创建一个临时用户
     */
    @PostMapping(value = "/cartListPage")
    @ResponseBody
    public Result CartListPage(String identity,HttpServletRequest request) throws IOException {
         HttpSession session = request.getSession();
        //获得当前线程共享的用户信息
        //EncapsulateUserInfoTo encapsulateUserInfoTo = CartInterceptor.threadLocal.get();
        if (identity.equals("游客")){

        }
       // logger.info("封装信息为:"+encapsulateUserInfoTo);
        return  Result.success(ResultCode.SUCCESS);
    }

    /**
     * @Method: GetCurrentProductPrice
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得商品价格信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/24 14:33
     * @Param: * @param productId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getCurrentProductPrice",method = RequestMethod.POST)
    @ResponseBody
    public Result GetCurrentProductPrice(@RequestParam(value = "productId",required = false)String productId){
       logger.info("获得参数信息为:"+productId);
       DifferentStatusPriceTo differentStatusPriceTo = this.cartManagerService.GetCurrentProductPrice(productId);
       logger.info("获得结尾为:"+differentStatusPriceTo);
       if (differentStatusPriceTo == null ){
           return  Result.failure(ResultCode.RESULE_DATA_NONE);
       }
       return  Result.success(ResultCode.SUCCESS,differentStatusPriceTo);
    }

    /**
     * @Method: AddProductToCart
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:添加商品信息到购物车
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/24 17:47
     * @Param: * @param prodId
     * @param storeName
     * @param prodImg
     * @param prodName
     * @param userLoginName
     * @param productStatus
     * @param purchaseCounts
     * @param priceTotalPrice
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/addProductToCart",method = RequestMethod.POST)
    @ResponseBody
    public Result  AddProductToCart(@RequestParam(value = "prodId",required = false)String prodId,
                                    @RequestParam(value = "storeName",required = false)String storeName,
                                    @RequestParam(value = "prodImg",required = false)String prodImg,
                                    @RequestParam(value = "prodName",required = false)String prodName,
                                    @RequestParam(value = "userLoginName",required =  false)String userLoginName,
                                    @RequestParam(value = "productStatus",required =  false)String productStatus,
                                    @RequestParam(value = "purchaseCounts",required =  false)String purchaseCounts,
                                    @RequestParam(value = "priceTotalPrice",required =  false)String priceTotalPrice){
        logger.info("商品信息为:"+prodId+"\t"+storeName+"\t"+prodImg+"\t"+prodName+"\t"+userLoginName+"\t"+productStatus+"\t"+purchaseCounts+"\t"+priceTotalPrice);
        int result = this.cartManagerService.addProductToCart(prodId,storeName,prodImg,prodName,userLoginName,productStatus,purchaseCounts,priceTotalPrice);
        if (result == 0){
            //添加数据失败则返回并封装失败信息状态码
            return  Result.failure(ResultCode.SPECIFIED_QUESTIONED_FAILURE_ADD_CART_ITEMS);
        }
        //添加数据成功后返回成功状态码
        return  Result.success(ResultCode.SUCCESS);
    }

    /**
     * @Method: GetCartItemsInfoOfCurrUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据用户名查询当前用户的购物车商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/24 18:08
     * @Param: * @param loginUserName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getCartItemsInfoOfCurrUser",method = RequestMethod.POST)
    @ResponseBody
    public Result GetCartItemsInfoOfCurrUser(@RequestParam(value = "loginUserName",required = false)String loginUserName){
        List<CartItemsInfoTo> cartItemInfoToList = this.cartManagerService.GetCartItemInfoOfCurrUser(loginUserName);
        if (cartItemInfoToList == null && cartItemInfoToList.size() == 0){
            return  Result.failure(ResultCode.RESULE_DATA_NONE);
        }
        return  Result.success(ResultCode.SUCCESS,cartItemInfoToList);
    }

    /**
     * @Method: UpdateCartItemInfoOfCurrentProdIdAndUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据当前用户以及商品id修改商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/24 19:27
     * @Param: * @param prodId
     * @param loginUserName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/updateCartItemInfoOfCurrentProdIdAndUser",method = RequestMethod.POST)
    @ResponseBody
    public Result UpdateCartItemInfoOfCurrentProdIdAndUser(@RequestParam(value = "prodId",required = false)String prodId,
                                                           @RequestParam(value = "loginUserName",required = false)String loginUserName){
         int result  = this.cartManagerService.UpdateCartItemInfoOfCurrentProdIdAndUser(prodId,loginUserName);
         if (result == 0){
             return  Result.failure(ResultCode.FAILURE);
         }
         return  Result.success(ResultCode.SUCCESS);
    }

    /**
     * @Method: GetCheckProdCountsAndTotalPrice
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/25 9:39
     * @Param: * @param loginUserName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getCheckProdCountsAndTotalPrice",method = RequestMethod.POST)
    @ResponseBody
    public Result GetCheckProdCountsAndTotalPrice(@RequestParam(value = "loginUserName",required = false)String loginUserName){

        Cart cart = this.cartManagerService.GetCheckProdCountsAndTotalPrice(loginUserName);
        if (cart == null){
            return  Result.failure(ResultCode.RESULE_DATA_NONE);
        }
        return  Result.success(ResultCode.SUCCESS,cart);
    }
    /**
     * @Method: UpdateCurrCheckProdTotalPrice
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/25 10:08
     * @Param: * @param cartId
     * @param count
     * @param prodPrice
     * @Return: com.zyt.entity.Result
     */
     @RequestMapping(value = "/updateCurrCheckProdTotalPrice",method = RequestMethod.POST)
     @ResponseBody
    public Result UpdateCurrCheckProdTotalPrice(@RequestParam(value = "cartId",required = false)String cartId,
                                                @RequestParam(value = "count",required = false) String count,
                                                @RequestParam(value = "prodPrice",required = false)String prodPrice){
         int result = this.cartManagerService.UpdateCurrCheckProdTotalPrice(cartId,count,prodPrice);
         if (result == 0){
             return  Result.failure(ResultCode.FAILURE);

         }
         return Result.success(ResultCode.SUCCESS);
     }


    /**
     * @Method: GetIsCheckedProdinfoOfCurrUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前用户准备下单的商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/25 14:01
     * @Param: * @param userName
     * @Return: com.zyt.entity.Result
     */
    @PostMapping(value = "/getIsCheckedProdinfoOfCurrUser")
    @ResponseBody
    public Result GetIsCheckedProdinfoOfCurrUser(@RequestParam(value = "userName",required = false)String userName){
        return this.cartManagerService.GetIsCheckedProdinfoOfCurrUser(userName,true) != null  && this.cartManagerService.getIsCheckedProdTotalPriceOfCurrUser(userName,true) != 0?
                Result.success(ResultCode.SUCCESS,this.cartManagerService.GetIsCheckedProdinfoOfCurrUser(userName,true),this.cartManagerService.getIsCheckedProdTotalPriceOfCurrUser(userName,true))
                :Result.failure(ResultCode.FAILURE);

    }

}
