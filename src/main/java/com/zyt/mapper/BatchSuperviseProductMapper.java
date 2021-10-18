package com.zyt.mapper;

import com.zyt.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: BatchSuperviseProductMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 批量管理商品持久层
 * @Date: 17:46 2021/1/27
 * @Version: 1.0
 */
@Mapper
@Repository(value = "batchSuperviseProductMapper")
public interface BatchSuperviseProductMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 批量添加商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/27 17:48
      * @Param: * @param null
      * @Return:
      */
      //public int batchAddProductInfos(Product product);
      public int batchAddProductInfos(List<Product> productList);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 批量删除商品编号
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:04
      * @Param: * @param null
      * @Return:
      */
    public  int  batchDeleteProductInfos(List<Integer> newListParams);
}
