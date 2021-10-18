package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: SystemRoleMenu
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户角色菜单关联表
 * @Date: 11:43 2021/3/16
 * @Version: 1.0
 */
public class SystemRoleMenu {
     private Integer id;

     private Integer role_id;

     private Integer menu_id;

     public SystemRoleMenu(){super();}

    public SystemRoleMenu(Integer id, Integer role_id, Integer menu_id) {
        this.id = id;
        this.role_id = role_id;
        this.menu_id = menu_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    @Override
    public String toString() {
        return "SystemRoleMenu{" +
                "id=" + id +
                ", role_id=" + role_id +
                ", menu_id=" + menu_id +
                '}';
    }
}
