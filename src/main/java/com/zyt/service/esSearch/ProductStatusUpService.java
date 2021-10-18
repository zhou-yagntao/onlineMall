package com.zyt.service.esSearch;

import com.zyt.entity.Result;
import com.zyt.entity.to.es.ProUpEslasticsearch;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.esSearch
 * @ClassName: ProductStatusUp
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品上架
 * @Date: 15:35 2021/2/5
 * @Version: 1.0
 */
public interface ProductStatusUpService {

    //处理商品上架
    public boolean UpProductInfos(@RequestBody List<ProUpEslasticsearch> productUpInfos);

}
