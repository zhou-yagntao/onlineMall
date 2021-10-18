package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: RecommendVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品推荐信息Vo
 * @Date: 20:45 2021/4/20
 * @Version: 1.0
 */
public class RecommendVo {
       private Integer prodId;

       private String prodName;

       private String prodImg;

       private String prodPrice;

       private String storeName;

      public RecommendVo(){}

      public RecommendVo(Integer prodId, String prodName, String prodImg, String prodPrice, String storeName) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodImg = prodImg;
        this.prodPrice = prodPrice;
        this.storeName = storeName;
      }

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdImg() {
        return prodImg;
    }

    public void setProdImg(String prodImg) {
        this.prodImg = prodImg;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    @Override
    public String toString() {
        return "RecommendVo{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", prodImg='" + prodImg + '\'' +
                ", prodPrice=" + prodPrice +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}
