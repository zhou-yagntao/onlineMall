package com.zyt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: SystemRoleMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 22:45 2021/3/16
 * @Version: 1.0
 */
@Mapper
@Repository(value = "systemRoleMapper")
public interface SystemRoleMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据角色名称获得角色id
      * @Return:
      * @Exception:
      * @Date: 2021/3/17 13:08
      * @Param: * @param null
      * @Return:
      */
    public int getRoleIdByRoleName(@Param("roleName")String roleName);

}
