package com.zyt.controller.productMoudle;

import com.zyt.entity.*;
import com.zyt.service.storeService.NewStoreSettledService;
import com.zyt.service.productService.StoreProductInfosService;
import com.zyt.service.brandService.StroreBrandPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: EachProductOfTypesController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 每种商品类型控制层
 * @Date: 17:59 2021/2/8
 * @Version: 1.0
 */
@Controller
@RequestMapping("/eachProductOfTypes")
public class EachProductOfTypesController {

    private Logger logger = LoggerFactory.getLogger(EachProductOfTypesController.class);

    @Autowired
    private StroreBrandPushService stroreBrandPushService;

    @Autowired
    private StoreProductInfosService storeProductInfosService;

    @Autowired
    private NewStoreSettledService storeSettledService;

    /**
     * @Method: GetEachProductOfTypes
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/8 19:33
     * @Param: * @param brandName
     * @param currPostions
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getEachProductOfTypes",method = RequestMethod.POST)
    @ResponseBody
    public Result GetEachProductOfTypes(@RequestParam(value = "brandName",required = false)String brandName,
                                        @RequestParam(value = "currPostions",required = false)String currPostions){

        logger.info("传入参数为:"+currPostions+"\t"+brandName);
        List<StoreBrand> storeInfoList = null;
        //若传入的条件不为空则获取数据
        if (brandName != null){
            storeInfoList = this.stroreBrandPushService.GetStoreInfoOfBrand(brandName,currPostions);
        }
        if (storeInfoList == null){
              return  Result.failure(ResultCode.RESULE_DATA_NONE);
        }
        //查询到数据进行封装并返回
        return  Result.success(ResultCode.SUCCESS,storeInfoList);
    }

    /**
     * @Method: GetAllProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 查询商品信进行前台渲染
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/10 17:01
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getAllProductInfos",method = RequestMethod.POST)
    @ResponseBody
    public Result GetAllProductInfos(){
         List<Product> productList = this.storeProductInfosService.GetAllProductInfos() != null ? this.storeProductInfosService.GetAllProductInfos() : null;
         if (productList == null){
             return  Result.failure(ResultCode.RESULE_DATA_NONE);
         }
         return  Result.success(ResultCode.SUCCESS,productList);
    }

    /**
     * @Method: GetStoreInfoOfCurrentPostion
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前位置下店铺信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/18 18:49
     * @Param: * @param currPostion
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getStoreInfoOfCurrentPostion",method = RequestMethod.POST)
    //@RequestMapping(value = "/getStoreInfoOfCurrentPostion")
    @ResponseBody
    public Result GetStoreInfoOfCurrentPostion(@RequestParam(value = "currPostions",required = false)String currPostions){
        logger.info("传入参数为:"+currPostions);
        List<Store> storeList = this.storeSettledService.GetStoreInfoOfCurrentPostion(currPostions);
        logger.info("获得店铺名结果为:"+storeList);
        if (storeList == null){
            return  Result.failure(ResultCode.RESULE_DATA_NONE);
        }
        return  Result.success(ResultCode.SUCCESS,storeList);
    }

    /**
     * @Method: GetAllProductInfoOfCurrBrand
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前品牌下所有店铺信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/18 19:27
     * @Param: * @param brandName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getAllProductInfoOfCurrBrand",method = RequestMethod.POST)
    @ResponseBody
    public  Result GetAllProductInfoOfCurrBrand(@RequestParam(value = "brandName",required = false)String brandName){
       List <Product> productList  = this.storeProductInfosService.GetAllProductInfoOfCurrBrand(brandName) != null ? this.storeProductInfosService.GetAllProductInfoOfCurrBrand(brandName) : null;
       if (productList == null){
           return  Result.failure(ResultCode.RESULE_DATA_NONE);
       }
       return  Result.success(ResultCode.SUCCESS,productList);
    }


    @RequestMapping(value = "/getAllProdInfoOfCurrBrandNameAndStore",method = RequestMethod.POST)
    @ResponseBody
    public  Result GetAllProdInfoOfCurrBrandNameAndStore(@RequestParam(value = "brandName",required = false)String brandName,
                                                         @RequestParam(value = "storeName",required = false)String storeName){
        logger.info("传入参数:"+brandName+"\t"+storeName);
        return  null;
    }

}
