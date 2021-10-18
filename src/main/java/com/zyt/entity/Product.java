package com.zyt.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity
 * @ClassName: Product
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品信息实体类
 * @Date: 12:30 2021/1/25
 * @Version: 1.0
 */
public class Product  implements Serializable {
   private Integer ProdId;
   private Integer ProdSort;
   private String ProdName;
   private String ProdImage;
   private Date ProdReleaseTime;
   private String ProdStatus;
   private String ProdNvipAllPrice;
   private String ProdVipAllPrice;
   private Integer ProdIntegral;
   private Integer ProdGrowthvalue;
   private Integer storeBrandId;
   private Boolean IsOnSheleves;
   private Integer prodEveryMaxStorage;
   private String prodMoral;
   private Boolean IsUseCouple;
   private Boolean IsUseIntegral;
   private ProductClick productClick;
   private String ProStoreName;
   private StoreBrand storeBrand;
   private List<ProductPraise> productPraise;

   public Product() {
      super();
   }

   public Product(Integer prodId) {
      ProdId = prodId;
   }

   public Product(Integer prodId, Integer prodSort, String prodName, String prodImage, Date prodReleaseTime, String prodStatus, String prodNvipAllPrice, String prodVipAllPrice, Integer prodIntegral, Integer prodGrowthvalue, Integer storeBrandId, Boolean isOnSheleves, Integer prodEveryMaxStorage, String prodMoral, Boolean isUseCouple, Boolean isUseIntegral, ProductClick productClick, String proStoreName, StoreBrand storeBrand, List<ProductPraise> productPraise) {
      ProdId = prodId;
      ProdSort = prodSort;
      ProdName = prodName;
      ProdImage = prodImage;
      ProdReleaseTime = prodReleaseTime;
      ProdStatus = prodStatus;
      ProdNvipAllPrice = prodNvipAllPrice;
      ProdVipAllPrice = prodVipAllPrice;
      ProdIntegral = prodIntegral;
      ProdGrowthvalue = prodGrowthvalue;
      this.storeBrandId = storeBrandId;
      IsOnSheleves = isOnSheleves;
      this.prodEveryMaxStorage = prodEveryMaxStorage;
      this.prodMoral = prodMoral;
      IsUseCouple = isUseCouple;
      IsUseIntegral = isUseIntegral;
      this.productClick = productClick;
      ProStoreName = proStoreName;
      this.storeBrand = storeBrand;
      this.productPraise = productPraise;
   }

   public Integer getProdId() {
      return ProdId;
   }

   public void setProdId(Integer prodId) {
      ProdId = prodId;
   }

   public Integer getProdSort() {
      return ProdSort;
   }

   public void setProdSort(Integer prodSort) {
      ProdSort = prodSort;
   }

   public String getProdName() {
      return ProdName;
   }

   public void setProdName(String prodName) {
      ProdName = prodName;
   }

   public String getProdImage() {
      return ProdImage;
   }

   public void setProdImage(String prodImage) {
      ProdImage = prodImage;
   }

   public Date getProdReleaseTime() {
      return ProdReleaseTime;
   }

   public void setProdReleaseTime(Date prodReleaseTime) {
      ProdReleaseTime = prodReleaseTime;
   }

   public String getProdStatus() {
      return ProdStatus;
   }

   public void setProdStatus(String prodStatus) {
      ProdStatus = prodStatus;
   }

   public String getProdNvipAllPrice() {
      return ProdNvipAllPrice;
   }

   public void setProdNvipAllPrice(String prodNvipAllPrice) {
      ProdNvipAllPrice = prodNvipAllPrice;
   }

   public String getProdVipAllPrice() {
      return ProdVipAllPrice;
   }

   public void setProdVipAllPrice(String prodVipAllPrice) {
      ProdVipAllPrice = prodVipAllPrice;
   }

   public Integer getProdIntegral() {
      return ProdIntegral;
   }

   public void setProdIntegral(Integer prodIntegral) {
      ProdIntegral = prodIntegral;
   }

   public Integer getProdGrowthvalue() {
      return ProdGrowthvalue;
   }

   public void setProdGrowthvalue(Integer prodGrowthvalue) {
      ProdGrowthvalue = prodGrowthvalue;
   }

   public Integer getStoreBrandId() {
      return storeBrandId;
   }

   public void setStoreBrandId(Integer storeBrandId) {
      this.storeBrandId = storeBrandId;
   }

   public Boolean getOnSheleves() {
      return IsOnSheleves;
   }

   public void setOnSheleves(Boolean onSheleves) {
      IsOnSheleves = onSheleves;
   }

   public Integer getProdEveryMaxStorage() {
      return prodEveryMaxStorage;
   }

   public void setProdEveryMaxStorage(Integer prodEveryMaxStorage) {
      this.prodEveryMaxStorage = prodEveryMaxStorage;
   }

   public String getProdMoral() {
      return prodMoral;
   }

   public void setProdMoral(String prodMoral) {
      this.prodMoral = prodMoral;
   }

   public Boolean getUseCouple() {
      return IsUseCouple;
   }

   public void setUseCouple(Boolean useCouple) {
      IsUseCouple = useCouple;
   }

   public Boolean getUseIntegral() {
      return IsUseIntegral;
   }

   public void setUseIntegral(Boolean useIntegral) {
      IsUseIntegral = useIntegral;
   }

   public ProductClick getProductClick() {
      return productClick;
   }

   public void setProductClick(ProductClick productClick) {
      this.productClick = productClick;
   }

   public String getProStoreName() {
      return ProStoreName;
   }

   public void setProStoreName(String proStoreName) {
      ProStoreName = proStoreName;
   }

   public StoreBrand getStoreBrand() {
      return storeBrand;
   }

   public void setStoreBrand(StoreBrand storeBrand) {
      this.storeBrand = storeBrand;
   }

   public List<ProductPraise> getProductPraise() {
      return productPraise;
   }

   public void setProductPraise(List<ProductPraise> productPraise) {
      this.productPraise = productPraise;
   }
}

