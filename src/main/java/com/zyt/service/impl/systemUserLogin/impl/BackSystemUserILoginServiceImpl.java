package com.zyt.service.impl.systemUserLogin.impl;

import com.zyt.entity.SystemUser;
import com.zyt.entity.vo.LoginSuccessVo;
import com.zyt.entity.vo.SystemMenuVo;
import com.zyt.mapper.SystemUserLoginMapper;
import com.zyt.service.impl.systemMenuManagerService.impl.BackSystemMenuManagerServiceImpl;
import com.zyt.service.systemMenuManagerService.BackSystemMenuManagerService;
import com.zyt.service.systemRoleInfoService.SystemRoleManagerService;
import com.zyt.service.systemUserLogin.BackSystemUserILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.systemUserLogin.impl
 * @ClassName: BackSystemUserILoginServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 后台用户登录服务层
 * @Date: 20:55 2021/4/2
 * @Version: 1.0
 */
@Service(value = "BackSystemUserILoginService")
public class BackSystemUserILoginServiceImpl implements BackSystemUserILoginService {

    private Logger logger  = LoggerFactory.getLogger(BackSystemMenuManagerServiceImpl.class);

    @Autowired
    private SystemUserLoginMapper systemUserLoginMapper;

    @Autowired
    private SystemRoleManagerService systemRoleManagerService;

    @Autowired
    private BackSystemMenuManagerService backSystemMenuManagerService;

    /**
     * @Method: checkIsExistsOfCurrUserByUserName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前用户是否存在
     * @Return: com.zyt.entity.SystemUser
     * @Exception:
     * @Date: 2021/4/2 20:59
     * @Param: * @param
     * @Return: com.zyt.entity.SystemUser
     */
    @Override
    public  LoginSuccessVo checkIsExistsOfCurrUserByUserName(String userName) {
        SystemUser systemUser = this.systemUserLoginMapper.checkIsExistsOfCurrUserByUserName(userName);
        if (systemUser == null){return  null;}
        int userId = systemUser.getUser_id();
        List<String> userInfoLists = this.systemRoleManagerService.getUserRolePermissionOfCurrUserByUserId(userId);
        if (userInfoLists == null && userInfoLists.size() == 0){
            return null;
        }else{
            LoginSuccessVo loginSuccessVo = new LoginSuccessVo();
            BeanUtils.copyProperties(systemUser,loginSuccessVo);
            loginSuccessVo.setRole(userInfoLists);
            return loginSuccessVo;
        }
    }

    /**
     * @Method: getSystemMeunInfoOfCurrUserRole
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前角色的菜单信息
     * @Return: java.util.List<com.zyt.entity.vo.SystemMenuVo>
     * @Exception:
     * @Date: 2021/4/2 21:48
     * @Param: * @param loginSuccessVo
     * @Return: java.util.List<com.zyt.entity.vo.SystemMenuVo>
     */
    @Override
    public List<SystemMenuVo> getSystemMeunInfoOfCurrUserRole(String  role) {
        //根据角色名称查询角色信息以及用户信息
        if (role == null){return  null;}
        //获取当前角色下的用户信息和角色信息
        logger.info("获得的参数信息为："+role);
        SystemUser systemUser = this.systemRoleManagerService.getUserInfoAndRoleInfoByRoleName(role);
        logger.info("=========>"+systemUser.toString());
        List<SystemMenuVo> systemMeunInfoOfCurrUserRole = this.backSystemMenuManagerService.getSystemMeunInfoOfCurrUserRole(systemUser);
        if (systemMeunInfoOfCurrUserRole == null){return  null;}
        return systemMeunInfoOfCurrUserRole;
    }


}
