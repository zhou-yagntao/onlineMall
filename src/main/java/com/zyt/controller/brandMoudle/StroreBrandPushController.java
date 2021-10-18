package com.zyt.controller.brandMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.StoreBrand;
import com.zyt.service.brandService.StroreBrandPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: StroreBrandPushController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品品牌管理
 * @Date: 11:28 2021/1/24
 * @Version: 1.0
 */
@Controller
@RequestMapping("/storeBrandPush")
public class StroreBrandPushController {

   private Logger logger = LoggerFactory.getLogger(StroreBrandPushController.class);

   @Autowired
   private StroreBrandPushService stroreBrandPushService;

    /**
     * @Method: PushStoreBrand
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:品牌入驻
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/24 12:49
     * @Param: * @param formData
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/pushStoreBrand",method = RequestMethod.POST)
    @ResponseBody
    public Result PushStoreBrand(@RequestBody String [] formData){
        int result = 0;
        try {
            result = this.stroreBrandPushService.PushStoreBrandInfos(formData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ( result > 0 ){
           return Result.success(ResultCode.SUCCESS);
        }
        return Result.failure(ResultCode.DATA_ALREADY_EXISTED);
    }

    /**
     * @Method: GetStoreBrandInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/24 14:09
     * @Param: * @param isForbiddenBrand
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/getStoreBrandInfos")
    @ResponseBody
    public Result GetStoreBrandInfos(@RequestParam(value = "isForbiddenBrand",required = false)String isForbiddenBrand,
                                     @RequestParam(value = "currentPage",required = false) String currentPage,
                                     @RequestParam(value = "pageSize",required = false) String pageSize
                                     ){
        logger.info("是否禁止商品"+isForbiddenBrand);
        logger.info("分页参数信息"+currentPage+"\t\t"+pageSize);
        //获得商品详细信息
        List<StoreBrand> storeBrands =  this.stroreBrandPushService.GetStoreBrandDetailInfos(currentPage,pageSize,isForbiddenBrand);
        //获得商品数量
        int brandCounts = this.stroreBrandPushService.GetStoreBrandCounts(isForbiddenBrand);
        logger.info("返回商品详情结果为:"+storeBrands);
        logger.info("返回商品的数量结果为:"+brandCounts);
        //返回商品的详细信息
        return  Result.success(ResultCode.SUCCESS,storeBrands,brandCounts);
    }

    /**
     * @Method: UpdateStoreBrandInfosByBrandId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:修改商品品牌的属性
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/24 16:40
     * @Param: * @param brandID
     * @param isForbidden
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/updateStoreBrandInfosByBrandId")
    @ResponseBody
    public Result UpdateStoreBrandInfosByBrandId(@RequestParam(value = "brandID",required = false)String brandID,
                                                 @RequestParam(value = "isForbidden",required = false)String isForbidden){
        logger.info("进来了");
        logger.info("修改商品的编号为:"+brandID);
        int result = this.stroreBrandPushService.UpdateStoreBrandInfosByBrandId(brandID,isForbidden);
        if(result == 1){
            //若修改成功，则返回200状态码
            return  Result.success(ResultCode.SUCCESS);
        }
        //若为0，则表示无该数据
        return  Result.failure(ResultCode.RESULE_DATA_NONE);
    }

    /**
     * @Method: DeleteStoreBrandInfosByBrandId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据商品编号删除商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/24 17:16
     * @Param: * @param brandID
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/deleteStoreBrandInfosByBrandId",method = RequestMethod.POST)
    @ResponseBody
    public Result DeleteStoreBrandInfosByBrandId(@RequestParam(value = "brandID",required = false)String brandID){
        logger.info("删除商品的编号为:"+brandID);
        int result = this.stroreBrandPushService.DeleteStoreBrandInfosByBrandId(brandID);
        if(result == 1){
            //删除成功，则返回成功状态码
            return  Result.success(ResultCode.SUCCESS);
        }
        return  Result.failure(ResultCode.SYSTEM_INNER_ERROR);
    }

    /**
     * @Method: GetAllProductBrandName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得所有商品品牌的名称信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/24 17:18
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getAllProductBrandName",method = RequestMethod.POST)
    @ResponseBody
    public  Result GetAllProductBrandName(){
        List<StoreBrand> storeBrandsNameInfos = this.stroreBrandPushService.GetAllProductBrandName();
        //获得所有的商品品牌信息并返回
        return  Result.success(ResultCode.SUCCESS,storeBrandsNameInfos);
    }
}
