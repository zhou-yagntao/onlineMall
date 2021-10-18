package com.zyt.service.impl.memberService.impl;

import com.zyt.entity.MemberShip;
import com.zyt.entity.VipMember;
import com.zyt.mapper.MemberShipMapper;
import com.zyt.mapper.VipMemberMapper;
import com.zyt.service.memberService.ViPMemberShipManagerService;
import com.zyt.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: ViPMemberShipManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员信息管理服务实现层
 * @Date: 12:19 2021/2/8
 * @Version: 1.0
 */
@Service(value = "viPMemberShipManagerService")
public class ViPMemberShipManagerServiceImpl implements ViPMemberShipManagerService {

     @Autowired
     private MemberShipMapper memberShipMapper;

     @Autowired
     private VipMemberMapper vipMemberMapper;


    /**
     * @Method: GetAllVipMemberInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得会员信息
     * @Return: java.util.List<com.zyt.entity.MemberShip>
     * @Exception:
     * @Date: 2021/2/8 12:20
     * @Param: * @param currentPage
     * @param pageLimit
     * @Return: java.util.List<com.zyt.entity.MemberShip>
     */
    @Override
    public List<MemberShip> GetAllVipMemberInfos(String currentPage, String pageLimit) {
        //转化传入参数的数据类型
        int currentPages = Integer.parseInt(currentPage);
        int pageLimits = Integer.parseInt(pageLimit);
        //计算分页属性
        int start  = (currentPages - 1)*pageLimits;
        int end = pageLimits;
        //若查询到结果就返回结果，否则就返回空
        return this.memberShipMapper.getAllVipMemberInfos(start,end) != null ? this.memberShipMapper.getAllVipMemberInfos(start,end):null;
    }

    /**
     * @Method: GetAllVipMemberInfosCounts
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得会员数量
     * @Return: int
     * @Exception:
     * @Date: 2021/2/8 12:21
     * @Param: * @param
     * @Return: int
     */
    @Override
    public int GetAllVipMemberInfosCounts() {
        return this.memberShipMapper.getAllVipMemberInfosCounts() >0 ?this.memberShipMapper.getAllVipMemberInfosCounts():0;
    }

    /**
     * @Method: getVipMemeberInfosByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询会员信息
     * @Return: java.util.List<com.zyt.entity.MemberShip>
     * @Exception:
     * @Date: 2021/2/8 12:52
     * @Param: * @param start
     * @param end
     * @param memberName
     * @param registerDate
     * @Return: java.util.List<com.zyt.entity.MemberShip>
     */
    @Override
    public List<MemberShip> getVipMemeberInfosByItems(String currentPage, String pageLimit, String memberName, String registerDate) {
        //转化传入参数的数据类型
        int currentPages = Integer.parseInt(currentPage);
        int pageLimits = Integer.parseInt(pageLimit);
        //计算分页属性
        int start  = (currentPages - 1)*pageLimits;
        int end = pageLimits;
        try {
            return this.memberShipMapper.getVipMemeberInfosByItems(start,end,memberName, DateUtils.stringDate(registerDate,"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * @Method: getVipMemeberInfosCountsByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询会员信息数量
     * @Return: int
     * @Exception:
     * @Date: 2021/2/8 12:53
     * @Param: * @param memberName
     * @param registerDate
     * @Return: int
     */
    @Override
    public int getVipMemeberInfosCountsByItems(String memberName,String  registerDate) {
        try {
            return this.memberShipMapper.getVipMemeberInfosCountsByItems(memberName,DateUtils.stringDate(registerDate,"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }

    /**
     * @Method: getVipMemberInfoOfCurrMemeberName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:判断当前会员是否存在
     * @Return: com.zyt.entity.VipMember
     * @Exception:
     * @Date: 2021/3/5 15:13
     * @Param: * @param subStrBirth
     * @param memeberName
     * @Return: com.zyt.entity.VipMember
     */
    @Override
    public VipMember getVipMemberInfoOfCurrMemeberName(String subStrBirth, String memeberName) {

        return this.vipMemberMapper.getVipMemberInfoOfCurrMemeberName(subStrBirth,memeberName) != null ?
                this.vipMemberMapper.getVipMemberInfoOfCurrMemeberName(subStrBirth,memeberName):
                null;
    }
}
