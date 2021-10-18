package com.zyt.entity;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: MemberShip
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员信息表
 * @Date: 10:02 2021/2/8
 * @Version: 1.0
 */
public class MemberShip {
    private Integer MshipId;
    private String RealName;
    private String PhoneNumer;
    private String MemberSex;
    private String IdentityCard;
    private String VipRegName;
    private Date RegisterTime;
    private String VipGrade;
    private Integer GrouthValue;
    private Boolean ProhibitReducte;

    public MemberShip(){super();}

    public MemberShip(Integer mshipId, String realName, String phoneNumer, String memberSex, String identityCard, String vipRegName, Date registerTime, String vipGrade, Integer grouthValue, Boolean prohibitReducte) {
        MshipId = mshipId;
        RealName = realName;
        PhoneNumer = phoneNumer;
        MemberSex = memberSex;
        IdentityCard = identityCard;
        VipRegName = vipRegName;
        RegisterTime = registerTime;
        VipGrade = vipGrade;
        GrouthValue = grouthValue;
        ProhibitReducte = prohibitReducte;
    }

    public Integer getMshipId() {
        return MshipId;
    }

    public void setMshipId(Integer mshipId) {
        MshipId = mshipId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getPhoneNumer() {
        return PhoneNumer;
    }

    public void setPhoneNumer(String phoneNumer) {
        PhoneNumer = phoneNumer;
    }

    public String getMemberSex() {
        return MemberSex;
    }

    public void setMemberSex(String memberSex) {
        MemberSex = memberSex;
    }

    public String getIdentityCard() {
        return IdentityCard;
    }

    public void setIdentityCard(String identityCard) {
        IdentityCard = identityCard;
    }

    public String getVipRegName() {
        return VipRegName;
    }

    public void setVipRegName(String vipRegName) {
        VipRegName = vipRegName;
    }

    public Date getRegisterTime() {
        return RegisterTime;
    }

    public void setRegisterTime(Date registerTime) {
        RegisterTime = registerTime;
    }

    public String getVipGrade() {
        return VipGrade;
    }

    public void setVipGrade(String vipGrade) {
        VipGrade = vipGrade;
    }

    public Integer getGrouthValue() {
        return GrouthValue;
    }

    public void setGrouthValue(Integer grouthValue) {
        GrouthValue = grouthValue;
    }

    public Boolean getProhibitReducte() {
        return ProhibitReducte;
    }

    public void setProhibitReducte(Boolean prohibitReducte) {
        ProhibitReducte = prohibitReducte;
    }
}
