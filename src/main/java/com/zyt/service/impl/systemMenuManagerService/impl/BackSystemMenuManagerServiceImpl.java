package com.zyt.service.impl.systemMenuManagerService.impl;

import com.zyt.entity.SystemMenu;
import com.zyt.entity.SystemUser;
import com.zyt.entity.vo.LoginSuccessVo;
import com.zyt.entity.vo.SystemMenuVo;
import com.zyt.entity.vo.UserNameAndRoleNameVo;
import com.zyt.mapper.SystemMenuMapper;
import com.zyt.mapper.SystemRoleMapper;
import com.zyt.service.systemMenuManagerService.BackSystemMenuManagerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.systemMenuManagerService.impl
 * @ClassName: BackSystemMenuManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 后台管理菜单模块实现层服务模块
 * @Date: 21:29 2021/4/2
 * @Version: 1.0
 */
@Service(value = "BackSystemMenuManagerService")
public class BackSystemMenuManagerServiceImpl implements BackSystemMenuManagerService {

    private Logger logger = LoggerFactory.getLogger(BackSystemMenuManagerServiceImpl.class);

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    /**
     * @Method: getSystemMeunInfoOfCurrUserRole
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询菜单信息并进行菜单封装
     * @Return: java.util.List<com.zyt.entity.vo.SystemMenuVo>
     * @Exception:
     * @Date: 2021/4/2 21:54
     * @Param: * @param loginSuccessVo
     * @Return: java.util.List<com.zyt.entity.vo.SystemMenuVo>
     */
    @Override
    public List<SystemMenuVo> getSystemMeunInfoOfCurrUserRole(SystemUser systemUser) {

        if (systemUser != null) {
            //根据用户角色去查当前用户当前角色下的菜单信息
            //Collection<GrantedAuthority> authorities = selfUserEntity.getAuthorities();
            //String authority = "";
            //查询当前用户的角色信息,用户名信息
            //SystemUser userNameAndRoleName = this.systemUserLoginMapper.getUserNameAndUserRoleName(authority);
            //logger.info("获得的角色信息和权限信息为:"+userNameAndRoleName);
            UserNameAndRoleNameVo userNameAndRoleNameVo = new UserNameAndRoleNameVo();
            userNameAndRoleNameVo.setUserName(systemUser.getUsername());
            userNameAndRoleNameVo.setRoleName(systemUser.getSystemRole().getRole_name());
            String authority =  systemUser.getSystemRole().getRole_name();
            int roleId = this.systemRoleMapper.getRoleIdByRoleName(authority);
            logger.info("获得权限id信息为:"+roleId);
            //获得所有的菜单类目信息
            List<SystemMenu> systemMenuLists = this.systemMenuMapper.getSystemMenuOfCurrUserByRoleId(roleId);
            //当我们的菜单信息不为空则执行以下代码
            if (systemMenuLists != null && systemMenuLists.size() > 0) {
                //获得所用的父级id
                List<SystemMenu> parentMenus = getParentMenu(systemMenuLists, 0);

                logger.info("父级菜单信息："+parentMenus.toString());
                //获得该父级id的二级菜单
                List<SystemMenuVo> systemMenuVoList = parentMenus.stream().map(parentMenu -> {
                    SystemMenuVo systemMenuVo = new SystemMenuVo();
                    systemMenuVo.setUserNameAndRoleNameVo(userNameAndRoleNameVo);
                    systemMenuVo.setMenu_id(parentMenu.getMenu_id());
                    systemMenuVo.setMenu_name(parentMenu.getMenu_name());
                    systemMenuVo.setMenu_father_id(parentMenu.getMenu_father_id());
                    systemMenuVo.setMenu_child_id(parentMenu.getMenu_child_id());
                    systemMenuVo.setMenu_icon(parentMenu.getMenu_icon());
                    systemMenuVo.setMenu_path(parentMenu.getMenu_path());
                    systemMenuVo.setPermission(parentMenu.getPermission());
                    systemMenuVo.setCreatetime(parentMenu.getCreatetime());
                    systemMenuVo.setUpdatetime(parentMenu.getUpdatetime());

                    if (parentMenu.getMenu_id() == 1){
                        systemMenuVo.setSystemMenuList(new ArrayList<>());
                    }else{
                        List<SystemMenu> sonMenus = getSonMenu(systemMenuLists, parentMenu.getMenu_id());
                        logger.info("---->"+sonMenus.size());
//                        for (int i = 0; i < sonMenu.size(); i++) {
//                            SystemMenu systemMenu = new SystemMenu();
//                            systemMenu.setMenu_id(sonMenu.get(i).getMenu_id());
//                            systemMenu.setMenu_name(sonMenu.get(i).getMenu_name());
//                            systemMenu.setMenu_father_id(sonMenu.get(i).getMenu_father_id());
//                            systemMenu.setMenu_child_id(sonMenu.get(i).getMenu_child_id());
//                            systemMenu.setMenu_icon(sonMenu.get(i).getMenu_icon());
//                            systemMenu.setMenu_path(sonMenu.get(i).getMenu_path());
//                            systemMenu.setPermission(sonMenu.get(i).getPermission());
//                            systemMenu.setCreatetime(sonMenu.get(i).getCreatetime());
//                            systemMenu.setUpdatetime(sonMenu.get(i).getUpdatetime());
//
//                            systemMenuVo.getSystemMenuList().add(systemMenu);
                        //(Arrays.asList(systemMenu));
//                        }
                        systemMenuVo.setSystemMenuList(sonMenus);
                    }
                    return systemMenuVo;
                }).collect(Collectors.toList());
                logger.info("封装的菜单信息为:"+systemMenuVoList.toString());
                return  systemMenuVoList;
            }
        }
        return  null;
    }

//            List<SystemMenuVo> systemMenus = userInfoAndRoleNameInfos.stream().map(userInfoAndRoleNameInfo -> {
//                securityUserVo.setCurrentUserInfo(userInfoAndRoleNameInfo);
//                String roleName = userInfoAndRoleNameInfo.getSystemRole().getRole_name();
//                if (roleName == null) {
//                    return null;
//                }
//                int roleId = this.systemRoleMapper.getRoleIdByRoleName(roleName);
//                //获得所有的菜单类目信息
//                List<SystemMenu> systemMenuLists = this.systemMenuMapper.getSystemMenuOfCurrUserByRoleId(roleId);
//                //当我们的菜单信息不为空则执行以下代码
//                if (systemMenuLists != null && systemMenuLists.size() > 0) {
//                    SystemMenu systemMenu = new SystemMenu();
//                    //获得所用的父级id
//                    List<SystemMenu> parentMenus = getParentMenu(systemMenuLists, 0);
//                    logger.info("父级菜单信息："+parentMenus.toString());
//                    //获得该父级id的二级菜单
//                    List<SystemMenu> sonMenuOfCurrParent = parentMenus.stream().map(parentMenu -> {
//                        BeanUtils.copyProperties(parentMenus, SystemMenuVo.class);
//                        List<SystemMenu> sonMenu = getSonMenu(systemMenuLists, parentMenu.getMenu_id());
//                        for (int i = 0; i < sonMenu.size(); i++) {
//                            systemMenu.setMenu_id(sonMenu.get(i).getMenu_id());
//                            systemMenu.setMenu_name(sonMenu.get(i).getMenu_name());
//                            systemMenu.setMenu_father_id(sonMenu.get(i).getMenu_father_id());
//                            systemMenu.setMenu_child_id(sonMenu.get(i).getMenu_child_id());
//                            systemMenu.setMenu_icon(sonMenu.get(i).getMenu_icon());
//                            systemMenu.setMenu_path(sonMenu.get(i).getMenu_path());
//                            systemMenu.setPermission(sonMenu.get(i).getPermission());
//                            systemMenu.setCreatetime(sonMenu.get(i).getCreatetime());
//                            systemMenu.setUpdatetime(sonMenu.get(i).getUpdatetime());
//                        }
//                        return systemMenu;
//                    }).collect(Collectors.toList());
//                    systemMenuVo.setSystemMenuList(sonMenuOfCurrParent);
//                }
//                return systemMenuVo;
//            }).collect(Collectors.toList());
//            securityUserVo.setPermissionList(Collections.singletonList(ChangeJsonTools.objToStringPretty(systemMenus)));
//            logger.info("获得的菜单信息为:"+securityUserVo);
//            return Collections.singletonList(systemMenuVo);
//        }else{
//            return  null;
//        }



    private List<SystemMenu>getParentMenu(List<SystemMenu> systemMenuLists,Integer parent_id){
        List<SystemMenu> collect = systemMenuLists.stream().filter(item -> item.getMenu_father_id().equals(parent_id)).collect(Collectors.toList());
        logger.info("父级菜单"+collect.toString());
        return  collect != null && collect.size() > 0 ? collect : null;
    }

    private List<SystemMenu> getSonMenu(List<SystemMenu> systemMenuLists, Integer menu_id) {
        //logger.info("传入的菜单栏信息："+systemMenuLists.toString()+"\t"+menu_id);
        List<SystemMenu> collect = systemMenuLists.stream().filter(item -> item.getMenu_father_id().equals(menu_id)).collect(Collectors.toList());
        logger.info("孩子菜单信息为:"+collect.toString());
        return  collect != null && collect.size() > 0 ? collect : null;
    }




}