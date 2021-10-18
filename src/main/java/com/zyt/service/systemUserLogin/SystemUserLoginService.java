package com.zyt.service.systemUserLogin;


import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.systemUserLogin
 * @ClassName: SystemUserLoginService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 后台系统用户登录服务层
 * @Date: 12:39 2021/3/16
 * @Version: 1.0
 */
public interface SystemUserLoginService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询当前用户是否存在
      * @Return:
      * @Exception:
      * @Date: 2021/3/16 12:44
      * @Param: * @param null
      * @Return:
      */
    //public SelfUserEntity checkIsExistsOfCurrUserByUserName(String userName);


     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户的权限类别
      * @Return:
      * @Exception:
      * @Date: 2021/3/16 16:33
      * @Param: * @param null
      * @Return:
      */
     public List<String> getUserRolePermissionOfCurrUserByUserId(Integer user_id);


      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:获得当前用户角色下的菜单信息
       * @Return:
       * @Exception:
       * @Date: 2021/3/29 13:37b
       * @Param: * @param null
       * @Return:
       */
     //public List<SystemMenuVo> getSystemMeunInfoOfCurrUserRole(SelfUserEntity selfUserEntity);
}
