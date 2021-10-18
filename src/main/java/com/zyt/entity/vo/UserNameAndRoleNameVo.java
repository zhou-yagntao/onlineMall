package com.zyt.entity.vo;

import java.io.Serializable;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: UserNameAndRoleNameVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户姓名+权限名vo
 * @Date: 15:43 2021/3/29
 * @Version: 1.0
 */
public class UserNameAndRoleNameVo implements Serializable {
    private String userName;
    private String roleName;

    public UserNameAndRoleNameVo(){}

    public UserNameAndRoleNameVo(String userName, String roleName) {
        this.userName = userName;
        this.roleName = roleName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
