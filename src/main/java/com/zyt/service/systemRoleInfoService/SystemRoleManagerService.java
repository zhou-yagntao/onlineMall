package com.zyt.service.systemRoleInfoService;

import com.zyt.entity.SystemUser;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.systemRoleInfoService
 * @ClassName: SystemRoleManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户角色管理服务层
 * @Date: 21:06 2021/4/2
 * @Version: 1.0
 */
public interface SystemRoleManagerService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获取当前用户的角色信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/2 21:08
      * @Param: * @param null
      * @Return:
      */
    public List<String> getUserRolePermissionOfCurrUserByUserId(Integer user_id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获取当前用户下用户信息了角色信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/9 22:12
      * @Param: * @param null
      * @Return:
      */
    public SystemUser getUserInfoAndRoleInfoByRoleName(String role);
}
