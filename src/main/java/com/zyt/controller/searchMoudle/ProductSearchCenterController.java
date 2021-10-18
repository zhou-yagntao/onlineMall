package com.zyt.controller.searchMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.StoreBrand;
import com.zyt.entity.to.es.ProUpEslasticsearch;
import com.zyt.entity.vo.SearchParams;
import com.zyt.entity.vo.SearchResult;
import com.zyt.service.mallSearchService.MallSearchService;
import com.zyt.service.productSearchService.ProductSearchCenterService;
import org.elasticsearch.search.profile.SearchProfileShardResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PipedOutputStream;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: ProductSearchCenterController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品搜索中心控制层
 * @Date: 19:40 2021/2/16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/productSearchCenter")
public class ProductSearchCenterController {

    private Logger logger = LoggerFactory.getLogger(ProductSearchCenterController.class);

    @Autowired
    private ProductSearchCenterService productSearchCenterService;


    @Autowired
    private MallSearchService mallSearchService;

    /**
     * @Method: GetProductBrandInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得商品品牌信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/16 19:41
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getProductBrandInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result GetProductBrandInfo(){
         List<StoreBrand> storeBrandList = this.productSearchCenterService.GetProductBrandInfo();
         if (storeBrandList == null){
             return  Result.failure(ResultCode.RESULE_DATA_NONE);
         }
         return  Result.success(ResultCode.SUCCESS,storeBrandList);
    }

    /**
     * @Method: GetProductInfoByKeyWord
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据首页关键字查询商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/20 11:39
     * @Param: * @param searchItems
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getProductInfoFromEsByKeyWord",method = RequestMethod.POST)
    @ResponseBody
    public Result GetProductInfoFromEsByKeyWord(@RequestParam(value = "searchItems",required = false)String searchItems){
        logger.info("传入的关键字信息为:"+searchItems);
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyWord(searchItems);
        SearchResult searchResult =  this.mallSearchService.GetProductInfoFromEsByParams(searchParams);
        if (searchResult == null){
            return  Result.failure(ResultCode.INTERFACE_REQUEST_TIMEOUT);
        }
        List<ProUpEslasticsearch> product  = searchResult.getPrducts();
        int total = searchResult.getTotal().intValue();
        //封装结果到前台
        return Result.success(ResultCode.SUCCESS,product,total);
    }
    /**
     * @Method: GetProductInfoFromEsByKeyWordAndBrandId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据关键字和品牌编号查询
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/20 11:46
     * @Param: * @param searchItems
     * @param brandName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getProductInfoFromEsByKeyWordAndBrandName",method = RequestMethod.POST)
    @ResponseBody
    public Result GetProductInfoFromEsByKeyWordAndBrandName(@RequestParam(value = "searchItems",required = false)String searchItems,
                                                          @RequestParam(value = "brandName",required = false)String brandName){
        logger.info("传入参数为:"+searchItems+"\t"+brandName);
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyWord(searchItems);
        //通过品牌名称查询品牌id
        int brandId = this.mallSearchService.getBrandIdByBrandName(brandName);
        logger.info("查询条件内容为:"+brandId);
        searchParams.setBrandId(Long.valueOf(brandId));

        return  null;
    }

    /**
     * @Method: GetProductInfoFromEsByKeyWordAndPrice
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据关键字和价格区间进行查询
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/20 11:50
     * @Param: * @param searchItems
     * @param minPrice
     * @param maxPrice
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getProductInfoFromEsByKeyWordAndPrice",method = RequestMethod.POST)
    @ResponseBody
    public Result GetProductInfoFromEsByKeyWordAndPrice(@RequestParam(value = "searchItems",required = false)String searchItems,
                                                        @RequestParam(value = "minPrice",required = false)String  minPrice,
                                                        @RequestParam(value = "maxPrice",required = false)String maxPrice){
        logger.info("传入参数为:"+searchItems+"\t"+minPrice+"\t"+maxPrice);
        String productPrice = "";
        if(minPrice == null){
            productPrice = "_"+maxPrice;
        }
        else if(maxPrice == null){
            productPrice = minPrice+"_";
        }else{
            productPrice = minPrice+"_"+maxPrice;
        }
        logger.info("转化后的价格信息为:"+productPrice);
        SearchParams searchParams = new SearchParams();
        searchParams.setKeyWord(searchItems);
        searchParams.setProdPrice(productPrice);
        SearchResult searchResult = this.mallSearchService.GetProductInfoFromEsByParams(searchParams);
        if (searchResult == null){
            return  Result.failure(ResultCode.INTERFACE_REQUEST_TIMEOUT);
        }
        List<ProUpEslasticsearch> product  = searchResult.getPrducts();
        int total = searchResult.getTotal().intValue();
        //封装结果到前台
        return Result.success(ResultCode.SUCCESS,product,total);
    }

    @RequestMapping(value = "/getProductInfoFromEsByPriceSort")
    @ResponseBody
    public Result GetProductInfoFromEsByPriceSort(){
        String sort = "";
        return  null;
    }

}
