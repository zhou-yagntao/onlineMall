package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: SystemUserRoleMenu
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户角色关联实体类
 * @Date: 11:47 2021/3/16
 * @Version: 1.0
 */
public class SystemUserRoleMenu {

    private Integer id;

    private Integer user_id;

    private Integer role_id;

    public SystemUserRoleMenu(){super();}

    public SystemUserRoleMenu(Integer id, Integer user_id, Integer role_id) {
        this.id = id;
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "SystemUserRoleMenu{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", role_id=" + role_id +
                '}';
    }
}
