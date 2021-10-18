package com.zyt.mapper;

import com.zyt.entity.Product;
import com.zyt.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: ProductMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品信息管理持久层
 * @Date: 12:48 2021/1/25
 * @Version: 1.0
 */
@Mapper
@Repository(value = "productMapper")
public interface ProductMapper {
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:新的商品入驻
      * @Return:
      * @Exception:
      * @Date: 2021/1/25 12:50
      * @Param: * @param null
      * @Return:
      */
    public int addNewStoreProduct(Product product);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询所有商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 10:53
      * @Param: * @param null
      * @Return:
      */
    //public List<Product> getAllProductRelationInfos(@Param("start") int start,@Param("end") int end,@Param("storeName") String storeName);
    public List<Product> getAllProductRelationInfos(@Param("start") int start,@Param("end") int end);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 查询当前店铺下商品信息总数
     * @Return:
     * @Exception:
     * @Date: 2021/2/1 10:55
     * @Param: * @param null
     * @Return:
     */
    //public int allProductCountsCurrentStore(@Param("storeName") String storeName);
    public int allProductCountsCurrentStore();


    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据查询商品系信息
     * @Return:
     * @Exception:
     * @Date: 2021/2/1 14:50
     * @Param: * @param null
     * @Return:
     */
    public List<Product> getProductInfosByItems(@Param("start") int start,@Param("end") int end,@Param("productName") String productName,@Param("storeName") String storeName,@Param("prodBrandId") Integer prodBrandId);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询商品数量
     * @Return:
     * @Exception:
     * @Date: 2021/2/1 14:50
     * @Param: * @param null
     * @Return:
     */
    public int getProductInfoCountsBYItems(@Param("productName") String productName,@Param("storeName") String storeName,@Param("prodBrandId") Integer prodBrandId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:删除商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:35
      * @Param: * @param null
      * @Return:
      */
    public int deleteProductByProdId(@Param("productId") int productId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:56
      * @Param: * @param null
      * @Return:
      */
    public int updateProductCoupleState(@Param("productId") int productId,@Param("coupleState") String coupleState);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:56
      * @Param: * @param null
      * @Return:
      */

    public int updateProductIntegeral(@Param("productId") int productId,@Param("integralState") String integralState);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 获得当前编号所有商品信息
     * @Return:
     * @Exception:
     * @Date: 2021/2/5 14:17
     * @Param: * @param null
     * @Return:
     */
    public List<Product> getProductInfoByProdId(@Param("productId") int productId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 修改商品是否上架状态
      * @Return:
      * @Exception:
      * @Date: 2021/2/5 20:06
      * @Param: * @param null
      * @Return:
      */
    public int updateProductIsOnSheleves(@Param("productId") int productId,@Param("flag")boolean flag);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件查询商品的信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/6 10:04
      * @Param: * @param null
      * @Return:
      */
    public List<Product> searchProInfoByItems(@Param("start") int start,@Param("end") int end,@Param("params")String params);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 根据条件查询商品的数量
     * @Return:
     * @Exception:
     * @Date: 2021/2/6 9:57
     * @Param: * @param null
     * @Return:
     */
    public int searchProInfoCountsByItems(@Param("params")String params);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得商品信息进行前台渲染
      * @Return:
      * @Exception:
      * @Date: 2021/2/10 16:55
      * @Param: * @param null
      * @Return:
      */
    public List<Product> getAllProductInfos();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据编号获得top 9商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/16 13:52
      * @Param: * @param null
      * @Return:
      */
    public List<Product> getProductsInfoByProdId(List<Integer>prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/18 19:34
      * @Param: * @param null
      * @Return:
      */
    public  List<Product> getAllProductInfoOfCurrBrand(@Param("brandId") int brandId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 根据商品名称+店铺名称获得商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 10:09
      * @Param: * @param null
      * @Return:
      */
    public List<Product> getProductDetailsByProdIdAndStoreName(@Param("prodId")int prodId,@Param("storeName")String storeName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 获得当前商品的价格
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 14:27
      * @Param: * @param null
      * @Return:
      */
    public String getCurrentProductPrice(@Param("prodId") int prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得商品的名称
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 13:08
      * @Param: * @param null
      * @Return:
      */
    public int getCurrentProdBrandIdByBrandName(@Param("prod_Name") String prod_Name);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得成长值
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 13:24
      * @Param: * @param null
      * @Return:
      */
    public int getGrouthOfCurrentProductByProdId(@Param("prodId") Integer prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得积分
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 13:24
      * @Param: * @param null
      * @Return:
      */
    public int getInternalOfCurrentProductByProdId(@Param("prodId") Integer prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件获得商品详细信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 21:24
      * @Param: * @param null
      * @Return:
      */
    public  List<Product> getProductInfoOfCurrStore(String storeName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据商品名称获得商品id
      * @Return:
      * @Exception:
      * @Date: 2021/3/9 16:17
      * @Param: * @param null
      * @Return:
      */
    public int getProdIdByProdName(@Param("prod_Name") String prod_Name,@Param("storename") String storename);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据id查询当前id对应的商品信
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 17:06
      * @Param: * @param null
      * @Return:
      */
    public Product getProductInfoOfCurrSecKillProdByProdId(@Param("prod_Id") int prod_Id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得随机的商品img
      * @Return:
      * @Exception:
      * @Date: 2021/4/10 22:23
      * @Param: * @param null
      * @Return:
      */
    public List<String> getProdImgInfoByProdId(Integer[] prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获取当前品牌的的所有商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/17 12:52
      * @Param: * @param null
      * @Return:
      */
    public List<Product> getProductInfoOfCurrBrandId(@Param("brandId") Integer brandId);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return:
     * @Exception:
     * @Date: 2021/4/27 15:44
     * @Param: * @param null
     * @Return:
     */
    public List<Product> getProductInfosByProdId(@Param("productId") List<Integer> productId);
}
