package com.zyt.mapper;

import com.zyt.entity.Store;
import com.zyt.entity.StoreHits;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: StoreHitsMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺浏览量持久层
 * @Date: 10:38 2021/2/10
 * @Version: 1.0
 */
@Mapper
@Repository(value = "storeHitsMapper")
public interface StoreHitsMapper {

    //获得商品点击量
    @Select("select * from storeClick")
    public List<StoreHits> getStoreHitsInfos();

    //获得前五的店铺点击店铺编号
    @Select("select sstoreId from storeClick where 1=1 and sotreHits = #{hits}")
    public Integer getStoreIdByHits(int hits);

    @Select("select sotreHits from storeClick where 1=1 and sstoreId = #{storeId}")
    public int getStoreHitsByStoreId(@Param("storeId") int storeId);

}
