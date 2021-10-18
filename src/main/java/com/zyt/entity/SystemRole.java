package com.zyt.entity;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: SystemRole
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户系统权限
 * @Date: 11:40 2021/3/16
 * @Version: 1.0
 */
public class SystemRole {
    private Integer role_id;
    private String role_name;
    private String role_context;
    private Date role_create_time;
    private String role_desc;
    private Date role_update_time;

    public SystemRole(){super();}

    public SystemRole(Integer role_id, String role_name, String role_context, Date role_create_time, String role_desc, Date role_update_time) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_context = role_context;
        this.role_create_time = role_create_time;
        this.role_desc = role_desc;
        this.role_update_time = role_update_time;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_context() {
        return role_context;
    }

    public void setRole_context(String role_context) {
        this.role_context = role_context;
    }

    public Date getRole_create_time() {
        return role_create_time;
    }

    public void setRole_create_time(Date role_create_time) {
        this.role_create_time = role_create_time;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }

    public Date getRole_update_time() {
        return role_update_time;
    }

    public void setRole_update_time(Date role_update_time) {
        this.role_update_time = role_update_time;
    }


    @Override
    public String toString() {
        return "SystemRole{" +
                "role_id=" + role_id +
                ", role_name='" + role_name + '\'' +
                ", role_context='" + role_context + '\'' +
                ", role_create_time=" + role_create_time +
                ", role_desc='" + role_desc + '\'' +
                ", role_update_time=" + role_update_time +
                '}';
    }
}
