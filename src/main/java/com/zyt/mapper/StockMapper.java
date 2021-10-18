package com.zyt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: StockMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 库存持久层
 * @Date: 18:33 2021/2/27
 * @Version: 1.0
 */
@Mapper
@Repository(value = "stockMapper")
public interface StockMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 判断当前商品是否有库存
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 19:15
      * @Param: * @param null
      * @Return:
      */
    public int selectStockOfCurrProdId(@Param("id") int id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询锁定量
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 19:25
      * @Param: * @param null
      * @Return:
      */
    public int selectStockCountsOfCurrProdId(@Param("id") int id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return: 
      * @Exception: 
      * @Date: 2021/2/27 19:48
      * @Param: * @param null
      * @Return: 
      */
    public int updateStockOfCurrProduct(@Param("id") int id,@Param("lockCount") Integer lockCount);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:解锁库存
      * @Return:
      * @Exception:
      * @Date: 2021/3/3 14:44
      * @Param: * @param null
      * @Return:
      */
    public void unLockStock(@Param("prod_Id") Integer prod_Id, @Param("LockCount") Integer LockCount);
}
