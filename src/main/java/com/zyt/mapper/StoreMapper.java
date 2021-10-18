package com.zyt.mapper;

import com.zyt.entity.Store;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: StoreMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺信息持久层
 * @Date: 17:54 2021/1/18
 * @Version: 1.0
 */
@Mapper
@Repository(value = "storeMapper")
public interface StoreMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 店铺入驻
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 12:28
      * @Param: * @param null
      * @Return:
      */
    public int storeSettled(Store store);

     /**
      * @Method:
      * @Author: Justin
      * @Version  1.0
      * @Description:获取店铺详细信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 12:28
      * @Param: * @param null
      * @Return:
      */
    public List<Store> getStoreDetailInfos(@Param("start") int start, @Param("end") int end);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获取店铺的总数量
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 12:28
      * @Param: * @param null
      * @Return:
      */
    public int getStoreTotalCounts();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品的信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 12:28
      * @Param: * @param null
      * @Return:
      */
    public int updateStoreInfos(Store store);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:删除商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 12:28
      * @Param: * @param null
      * @Return:
      */
    public int deleteStoreInfosByStoreId(@Param("storeId") Integer storeId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/1/26 15:24
      * @Param: * @param null
      * @Return:
      */
    public String getStoreNameByOwnerName(@Param("storeOwnerName") String storeOwnerName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/1/26 15:24
      * @Param: * @param null
      * @Return:
      */
    public Integer  getStoreIdByStoreName(@Param("storeName") String storeName);
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据店铺名称查询店铺信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/6 16:55
      * @Param: * @param null
      * @Return:
      */
    public Store getStoreInfosByStoreName(@Param("storeName")String storeName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据店铺拥有者姓名查询店铺编号
      * @Return:
      * @Exception:
      * @Date: 2021/2/6 20:09
      * @Param: * @param null
      * @Return:
      */
    public int getStoreIdByStoreOwnerName(@Param("storeOwner") String storeOwner);


    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据查询到的前top的店铺编号去查询店铺信息
     * @Return:
     * @Exception:
     * @Date: 2021/2/14 11:22
     * @Param: * @param null
     * @Return:
     * @return
     * @param storeId
     */
    public Store getStoreInfoByStoreId(@Param("storeId") int storeId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description://根据位置店铺名称信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/18 18:52
      * @Param: * @param null
      * @Return:
      */
    public List<Store> getStoreInfoOfCurrentPostion(@Param("postion") String postion);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 11:40
      * @Param: * @param null
      * @Return:
      */
    public  List<Store> getTopFiveStoreInfo(List<Integer> storeIdInfo);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据店铺名称查询店铺图片信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 19:02
      * @Param: * @param null
      * @Return:
      */
    public String getStoreImgByStoreName(@Param("storeName") String storeName);


     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据店铺名称获得店铺id
      * @Return:
      * @Exception:
      * @Date: 2021/3/5 21:58
      * @Param: * @param null
      * @Return:
      */
    public  int getStoreIdByOfCurrStoreName(@Param("store_Name")String store_Name);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据店铺拥有者姓名查询店铺id
      * @Return:
      * @Exception:
      * @Date: 2021/3/6 13:27
      * @Param: * @param null
      * @Return:
      */
    public int getStoreIdByStoreOwnerUserName(@Param("userName") String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件查询店铺信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 21:17
      * @Param: * @param null
      * @Return:
      */
    public Store getStoreInfoByStoreOwner(@Param("userName") String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据di查出店铺名称
      * @Return:
      * @Exception:
      * @Date: 2021/3/9 16:25
      * @Param: * @param null
      * @Return:
      */
    public String getStoreNameByStoreId(@Param("storeId") int storeId);
}
