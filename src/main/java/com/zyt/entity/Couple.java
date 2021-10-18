package com.zyt.entity;

import java.util.Date;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Couple
 * @Author: zhou_yangtao@yeah.net
 * @Description: 优惠券实体类信息
 * @Date: 10:58 2021/2/6
 * @Version: 1.0
 */
public class Couple {
       private Integer Cid;
       private String Cname;
       private String CoupleType;
       private String CoupleImg;
       private Integer CoupleStoreId;
       private Store store;
       private Integer CoupleRelMaxCounts;
       private Integer CoupleChangeMoney;
       private Integer PerLimitedCouple;
       private String CoupleUseScenarios;
       private boolean IsCrossStoreUse;
       private Date CoupleObtainStart;
       private Date CoupleObtainEnd;
       private Date CoupleUseStart;
       private Date CoupleUseEnd;
       private Integer CoupleCollected;
       private Integer CoupleSurplus;
       private String CoupleUseCondition;
       private Integer OrderAmount;
       private  String CustomerType;

       public Couple(){super();}

    public Couple(Integer cid, String cname, String coupleType, String coupleImg, Integer coupleStoreId, Store store, Integer coupleRelMaxCounts, Integer coupleChangeMoney, Integer perLimitedCouple, String coupleUseScenarios, boolean isCrossStoreUse, Date coupleObtainStart, Date coupleObtainEnd, Date coupleUseStart, Date coupleUseEnd, Integer coupleCollected, Integer coupleSurplus, String coupleUseCondition, Integer orderAmount, String customerType) {
        Cid = cid;
        Cname = cname;
        CoupleType = coupleType;
        CoupleImg = coupleImg;
        CoupleStoreId = coupleStoreId;
        this.store = store;
        CoupleRelMaxCounts = coupleRelMaxCounts;
        CoupleChangeMoney = coupleChangeMoney;
        PerLimitedCouple = perLimitedCouple;
        CoupleUseScenarios = coupleUseScenarios;
        IsCrossStoreUse = isCrossStoreUse;
        CoupleObtainStart = coupleObtainStart;
        CoupleObtainEnd = coupleObtainEnd;
        CoupleUseStart = coupleUseStart;
        CoupleUseEnd = coupleUseEnd;
        CoupleCollected = coupleCollected;
        CoupleSurplus = coupleSurplus;
        CoupleUseCondition = coupleUseCondition;
        OrderAmount = orderAmount;
        CustomerType = customerType;
    }

    public Integer getCid() {
        return Cid;
    }

    public void setCid(Integer cid) {
        Cid = cid;
    }

    public Integer getCoupleStoreId() {
        return CoupleStoreId;
    }

    public void setCoupleStoreId(Integer coupleStoreId) {
        CoupleStoreId = coupleStoreId;
    }

    public String getCname() {
        return Cname;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public String getCoupleType() {
        return CoupleType;
    }

    public void setCoupleType(String coupleType) {
        CoupleType = coupleType;
    }

    public String getCoupleImg() {
        return CoupleImg;
    }

    public void setCoupleImg(String coupleImg) {
        CoupleImg = coupleImg;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getCoupleRelMaxCounts() {
        return CoupleRelMaxCounts;
    }

    public void setCoupleRelMaxCounts(Integer coupleRelMaxCounts) {
        CoupleRelMaxCounts = coupleRelMaxCounts;
    }

    public Integer getCoupleChangeMoney() {
        return CoupleChangeMoney;
    }

    public void setCoupleChangeMoney(Integer coupleChangeMoney) {
        CoupleChangeMoney = coupleChangeMoney;
    }

    public Integer getPerLimitedCouple() {
        return PerLimitedCouple;
    }

    public void setPerLimitedCouple(Integer perLimitedCouple) {
        PerLimitedCouple = perLimitedCouple;
    }

    public String getCoupleUseScenarios() {
        return CoupleUseScenarios;
    }

    public void setCoupleUseScenarios(String coupleUseScenarios) {
        CoupleUseScenarios = coupleUseScenarios;
    }

    public boolean isCrossStoreUse() {
        return IsCrossStoreUse;
    }

    public void setCrossStoreUse(boolean crossStoreUse) {
        IsCrossStoreUse = crossStoreUse;
    }

    public Date getCoupleObtainStart() {
        return CoupleObtainStart;
    }

    public void setCoupleObtainStart(Date coupleObtainStart) {
        CoupleObtainStart = coupleObtainStart;
    }

    public Date getCoupleObtainEnd() {
        return CoupleObtainEnd;
    }

    public void setCoupleObtainEnd(Date coupleObtainEnd) {
        CoupleObtainEnd = coupleObtainEnd;
    }

    public Date getCoupleUseStart() {
        return CoupleUseStart;
    }

    public void setCoupleUseStart(Date coupleUseStart) {
        CoupleUseStart = coupleUseStart;
    }

    public Date getCoupleUseEnd() {
        return CoupleUseEnd;
    }

    public void setCoupleUseEnd(Date coupleUseEnd) {
        CoupleUseEnd = coupleUseEnd;
    }

    public Integer getCoupleCollected() {
        return CoupleCollected;
    }

    public void setCoupleCollected(Integer coupleCollected) {
        CoupleCollected = coupleCollected;
    }

    public Integer getCoupleSurplus() {
        return CoupleSurplus;
    }

    public void setCoupleSurplus(Integer coupleSurplus) {
        CoupleSurplus = coupleSurplus;
    }

    public String getCoupleUseCondition() {
        return CoupleUseCondition;
    }

    public void setCoupleUseCondition(String coupleUseCondition) {
        CoupleUseCondition = coupleUseCondition;
    }

    public Integer getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        OrderAmount = orderAmount;
    }

    public String getCustomerType() {
        return CustomerType;
    }

    public void setCustomerType(String customerType) {
        CustomerType = customerType;
    }

    @Override
    public String toString() {
        return "Couple{" +
                "Cid=" + Cid +
                ", Cname='" + Cname + '\'' +
                ", CoupleType='" + CoupleType + '\'' +
                ", CoupleImg='" + CoupleImg + '\'' +
                ", store=" + store +
                ", CoupleRelMaxCounts=" + CoupleRelMaxCounts +
                ", CoupleChangeMoney=" + CoupleChangeMoney +
                ", PerLimitedCouple=" + PerLimitedCouple +
                ", CoupleUseScenarios='" + CoupleUseScenarios + '\'' +
                ", IsCrossStoreUse=" + IsCrossStoreUse +
                ", CoupleObtainStart=" + CoupleObtainStart +
                ", CoupleObtainEnd=" + CoupleObtainEnd +
                ", CoupleUseStart=" + CoupleUseStart +
                ", CoupleUseEnd=" + CoupleUseEnd +
                ", CoupleCollected=" + CoupleCollected +
                ", CoupleSurplus=" + CoupleSurplus +
                ", CoupleUseCondition='" + CoupleUseCondition + '\'' +
                ", OrderAmount=" + OrderAmount +
                ", CustomerType='" + CustomerType + '\'' +
                '}';
    }
}
