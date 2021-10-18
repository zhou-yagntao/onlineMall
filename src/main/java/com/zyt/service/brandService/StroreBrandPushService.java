package com.zyt.service.brandService;

import com.zyt.entity.Store;
import com.zyt.entity.StoreBrand;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: StroreBrandPushService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品品牌管理服务层
 * @Date: 12:23 2021/1/24
 * @Version: 1.0
 */
public interface StroreBrandPushService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 14:25
      * @Param: * @param null
      * @Return:
      */
    public int PushStoreBrandInfos(String[] brandsInfos) throws Exception;

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得商品详细信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 14:26
      * @Param: * @param null
      * @Return:
      */
    public List<StoreBrand> GetStoreBrandDetailInfos(String start,String end,String isForbiddenBrand);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得商品的数量
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 14:27
      * @Param: * @param null
      * @Return:
      */
    public  int GetStoreBrandCounts(String isForbiddenBrand);


     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改品牌信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 16:36
      * @Param: * @param null
      * @Return:
      */
    public int UpdateStoreBrandInfosByBrandId(String brandId,String isForbidden);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:删除商品品牌信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 17:04
      * @Param: * @param null
      * @Return:
      */
    public  int DeleteStoreBrandInfosByBrandId(String brandId);


     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得所有的商品的信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 17:19
      * @Param: * @param null
      * @Return:
      */
    public List<StoreBrand> GetAllProductBrandName();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得品牌信息进行前台渲染
      * @Return:
      * @Exception:
      * @Date: 2021/2/8 16:15
      * @Param: * @param null
      * @Return:
      */
    public  List<StoreBrand> GetAllProductBrandTypeMenu();

     /**
      * @Method: 
      * @Author: Justin
      * @Version  1.0
      * @Description:
      * @Return: 
      * @Exception: 
      * @Date: 2021/2/8 18:15
      * @Param: * @param null
      * @Return: 
      */
    public List<StoreBrand> GetStoreInfoOfBrand(String brandName, String currPostions);
}
