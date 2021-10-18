package com.zyt.service.impl.esSearch.impl;

import com.zyt.config.ElasticSearchConfig;
import com.zyt.constant.EsContant;
import com.zyt.entity.Result;
import com.zyt.entity.to.es.ProUpEslasticsearch;
import com.zyt.service.esSearch.ProductStatusUpService;
import com.zyt.utils.ChangeJsonTools;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.esSearch.impl
 * @ClassName: ProductStatusUpServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 15:43 2021/2/5
 * @Version: 1.0
 */
@Service(value = "productStatusUpService")
public class ProductStatusUpServiceImpl implements ProductStatusUpService {

    private Logger logger = LoggerFactory.getLogger(ProductStatusUpServiceImpl.class);

    //引入es服务
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * @Method: UpProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理商品上架业务
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/5 15:44
     * @Param: * @param productUpInfos
     * @Return: com.zyt.entity.Result
     */
    @Override
    public boolean UpProductInfos(List<ProUpEslasticsearch> productUpInfos) {
        BulkResponse bulkResponse = null;
        //给es建立索引,建立好映射关系
        //向es中保存数据
        BulkRequest bulkRequest = new BulkRequest();
        for (ProUpEslasticsearch proUpEslasticsearch:productUpInfos){
            //构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsContant.PRODUCT_INDEX);
            indexRequest.id(proUpEslasticsearch.getProdId().toString());
            //将数据保存为json格式再进行存储
            String value  = ChangeJsonTools.objToStringPretty(proUpEslasticsearch);
            indexRequest.source(value, XContentType.JSON);
            bulkRequest.add(indexRequest);
        }
        try {
            bulkResponse = this.restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean flag = bulkResponse.hasFailures();
        List<String> collect = Arrays.stream(bulkResponse.getItems()).map(item ->{
            return  item.getId();
        }).collect(Collectors.toList());
        new Exception("商品上架错误:"+collect);
        return  flag;
    }
}
