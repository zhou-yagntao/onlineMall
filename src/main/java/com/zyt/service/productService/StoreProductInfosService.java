package com.zyt.service.productService;

import com.zyt.entity.Product;
import com.zyt.entity.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: StoreProductInfosService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商店商品信息管理服务层
 * @Date: 13:21 2021/1/26
 * @Version: 1.0
 */
public interface StoreProductInfosService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:新增商品
      * @Return:
      * @Exception:
      * @Date: 2021/1/26 13:22
      * @Param: * @param null
      * @Return:
      */
    public int AddNewProductInfos(MultipartFile file,String [] params);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 查询所有商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/28 10:25
      * @Param: * @param null
      * @Return:
      */
    public List<Product> GetCurrentStoreAllProductInfos(String currentPage,String pageLimit,String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询当前用户名下所有商品的数量
      * @Return:
      * @Exception:
      * @Date: 2021/1/28 10:34
      * @Param: * @param null
      * @Return:
      */
    public int GetCurrrentStoreAllProductsCounts(String userName);

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
    public List<Product> GetProductInfosByItems(String currentPage,String pageLimit,String productName,String storeName,String prodBrandId);

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
    public int GetProductInfoCountsBYItems(String productName,String storeName,String prodBrandId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据编号删除商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:34
      * @Param: * @param null
      * @Return:
      */
    public int DeleteProductByProdId(String productId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:50
      * @Param: * @param null
      * @Return:
      */
    public int UpdateProductCoupleState(String productId,String coupleState);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:51
      * @Param: * @param null
      * @Return:
      */
    public int UpdateProductIntegeral(String productId,String integralState);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 商品上架业务
      * @Return:
      * @Exception:
      * @Date: 2021/2/4 11:39
      * @Param: * @param null
      * @Return:
      */
    public int ProductUp(String prodId,String brandId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询商品信息根据条件
      * @Return:
      * @Exception:
      * @Date: 2021/2/6 9:56
      * @Param: * @param null
      * @Return:
      */
    public List<Product> SearchProInfoByItems(String currentPage,String pageLimit,String params);

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
    public int SearchProInfoCountsByItems(String params);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得所有商品信息进行前端页面渲染
      * @Return:
      * @Exception:
      * @Date: 2021/2/10 16:55
      * @Param: * @param null
      * @Return:
      */
    public List<Product> GetAllProductInfos();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/18 19:31
      * @Param: * @param null
      * @Return:
      */
    public  List<Product> GetAllProductInfoOfCurrBrand(String brandName);

}
