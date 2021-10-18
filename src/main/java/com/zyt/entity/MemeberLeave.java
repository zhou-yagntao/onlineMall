package com.zyt.entity;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: MemeberLeave
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员等级表
 * @Date: 13:51 2021/2/21
 * @Version: 1.0
 */
public class MemeberLeave {
    private Integer lid;
    private String Lname;
    private Integer currGrouth;
    private Integer growthPoint;
    private Boolean defaultStatus;
    private Boolean priviledgeMemberPrice;
    private Boolean priviledgeBirthday;
    private String note;
    private Integer memberLeaveId;

    public MemeberLeave(){
        super();
    }

    public MemeberLeave(Integer lid, String lname, Integer currGrouth, Integer growthPoint, Boolean defaultStatus, Boolean priviledgeMemberPrice, Boolean priviledgeBirthday, String note, Integer memberLeaveId) {
        this.lid = lid;
        Lname = lname;
        this.currGrouth = currGrouth;
        this.growthPoint = growthPoint;
        this.defaultStatus = defaultStatus;
        this.priviledgeMemberPrice = priviledgeMemberPrice;
        this.priviledgeBirthday = priviledgeBirthday;
        this.note = note;
        this.memberLeaveId = memberLeaveId;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public Integer getCurrGrouth() {
        return currGrouth;
    }

    public void setCurrGrouth(Integer currGrouth) {
        this.currGrouth = currGrouth;
    }

    public Integer getGrowthPoint() {
        return growthPoint;
    }

    public void setGrowthPoint(Integer growthPoint) {
        this.growthPoint = growthPoint;
    }

    public Boolean getDefaultStatus() {
        return defaultStatus;
    }

    public void setDefaultStatus(Boolean defaultStatus) {
        this.defaultStatus = defaultStatus;
    }

    public Boolean getPriviledgeMemberPrice() {
        return priviledgeMemberPrice;
    }

    public void setPriviledgeMemberPrice(Boolean priviledgeMemberPrice) {
        this.priviledgeMemberPrice = priviledgeMemberPrice;
    }

    public Boolean getPriviledgeBirthday() {
        return priviledgeBirthday;
    }

    public void setPriviledgeBirthday(Boolean priviledgeBirthday) {
        this.priviledgeBirthday = priviledgeBirthday;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getMemberLeaveId() {
        return memberLeaveId;
    }

    public void setMemberLeaveId(Integer memberLeaveId) {
        this.memberLeaveId = memberLeaveId;
    }

    @Override
    public String toString() {
        return "MemeberLeave{" +
                "lid=" + lid +
                ", Lname='" + Lname + '\'' +
                ", currGrouth=" + currGrouth +
                ", growthPoint=" + growthPoint +
                ", defaultStatus=" + defaultStatus +
                ", priviledgeMemberPrice=" + priviledgeMemberPrice +
                ", priviledgeBirthday=" + priviledgeBirthday +
                ", note='" + note + '\'' +
                ", memberLeaveId=" + memberLeaveId +
                '}';
    }
}
