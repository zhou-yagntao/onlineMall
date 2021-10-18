package com.zyt.mapper;

import com.zyt.entity.MemberShip;
import com.zyt.entity.VipMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: MemberShipMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员信息管理持久层
 * @Date: 10:05 2021/2/8
 * @Version: 1.0
 */
@Mapper
@Repository(value = "memberShipMapper")
public interface MemberShipMapper {

    //获得所有会员信息数据
    public List<MemberShip> getAllVipMemberInfos(@Param("start")int start,@Param("end")int end);

    //查询会员数量
    public int getAllVipMemberInfosCounts();

    //根据条件查询信息
    public List<MemberShip> getVipMemeberInfosByItems(@Param("start")int start,@Param("end")int end,@Param("memberName")String memberName,@Param("registerDate") Date registerDate);

    //根据条件查询条数
    public int  getVipMemeberInfosCountsByItems(@Param("memberName")String memberName,@Param("registerDate")Date registerDate);

}
