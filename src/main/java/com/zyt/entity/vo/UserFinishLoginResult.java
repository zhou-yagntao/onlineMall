package com.zyt.entity.vo;

import javax.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: UserFinishLoginResult
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装用户完成登录后的结果(普通方式用户登录 + 社交账号登录)
 * @Date: 20:59 2021/2/21
 * @Version: 1.0
 */
public class UserFinishLoginResult implements Serializable {

    /*
    *设置用户唯一id
    * */
    private String uid;

    /*
    * 用户名
    * */
    private String userName;
    /*
     * 头像
     * */
    private String userHeader;
    /*
     * 等级名称
     * */
    private String userMemberLname;
    /*
     * 成长值占比
     * */
    private Double currGrouthproportion;





    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHeader() {
        return userHeader;
    }

    public void setUserHeader(String userHeader) {
        this.userHeader = userHeader;
    }

    public String getUserMemberLname() {
        return userMemberLname;
    }

    public void setUserMemberLname(String userMemberLname) {
        this.userMemberLname = userMemberLname;
    }

    public Double getCurrGrouthproportion() {
        return currGrouthproportion;
    }

    public void setCurrGrouthproportion(Double currGrouthproportion) {
        this.currGrouthproportion = currGrouthproportion;
    }

    @Override
    public String toString() {
        return "UserFinishLoginResult{" +
                "userName='" + userName + '\'' +
                ", userHeader='" + userHeader + '\'' +
                ", userMemberLname='" + userMemberLname + '\'' +
                ", currGrouthproportion=" + currGrouthproportion +
                '}';
    }
}
