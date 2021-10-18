package com.zyt.mapper;

import com.zyt.entity.SystemMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: SystemMenuMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 菜单模块
 * @Date: 22:44 2021/3/16
 * @Version: 1.0
 */
@Mapper
@Repository(value = "systemMenuMapper")
public interface SystemMenuMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获取当前角色id对应的菜单类目信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/17 13:11
      * @Param: * @param null
      * @Return:
      */
    public List<SystemMenu> getSystemMenuOfCurrUserByRoleId(@Param("roleId") int roleId);



}
