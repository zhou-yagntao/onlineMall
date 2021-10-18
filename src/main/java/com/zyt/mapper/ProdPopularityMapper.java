package com.zyt.mapper;

import com.zyt.entity.ProdPopularity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: ProdPopularityMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品热度表持久层
 * @Date: 14:57 2021/2/5
 * @Version: 1.0
 */
@Mapper
@Repository(value = "prodPopularityMapper")
public interface ProdPopularityMapper {

    public ProdPopularity getProdPopularityInfoByProdId(@Param("prodId") int prodId);

}
