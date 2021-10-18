package com.zyt.service.impl.mallSearchService.impl;

import com.zyt.config.ElasticSearchConfig;
import com.zyt.constant.EsContant;
import com.zyt.entity.to.es.ProUpEslasticsearch;
import com.zyt.entity.vo.SearchParams;
import com.zyt.entity.vo.SearchResult;
import com.zyt.mapper.BrandMapper;
import com.zyt.service.mallSearchService.MallSearchService;
import com.zyt.utils.ChangeJsonTools;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.mallSearchService.impl
 * @ClassName: MallSearchServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品检索服务实现类
 * @Date: 19:47 2021/2/19
 * @Version: 1.0
 */
@Service(value = "mallSearchService")
public class MallSearchServiceImpl implements MallSearchService {
    private Logger logger = LoggerFactory.getLogger(MallSearchServiceImpl.class);


    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @Method: getBrandIdByBrandName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得品牌编号
     * @Return: java.lang.Integer
     * @Exception:
     * @Date: 2021/2/20 12:19
     * @Param: * @param brandName
     * @Return: java.lang.Integer
     */
    @Override
    public Integer getBrandIdByBrandName(String brandName) {
        return this.brandMapper.getBrandIdOfCurrBrandName(brandName) != 0 ? this.brandMapper.getBrandIdOfCurrBrandName(brandName) : 0;
    }

    /**
     * @Method: GetProductInfoFromEsByParams
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据传递的参数信息在es检索商品信息数据
     * @Return: java.util.List<com.zyt.entity.to.es.ProUpEslasticsearch>
     * @Exception:
     * @Date: 2021/2/20 12:24
     * @Param: * @param searchParams
     * @Return: java.util.List<com.zyt.entity.to.es.ProUpEslasticsearch>
     */
    @Override
    public SearchResult GetProductInfoFromEsByParams(SearchParams searchParams) {
        SearchResult searchResult = null;
        //动态的构建查询需要的DSL语句
        //1.准备检索请求
        SearchRequest searchRequest  = buildSearchQueryRequest(searchParams);
        try {
            //2.执行检索请求
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest,ElasticSearchConfig.COMMON_OPTIONS);
            //分析检索数据进行封装
            searchResult  = buildSearchResult(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("获得记录为:"+searchResult);
        return searchResult;
    }

    //封装返回结果
    private SearchResult buildSearchResult(SearchResponse searchResponse) {
        SearchResult result = new SearchResult();
        //返回所有的商品信息
        SearchHits searchHits = searchResponse.getHits();
        List<ProUpEslasticsearch> list  = new ArrayList<>();
       if(searchHits.getHits() != null && searchHits.getHits().length > 0){
            for(SearchHit hit : searchHits.getHits()){
                //获得json型数据
                String sourceAsString  = hit.getSourceAsString();
                //将json数据转化为对象格式
                ProUpEslasticsearch proUpEslasticsearch = ChangeJsonTools.stringToObj(sourceAsString,ProUpEslasticsearch.class);
                list.add(proUpEslasticsearch);
            }
       }
        //封装商品信息
        result.setPrducts(list);
        long total = searchHits.getTotalHits().value;
        //总记录数
        result.setTotal(total);
        return  result;
    }

    /**
      * @Description:准备检索
      * @Date: 2021/2/20 12:30
      * @Param: * @param null
      * @Return:
      */
    private SearchRequest buildSearchQueryRequest(SearchParams searchParams) {
        //构建DSL语句
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //查询条件  模糊匹配
        //1.构建bool--query查询
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        //1.1.must --模糊查询
        if(!StringUtils.isEmpty(searchParams.getKeyWord())){
            queryBuilder.must(QueryBuilders.matchQuery("prodName",searchParams.getKeyWord()));
        }
        //1.2.构建bool--filter 过滤
        //按照品牌id进行查询
        if(searchParams.getBrandId() != null){
            queryBuilder.filter(QueryBuilders.termQuery("prodBrandId",searchParams.getBrandId()));
        }
        //按照价格区间进行查询 10_20 _20 10_
        if(!StringUtils.isEmpty(searchParams.getProdPrice())){
            RangeQueryBuilder rangeQueryBuilder  = QueryBuilders.rangeQuery("prodPrice");
            String [] str  =searchParams.getProdPrice().split("_");
            if (str.length == 2){
                rangeQueryBuilder.gte(str[0]).lte(str[1]);
            }else if(str.length == 1){
                if(searchParams.getProdPrice().startsWith("_")){
                    rangeQueryBuilder.lte(str[0]);
                }
                if(searchParams.getProdPrice().endsWith("_")){
                    rangeQueryBuilder.gte(str[0]);
                }
            }
            queryBuilder.filter(rangeQueryBuilder);
        }
        searchSourceBuilder.query(queryBuilder);
        /*
        *排序
        * */
        //2.1排序
        if(!StringUtils.isEmpty(searchParams.getSort())){
             //sort = hotScore_asc/desc
            //分割
            String sort = searchParams.getSort();
            String [] str  = sort.split("-");
            SortOrder order = str[1].equalsIgnoreCase("asc") ?SortOrder.ASC:SortOrder.DESC;
            searchSourceBuilder.sort(str[0],order);
        }
        /*
        *分页
        * */
        searchSourceBuilder.from((searchParams.getStartPage() - 1)*EsContant.PRODUCT_PAGESIZE);
        searchSourceBuilder.size(EsContant.PRODUCT_PAGESIZE);
        //高亮
        if(!StringUtils.isEmpty(searchParams.getKeyWord())){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("prodName");
            highlightBuilder.preTags("<b style = 'color:red;'>");
            highlightBuilder.postTags("</b>");
            searchSourceBuilder.highlighter(highlightBuilder);
        }
        String result = searchSourceBuilder.toString();
        logger.info("结果为:"+result);
        SearchRequest searchRequest = new SearchRequest(new String []{EsContant.PRODUCT_INDEX},searchSourceBuilder);

        return  searchRequest;
    }
}
