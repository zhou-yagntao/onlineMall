package com.zyt.mapper;

import com.zyt.entity.Product;
import com.zyt.entity.ProductClick;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: ProductClickMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品点击持久层
 * @Date: 13:05 2021/4/17
 * @Version: 1.0
 */
@Mapper
@Repository
public interface ProductClickMapper {
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得商品点击量
      * @Return:
      * @Exception:
      * @Date: 2021/4/17 13:22
      * @Param: * @param null
      * @Return:
      */
    public  int getProductClick(@Param("prodId") Integer prodId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品点击数量
      * @Return:
      * @Exception:
      * @Date: 2021/4/17 18:21
      * @Param: * @param null
      * @Return:
      */
     public void updateProductClickCounts(@Param("productId") Integer productId);

       /**
        * @Method: 
        * @Author: zhou_yangtao@yeah.net
        * @Version  1.0
        * @Description:
        * @Return: 
        * @Exception: 
        * @Date: 2021/4/20 21:28
        * @Param: * @param null
        * @Return: 
        */ 
     public List<ProductClick> getUserActiveInfo();

      /**
       * @Method: 
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:
       * @Return: 
       * @Exception: 
       * @Date: 2021/4/27 15:38
       * @Param: * @param null
       * @Return: 
       */
     public List<Integer> getProductIdByUserId(@Param("similarityBetweenUsers") List<Long> similarityBetweenUsers);

}
