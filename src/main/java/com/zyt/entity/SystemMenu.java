package com.zyt.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: SystemMenu
 * @Author: zhou_yangtao@yeah.net
 * @Description: 系统左侧菜单栏实体类
 * @Date: 11:10 2021/3/16
 * @Version: 1.0
 */
public class SystemMenu  implements Serializable {
    private Integer menu_id;

    private String menu_name;

    private String menu_icon;

    private String menu_path;

    private Integer menu_father_id;

    private Integer menu_child_id;

    private String permission;

    private Date createtime;

    private Date updatetime;

    public SystemMenu(){super();}

    public SystemMenu(Integer menu_id, String menu_name, String menu_icon, String menu_path, Integer menu_father_id, Integer menu_child_id, String permission, Date createtime, Date updatetime) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_icon = menu_icon;
        this.menu_path = menu_path;
        this.menu_father_id = menu_father_id;
        this.menu_child_id = menu_child_id;
        this.permission = permission;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public void setMenu_name(String menu_name) {
        this.menu_name = menu_name;
    }

    public String getMenu_icon() {
        return menu_icon;
    }

    public void setMenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }

    public String getMenu_path() {
        return menu_path;
    }

    public void setMenu_path(String menu_path) {
        this.menu_path = menu_path;
    }

    public Integer getMenu_father_id() {
        return menu_father_id;
    }

    public void setMenu_father_id(Integer menu_father_id) {
        this.menu_father_id = menu_father_id;
    }

    public Integer getMenu_child_id() {
        return menu_child_id;
    }

    public void setMenu_child_id(Integer menu_child_id) {
        this.menu_child_id = menu_child_id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "SystemMenu{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_icon='" + menu_icon + '\'' +
                ", menu_path='" + menu_path + '\'' +
                ", menu_father_id=" + menu_father_id +
                ", menu_child_id=" + menu_child_id +
                ", permission='" + permission + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                '}';
    }
}
