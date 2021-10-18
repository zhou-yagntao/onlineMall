package com.zyt.mapper;

import com.zyt.entity.Couple;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: CoupleMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 优惠券管理持久层
 * @Date: 19:13 2021/2/6
 * @Version: 1.0
 */
@Mapper
@Repository(value = "coupleMapper")
public interface CoupleMapper {

    public int  addCoupleInfo(Couple couple);

    public List<Couple> getAllCoupleInfos(@Param("storeId") int storeId,@Param("start") int start,@Param("end") int end);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得所有优惠券条数
     * @Return:
     * @Exception:
     * @Date: 2021/2/6 19:52
     * @Param: * @param null
     * @Return:
     */
    public  int getAllCoupleInfoCounts(@Param("storeId") int storeId);
}
