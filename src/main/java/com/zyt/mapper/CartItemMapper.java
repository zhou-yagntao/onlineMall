package com.zyt.mapper;

import com.zyt.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: CartItem
 * @Author: zhou_yangtao@yeah.net
 * @Description: 购物车数据信息
 * @Date: 14:10 2021/2/24
 * @Version: 1.0
 */
@Mapper
@Repository(value = "cartItemMapper")
public interface CartItemMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加商品信息到购物车
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 16:42
      * @Param: * @param null
      * @Return:
      */
    public int addProductToCart(@Param("cartItem") CartItem cartItem);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询当前用户的购物车信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 18:03
      * @Param: * @param null
      * @Return:
      */
    public List<CartItem> getCartItemInfoOfCurrUser(@Param("userId") Integer userId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改当前用户下当前商品是否选中
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 19:33
      * @Param: * @param null
      * @Return:
      */
    public int updateCartItemInfoOfCurrentProdIdAndUser(@Param("userId") int userId, @Param("prodId") int prodId,@Param("isCheck") Boolean isCheck);

     /**
      * @Method:
      * @Author:  zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询选中的商品数量
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 19:53
      * @Param: * @param null
      * @Return:
      */
    public int getCartItemInfoCountsOfIsCheck(@Param("isCheck") Boolean isCheck,@Param("user_Id") int user_Id);
    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询商品总价格
     * @Return:
     * @Exception:
     * @Date: 2021/2/24 19:53
     * @Param: * @param null
     * @Return:
     */
    public int getCartItemInfoTotalPriceOfIsCheck(@Param("isCheck") Boolean isCheck,@Param("user_Id") int user_Id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 10:55
      * @Param: * @param null
      * @Return:
      */
    public  int updateCurrCheckProdCountAndTotalPrice(@Param("cartId") Integer cartId,@Param("count") Integer count,@Param("prodPrirse") Integer prodPrirse);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户以及选中的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 14:04
      * @Param: * @param null
      * @Return:
      */
    public List<CartItem> getIsCheckedProdinfoOfCurrUser(@Param("userId") Integer userId,@Param("isCheck") Boolean isCheck);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户选中商品总价格
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 14:27
      * @Param: * @param null
      * @Return:
      */
    public int getIsCheckedProdTotalPriceOfCurrUser(@Param("userId") Integer userId,@Param("isCheck") Boolean isCheck);
}
