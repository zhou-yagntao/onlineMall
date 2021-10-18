package com.zyt.entity.vo;

import com.zyt.entity.SystemMenu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: SystemMenuVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 系统菜单信息
 * @Date: 13:56 2021/3/17
 * @Version: 1.0
 */
public class SystemMenuVo  implements Serializable {

    private Integer menu_id;

    private String menu_name;

    private String menu_icon;

    private String menu_path;

    private Integer menu_father_id;

    private Integer menu_child_id;

    private String permission;

    private Date createtime;

    private Date updatetime;

    private List<SystemMenu> systemMenuList;

    private UserNameAndRoleNameVo userNameAndRoleNameVo;

    public SystemMenuVo(){
        super();
    }


    public SystemMenuVo(Integer menu_id, String menu_name, String menu_icon, String menu_path, Integer menu_father_id, Integer menu_child_id, String permission, Date createtime, Date updatetime, List<SystemMenu> systemMenuList, UserNameAndRoleNameVo userNameAndRoleNameVo) {
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_icon = menu_icon;
        this.menu_path = menu_path;
        this.menu_father_id = menu_father_id;
        this.menu_child_id = menu_child_id;
        this.permission = permission;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.systemMenuList = systemMenuList;
        this.userNameAndRoleNameVo = userNameAndRoleNameVo;
    }

    public List<SystemMenu> getSystemMenuList() {
        return systemMenuList;
    }

    public void setSystemMenuList(List<SystemMenu> systemMenuList) {
        this.systemMenuList = systemMenuList;
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


    public UserNameAndRoleNameVo getUserNameAndRoleNameVo() {
        return userNameAndRoleNameVo;
    }

    public void setUserNameAndRoleNameVo(UserNameAndRoleNameVo userNameAndRoleNameVo) {
        this.userNameAndRoleNameVo = userNameAndRoleNameVo;
    }

    @Override
    public String toString() {
        return "SystemMenuVo{" +
                "menu_id=" + menu_id +
                ", menu_name='" + menu_name + '\'' +
                ", menu_icon='" + menu_icon + '\'' +
                ", menu_path='" + menu_path + '\'' +
                ", menu_father_id=" + menu_father_id +
                ", menu_child_id=" + menu_child_id +
                ", permission='" + permission + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", systemMenuList=" + systemMenuList +
                ", userNameAndRoleNameVo=" + userNameAndRoleNameVo +
                '}';
    }
}
