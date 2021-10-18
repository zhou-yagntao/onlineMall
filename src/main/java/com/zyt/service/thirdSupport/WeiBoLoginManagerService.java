package com.zyt.service.thirdSupport;

import com.zyt.entity.SocailUser;
import com.zyt.entity.VipMember;
import com.zyt.entity.vo.UserFinishLoginResult;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.thirdSupport
 * @ClassName: WeiBoLoginManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 微博登录验证服务层
 * @Date: 12:56 2021/2/21
 * @Version: 1.0
 */
public interface WeiBoLoginManagerService {

    public UserFinishLoginResult WeiBoAuthorize(String code) ;
}
