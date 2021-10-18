package com.zyt.service.userService;

import com.zyt.entity.Result;
import com.zyt.entity.Users;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: UserInfoManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户注册模块服务层
 * @Date: 11:04 2021/2/9
 * @Version: 1.0
 */
public interface UserInfoManagerService {
    //查询当前手机号下是否已经有人进行了注册
    public List<Users> GetUserIsFinishedRegsiter(String phoneNum);

    //用户注册
    public int AddUserRegisterInfos(String username,String nickname,String password,String phone,String code);
}
