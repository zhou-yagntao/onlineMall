package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: TopFiveProductVo
 * @Author: zhou_yangtao@yeah.net
 * @Description: TopN商品信息
 * @Date: 12:32 2021/4/17
 * @Version: 1.0
 */
public class TopFiveProductVo {

      private Integer proId;

      private String prodName;

      private String quotations;

      private Integer clickCounts;

      private String prodImg;

      private String storeName;

      private String topImg;

      public  TopFiveProductVo(){

      }

      public TopFiveProductVo(Integer proId, String prodName, String quotations, Integer clickCounts, String prodImg, String storeName, String topImg) {
            this.proId = proId;
            this.prodName = prodName;
            this.quotations = quotations;
            this.clickCounts = clickCounts;
            this.prodImg = prodImg;
            this.storeName = storeName;
            this.topImg = topImg;
      }

      public Integer getProId() {
            return proId;
      }

      public void setProId(Integer proId) {
            this.proId = proId;
      }

      public String getProdName() {
            return prodName;
      }

      public void setProdName(String prodName) {
            this.prodName = prodName;
      }

      public String getQuotations() {
            return quotations;
      }

      public void setQuotations(String quotations) {
            this.quotations = quotations;
      }

      public Integer getClickCounts() {
            return clickCounts;
      }

      public void setClickCounts(Integer clickCounts) {
            this.clickCounts = clickCounts;
      }

      public String getProdImg() {
            return prodImg;
      }

      public void setProdImg(String prodImg) {
            this.prodImg = prodImg;
      }

      public String getStoreName() {
            return storeName;
      }

      public void setStoreName(String storeName) {
            this.storeName = storeName;
      }

      @Override
      public String toString() {
            return "TopFiveProductVo{" +
                    "proId=" + proId +
                    ", prodName='" + prodName + '\'' +
                    ", quotations='" + quotations + '\'' +
                    ", clickCounts=" + clickCounts +
                    ", prodImg='" + prodImg + '\'' +
                    ", storeName='" + storeName + '\'' +
                    '}';
      }
}
