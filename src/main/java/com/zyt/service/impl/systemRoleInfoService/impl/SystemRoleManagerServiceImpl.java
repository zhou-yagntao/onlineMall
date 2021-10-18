package com.zyt.service.impl.systemRoleInfoService.impl;

import com.zyt.entity.SystemUser;
import com.zyt.mapper.SystemRoleMapper;
import com.zyt.mapper.SystemUserLoginMapper;
import com.zyt.service.systemRoleInfoService.SystemRoleManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.systemRoleInfoService.impl
 * @ClassName: SystemRoleManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户角色管理服务层实现模块
 * @Date: 21:07 2021/4/2
 * @Version: 1.0
 */
@Service(value = "SystemRoleManagerService")
public class SystemRoleManagerServiceImpl implements SystemRoleManagerService {
     private Logger logger = LoggerFactory.getLogger(SystemRoleManagerServiceImpl.class);

     @Autowired
     private SystemUserLoginMapper systemUserLoginMapper;

    /**
     * @Method: getUserRolePermissionOfCurrUserByUserId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前用户的 角色信息
     * @Return: java.util.List<java.lang.String>
     * @Exception:
     * @Date: 2021/4/2 21:09
     * @Param: * @param user_id
     * @Return: java.util.List<java.lang.String>
     */
    @Override
    public List<String> getUserRolePermissionOfCurrUserByUserId(Integer user_id) {
        if (user_id == null){return  null;}
        List<String> roleInfos = this.systemUserLoginMapper.getUserRolePermissionOfCurrUserByUserId(user_id);
        return roleInfos != null ? roleInfos : null;

    }

    /**
     * @Method: getUserInfoAndRoleInfoByRoleName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获取当前用户的角色信息
     * @Return: com.zyt.entity.SystemUser
     * @Exception:
     * @Date: 2021/4/9 22:17
     * @Param: * @param role
     * @Return: com.zyt.entity.SystemUser
     */
    @Override
    public SystemUser getUserInfoAndRoleInfoByRoleName(String role) {
        SystemUser systemUser = this.systemUserLoginMapper.getUserNameAndUserRoleName(role);
        return  systemUser != null ? systemUser : null;
    }
}
