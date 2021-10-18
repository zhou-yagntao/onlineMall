package com.zyt.service.memberService.addMemberInfoByWeiBo;

import com.zyt.entity.SocailUser;
import com.zyt.entity.VipMember;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.memberService.addMemberInfoByWeiBo
 * @ClassName: AddMemberInfoFromSocialUserService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 添加用户会员信息
 * @Date: 14:36 2021/2/21
 * @Version: 1.0
 */
public interface AddMemberInfoFromSocialUserService {


    public VipMember addMemberInfoFromWeibo(SocailUser socailUser);



}
