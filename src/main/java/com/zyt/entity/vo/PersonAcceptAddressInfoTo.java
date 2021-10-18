package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: PersonAcceptAddressInfoTo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装个人收货地址信息
 * @Date: 13:28 2021/2/25
 * @Version: 1.0
 */
public class PersonAcceptAddressInfoTo {

    private String addressDetails;

    private String accpter;

    private String contactNum;

    public PersonAcceptAddressInfoTo(){
        super();
    }

    public PersonAcceptAddressInfoTo(String addressDetails, String accpter, String contactNum) {
        this.addressDetails = addressDetails;
        this.accpter = accpter;
        this.contactNum = contactNum;
    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getAccpter() {
        return accpter;
    }

    public void setAccpter(String accpter) {
        this.accpter = accpter;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}
