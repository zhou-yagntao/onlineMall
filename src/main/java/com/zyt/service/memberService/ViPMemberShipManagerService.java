package com.zyt.service.memberService;

import com.zyt.entity.MemberShip;
import com.zyt.entity.VipMember;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: ViPMemberShipManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员信息管理服务层
 * @Date: 12:18 2021/2/8
 * @Version: 1.0
 */
public interface ViPMemberShipManagerService {

    //获得所有会员信息数据
    public List<MemberShip> GetAllVipMemberInfos(String currentPage, String pageLimit);

    //查询会员数量
    public int GetAllVipMemberInfosCounts();

    //根据条件查询信息
    public List<MemberShip> getVipMemeberInfosByItems(String currentPage, String pageLimit,String memberName,String registerDate);

    //根据条件查询条数
    public int  getVipMemeberInfosCountsByItems(String memberName,String registerDate);

    //获得当前会员信息是否存在
    public VipMember getVipMemberInfoOfCurrMemeberName(String subStrBirth, String memeberName);
}

