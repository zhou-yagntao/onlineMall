package com.zyt.service.cartService;

import com.zyt.entity.Cart;
import com.zyt.entity.CartItem;
import com.zyt.entity.Result;
import com.zyt.entity.vo.CartItemsInfoTo;
import com.zyt.entity.vo.DifferentStatusPriceTo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.cartService
 * @ClassName: CartManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 购物车管理服务层
 * @Date: 20:39 2021/2/20
 * @Version: 1.0
 */
public interface CartManagerService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前商品的价格
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 16:41
      * @Param: * @param null
      * @Return:
      */
    public DifferentStatusPriceTo GetCurrentProductPrice(String prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 16:41
      * @Param: * @param null
      * @Return:
      */
    public int addProductToCart(String prodId, String storeName,String prodImg,String prodName,String userLoginName,String productStatus,String purchaseCounts,String priceTotalPrice);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户的购物项
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 18:02
      * @Param: * @param null
      * @Return:
      */
    public List<CartItemsInfoTo> GetCartItemInfoOfCurrUser(String userName);
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 10:45
      * @Param: * @param null
      * @Return:
      */
    public int UpdateCartItemInfoOfCurrentProdIdAndUser(String prodId, String loginUserName);
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 10:45
      * @Param: * @param null
      * @Return:
      */
    public Cart GetCheckProdCountsAndTotalPrice(String loginUserName);
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 10:45
      * @Param: * @param null
      * @Return:
      */
    public int UpdateCurrCheckProdTotalPrice(String cartId, String count, String prodPrice);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户准备购买的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 14:03
      * @Param: * @param null
      * @Return:
      */
    public List<CartItem> GetIsCheckedProdinfoOfCurrUser(String userName, Boolean isCheck);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前选中上的总价格
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 14:28
      * @Param: * @param null
      * @Return:
      */
    public int getIsCheckedProdTotalPriceOfCurrUser(String userName, Boolean isCheck);
}

