package com.zyt.entity.vo;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: SearchParams
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装商城系统页面传递过来的条件
 * @Date: 19:42 2021/2/19
 * @Version: 1.0
 */
public class SearchParams {

    private String keyWord; //页面传递过来的关键字

    private String sort; //排序条件

    private Long BrandId; //品牌编号

    private String prodPrice;//价格区间\\

    private Integer startPage = 1;

    public SearchParams(){}

    public SearchParams(String keyWord, String sort, Long brandId, String prodPrice) {
        this.keyWord = keyWord;
        this.sort = sort;
        BrandId = brandId;
        this.prodPrice = prodPrice;
    }

    public SearchParams(String keyWord, String sort, Long brandId, String prodPrice, Integer startPage) {
        this.keyWord = keyWord;
        this.sort = sort;
        BrandId = brandId;
        this.prodPrice = prodPrice;
        this.startPage = startPage;
    }

    public Integer getStartPage() {
        return startPage;
    }

    public void setStartPage(Integer startPage) {
        this.startPage = startPage;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Long getBrandId() {
        return BrandId;
    }

    public void setBrandId(Long brandId) {
        BrandId = brandId;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    @Override
    public String toString() {
        return "SearchParams{" +
                "keyWord='" + keyWord + '\'' +
                ", sort='" + sort + '\'' +
                ", BrandId=" + BrandId +
                ", prodPrice='" + prodPrice + '\'' +
                '}';
    }
}
