package com.zyt.mapper;

import com.zyt.entity.StoreProfit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: StoreProfitMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺收益持久层
 * @Date: 15:54 2021/3/4
 * @Version: 1.0
 */
@Mapper
@Repository(value = "storeProfitMapper")
public interface StoreProfitMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询当前的店铺是否已有收益信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 16:20
      * @Param: * @param null
      * @Return:
      */
    public StoreProfit getStoreProfitInfoOfCurrStoreId(@Param("StoreId") int StoreId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:表示进行添加数据
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 16:24
      * @Param: * @param null
      * @Return:
      */
    public void saveStoreProfitInfoOfCurrStoreId(@Param("storeProfit") StoreProfit storeProfit);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改店铺收益表
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 16:29
      * @Param: * @param null
      * @Return:
      */
    public void updateStoreProfitInfoOfCurrStoreId(@Param("storeProfit") StoreProfit storeProfit);
}
