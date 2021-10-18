package com.zyt.entity.vo;

import com.zyt.entity.to.es.ProUpEslasticsearch;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.entity.vo
 * @ClassName: SearchResult
 * @Author: zhou_yangtao@yeah.net
 * @Description: 封装商品返回信息结果
 * @Date: 12:33 2021/2/20
 * @Version: 1.0
 */
public class SearchResult {
    //查询到的所有商品信息
    private List<ProUpEslasticsearch> prducts;
    private Integer pageNum; //当前页码
    private Long total;
    private Integer pageTotals;

    public  SearchResult(){
        super();
    }

    public List<ProUpEslasticsearch> getPrducts() {
        return prducts;
    }

    public void setPrducts(List<ProUpEslasticsearch> prducts) {
        this.prducts = prducts;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageTotals() {
        return pageTotals;
    }

    public void setPageTotals(Integer pageTotals) {
        this.pageTotals = pageTotals;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "prducts=" + prducts +
                ", pageNum=" + pageNum +
                ", total=" + total +
                ", pageTotals=" + pageTotals +
                '}';
    }
}
