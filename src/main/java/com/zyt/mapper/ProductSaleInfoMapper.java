package com.zyt.mapper;

import com.zyt.entity.ProductSaleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: ProductSaleInfoMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品销售情况持久层
 * @Date: 10:42 2021/2/24
 * @Version: 1.0
 */
@Mapper
@Repository(value = "productSaleInfoMapper ")
public interface ProductSaleInfoMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/24 10:47
      * @Param: * @param null
      * @Return:
      */
    public List<ProductSaleInfo> getProductSaleCountByProdId(@Param("productId")int productId);

}
