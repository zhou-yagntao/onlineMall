package com.zyt.mapper;

import com.zyt.entity.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: SystemUserLoginMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 后台管理系统用户登录持久层模块
 * @Date: 15:06 2021/3/16
 * @Version: 1.0
 */
@Mapper
@Repository(value = "systemUserLoginMapper")
public interface SystemUserLoginMapper {
     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据用户名查询用户信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/16 15:11
      * @Param: * @param null
      * @Return:
      */
    public SystemUser checkIsExistsOfCurrUserByUserName(@Param("userName") String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据用户名查询当前用户信息以及权限列表信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/16 16:36
      * @Param: * @param null
      * @Return:
      */

     public List<String> getUserRolePermissionOfCurrUserByUserId(@Param("user_id") Integer user_id);


    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获取用户名和用户角色信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/29 13:57
     * @Param: * @param null
     * @Return:
     */
    public SystemUser getUserNameAndUserRoleName(String authority);
}
