package com.zyt.entity;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Addreess
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户收货地址管理
 * @Date: 11:34 2021/2/25
 * @Version: 1.0
 */
public class Addreess {

    private Integer address_id;
    private Integer user_id;
    private String user_name;
    private String user_area;
    private String add_detail;
    private String contact_num;
    private Boolean isCheck = false;

    public Addreess(){super();}

    public Addreess(Integer address_id, Integer user_id, String user_name, String user_area, String add_detail, String contact_num, Boolean isCheck) {
        this.address_id = address_id;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_area = user_area;
        this.add_detail = add_detail;
        this.contact_num = contact_num;
        this.isCheck = isCheck;
    }

    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer address_id) {
        this.address_id = address_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_area() {
        return user_area;
    }

    public void setUser_area(String user_area) {
        this.user_area = user_area;
    }

    public String getAdd_detail() {
        return add_detail;
    }

    public void setAdd_detail(String add_detail) {
        this.add_detail = add_detail;
    }

    public String getContact_num() {
        return contact_num;
    }

    public void setContact_num(String contact_num) {
        this.contact_num = contact_num;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }

    @Override
    public String toString() {
        return "Addreess{" +
                "address_id=" + address_id +
                ", user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_area='" + user_area + '\'' +
                ", add_detail='" + add_detail + '\'' +
                ", contact_num='" + contact_num + '\'' +
                ", isCheck=" + isCheck +
                '}';
    }
}
