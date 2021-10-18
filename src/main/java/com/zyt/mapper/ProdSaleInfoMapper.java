package com.zyt.mapper;

import com.zyt.entity.ProdSaleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: ProdSaleInfoMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品销售持久层
 * @Date: 12:01 2021/2/16
 * @Version: 1.0
 */
@Mapper
@Repository(value = "prodSaleInfoMapper")
public interface ProdSaleInfoMapper {

    public List<ProdSaleInfo> getProdSaleInfo();


    public int getProdSaleInfoProdId(int topNineProdInfos);
}
