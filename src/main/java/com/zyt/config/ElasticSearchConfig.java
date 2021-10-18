package com.zyt.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.HttpAsyncResponseConsumerFactory;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.config
 * @ClassName: ElasticSearchConfig
 * @Author: zhou_yangtao@yeah.net
 * @Description: es搜索引擎配置信息
 * @Date: 10:16 2021/2/4
 * @Version: 1.0
 */
@Configuration
public class ElasticSearchConfig {

    public static final RequestOptions COMMON_OPTIONS;
    static {
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
//        builder.addHeader("Authorization", "Bearer " + TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
         COMMON_OPTIONS = builder.build();
    }

    /**
     * @Method: EsRestClient
     * @Author: Justin
     * @Version  1.0
     * @Description:编写连接es的配置信息
     * @Return: org.elasticsearch.client.RestHighLevelClient
     * @Exception:
     * @Date: 2021/2/4 10:24
     * @Param: * @param
     * @Return: org.elasticsearch.client.RestHighLevelClient
     */
    @Bean
    public RestHighLevelClient EsRestClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.10.131", 9200, "http")
                        //new HttpHost("localhost", 9201, "http") //尚未配置集群 此处以单机连接为主
                ));
        return  client;
        
    }

}
