package com.zyt.service.mallSearchService;

import com.zyt.entity.to.es.ProUpEslasticsearch;
import com.zyt.entity.vo.SearchParams;
import com.zyt.entity.vo.SearchResult;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.mallSearchService
 * @ClassName: MallSearchService
 * @Author: zhou_yangtao@yeah.net
 * @Description:检索服务实现类
 * @Date: 19:46 2021/2/19
 * @Version: 1.0
 */
public interface MallSearchService {

    //根据品牌名称查询品牌id
    public  Integer getBrandIdByBrandName(String brandName);

    //根据条件查询商品信息
    public SearchResult GetProductInfoFromEsByParams(SearchParams searchParams);
}
