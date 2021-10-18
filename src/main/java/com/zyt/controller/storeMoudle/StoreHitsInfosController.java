package com.zyt.controller.storeMoudle;

import com.zyt.controller.productMoudle.StoreProductInfosController;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.Store;
import com.zyt.entity.StoreHits;
import com.zyt.entity.vo.TopFiveStoreInfoTo;
import com.zyt.service.storeService.StoreHitsInfosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: StoreHitsInfosController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品点击量controller层
 * @Date: 10:44 2021/2/10
 * @Version: 1.0
 */
@Controller
@RequestMapping("/storeHitsInfos")
public class StoreHitsInfosController {

    private Logger logger = LoggerFactory.getLogger(StoreProductInfosController.class);

    @Autowired
    private StoreHitsInfosService storeHitsInfosService;

    /**
     * @Method: GetStoreHitsInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得top 5店铺信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/14 13:09
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getStoreHitsInfos")
    @ResponseBody
    public Result GetStoreHitsInfos(){
        List<TopFiveStoreInfoTo> storeTopFiveInfo =  this.storeHitsInfosService.GetStoreHitsInfos();
        logger.info("店铺点击TOP five:"+storeTopFiveInfo);
        if (storeTopFiveInfo.size() <= 0){
            return  Result.failure(ResultCode.FAILURE);
        }
        return  Result.success(ResultCode.SUCCESS,storeTopFiveInfo);
    }

}
