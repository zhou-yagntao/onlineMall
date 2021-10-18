package com.zyt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: StoreBrand
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品品牌实体类
 * @Date: 11:34 2021/1/24
 * @Version: 1.0
 */
public class StoreBrand implements Serializable {
    private Integer BrandId;
    private String BrandName;
    private String BrandType;
    private Date BrandCreatTime;
    private String BrandDescrible;
    private String IsForbidden;
    private String IndexLetter;
    private List<Store> storeInfo;


    public StoreBrand() {
        super();
    }


    public StoreBrand(Integer brandId) {
        BrandId = brandId;
    }

    public StoreBrand(Integer brandId, String brandName, String brandType, Date randCreatTime, String brandDescrible, String isForbidden, String indexLetter) {
        BrandId = brandId;
        BrandName = brandName;
        BrandType = brandType;
        BrandCreatTime = randCreatTime;
        BrandDescrible = brandDescrible;
        IsForbidden = isForbidden;
        IndexLetter = indexLetter;
    }

    public StoreBrand(Integer brandId, String brandName, String brandType, Date brandCreatTime, String brandDescrible, String isForbidden, String indexLetter, List<Store> storeInfo) {
        BrandId = brandId;
        BrandName = brandName;
        BrandType = brandType;
        BrandCreatTime = brandCreatTime;
        BrandDescrible = brandDescrible;
        IsForbidden = isForbidden;
        IndexLetter = indexLetter;
        this.storeInfo = storeInfo;
    }

    public Integer getBrandId() {
        return BrandId;
    }

    public void setBrandId(Integer brandId) {
        BrandId = brandId;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getBrandType() {
        return BrandType;
    }

    public void setBrandType(String brandType) {
        BrandType = brandType;
    }

    public Date geBrandCreatTime() {
        return BrandCreatTime;
    }

    public void setRandCreatTime(Date BrandCreatTime) {
        BrandCreatTime = BrandCreatTime;
    }

    public String getBrandDescrible() {
        return BrandDescrible;
    }

    public void setBrandDescrible(String brandDescrible) {
        BrandDescrible = brandDescrible;
    }

    public String getIsForbidden() {
        return IsForbidden;
    }

    public void setIsForbidden(String isForbidden) {
        IsForbidden = isForbidden;
    }

    public String getIndexLetter() {
        return IndexLetter;
    }

    public void setIndexLetter(String indexLetter) {
        IndexLetter = indexLetter;
    }

    public Date getBrandCreatTime() {
        return BrandCreatTime;
    }

    public void setBrandCreatTime(Date brandCreatTime) {
        BrandCreatTime = brandCreatTime;
    }

    public List<Store> getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(List<Store> storeInfo) {
        this.storeInfo = storeInfo;
    }
}
