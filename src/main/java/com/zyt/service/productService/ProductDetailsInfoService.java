package com.zyt.service.productService;

import com.zyt.entity.Product;
import com.zyt.entity.vo.ProductDetailsTo;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.productService
 * @ClassName: ProductDetailsInfoService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品详情页服务层
 * @Date: 12:38 2021/2/23
 * @Version: 1.0
 */
public interface ProductDetailsInfoService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 21:21
      * @Param: * @param null
      * @Return:
      */
    public List<ProductDetailsTo> GetProductDetailsInfoByProdIdAndStoreName(String prodId, String storeName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件获得详细商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 21:21
      * @Param: * @param null
      * @Return:
      */
    public List<Product> getProductInfoOfCurrStore(String storeName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据商品名称获得商品id
      * @Return:
      * @Exception:
      * @Date: 2021/3/9 16:15
      * @Param: * @param null
      * @Return:
      */
    public int getProdIdByProdName(String prodId,String storename);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据商品id获得当前id对应s商品信
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 17:04
      * @Param: * @param null
      * @Return:
      */
    public Product getProductInfoOfCurrSecKillProdByProdId(Integer prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得随机商品展示信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/10 22:01
      * @Param: * @param null
      * @Return:
      */
    public List<String> getRandomProductImgInfo();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据品牌id获得所有商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/17 12:50
      * @Param: * @param null
      * @Return:
      */
     public List<Product> getProductInfoOfCurrBrandId(Integer brandId);
}
