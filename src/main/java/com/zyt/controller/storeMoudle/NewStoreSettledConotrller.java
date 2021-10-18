package com.zyt.controller.storeMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.Store;
import com.zyt.service.storeService.NewStoreSettledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: NewStoreSettledConotrller
 * @Author: zhou_yangtao@yeah.net
 * @Description: 新店入驻
 * @Date: 14:01 2021/1/18
 * @Version: 1.0
 */
@Controller
@RequestMapping("/newStoreSettled")
public class NewStoreSettledConotrller {
    Logger logger = LoggerFactory.getLogger(NewStoreSettledConotrller.class);
    private MultipartFile file;

    @Autowired
    private NewStoreSettledService newStoreSettledService;

    @RequestMapping(value = "/getStoreImg",method = RequestMethod.POST)
    public void GetStoreImg(HttpServletRequest request,@RequestParam(value = "storeImgInfos",required = false) MultipartFile storeImgInfos){
        logger.info("获得新入驻店铺的照片"+storeImgInfos.getOriginalFilename());
        this.file = storeImgInfos;
    }

    /**
     * @Method: ConfireNewStoreSettled
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/1/18 15:37
     * @Param: * @param request 请求参数
     * @param response  响应参数
     * @param storeName 店铺名称
     * @param storeOwner 店铺拥有者
     * @param addressDetails 店铺详细地址
     * @param longitude 店铺坐标
     * @param latitude 店铺坐标
     * @param settledDate 店铺创建日期
     * @param storeDesc 店铺描述
     * @Return: java.lang.String
     */
    @RequestMapping(value = "/confireNewStoreSettled",method = RequestMethod.POST)
    @ResponseBody
    public Result ConfireNewStoreSettled(HttpServletRequest request,
                                         HttpServletResponse response,
                                         @RequestParam(value = "storeName",required = false) String storeName,
                                         @RequestParam(value = "storeOwner",required = false) String storeOwner,
                                         @RequestParam(value = "addressDetails",required = false) String addressDetails,
                                         @RequestParam(value = "longitude",required = false) String longitude,
                                         @RequestParam(value = "latitude",required = false) String latitude,
                                         @RequestParam(value = "settledDate",required = false) String settledDate,
                                         @RequestParam(value = "storeDesc",required = false) String storeDesc) throws IOException {
        logger.info("controller结果为:"+storeName+"\t"+storeOwner+"\t"+addressDetails+"\t"+longitude+"\t"
                                      +latitude+"\t"+settledDate+"\t"+storeDesc+"\t"+file);
        int result = newStoreSettledService.NewStoreSettled(file,storeName,storeOwner,addressDetails,longitude,latitude,settledDate,storeDesc);
        Map<String,String> map = new HashMap<>();
        if (result <0){
           return Result.failure(ResultCode.DATA_ALREADY_EXISTED);
        }
        return Result.success(ResultCode.SUCCESS);
    }

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获取商品数据信息
     * @Return:
     * @Exception:
     * @Date: 2021/1/19 15:59
     * @Param: * @param null
     * @Return:
     */
    @RequestMapping(value = "/getStoreDetailsInfos",method = RequestMethod.POST)
    @ResponseBody
    public Result GetStoreDetailInfos(@RequestParam(value = "currentPage",required = false) String currentPage ,
                                      @RequestParam(value = "offset",required = false) String offset,
                                      @RequestParam(value = "pageLimitData",required = false) String pageLimitData){
      logger.info(currentPage+"\t\t"+"\t\t"+offset+"\t\t"+pageLimitData);
      //String startRow = offset;
      //获取已经入住店铺的信息
      List<Store> storesList  = this.newStoreSettledService.GetStoreDetailInfos(currentPage,pageLimitData);
      //获取已入驻店铺的数目
      int storeCounts = this.newStoreSettledService.GetAllStoreCounts();
      logger.info("获取的数据信息为:"+storesList+"\t"+"获取的数据总数目为:"+storeCounts);
      //已经成功获取数进行封装返回
      return  Result.success(ResultCode.SUCCESS,storesList,storeCounts);
    }

    /**
     * @Method: UpdateStoreInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:修改商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/19 16:26
     * @Param: * @param storeId
     * @param storeName
     * @param storeAddress
     * @param longitude
     * @param lattitude
     * @param storeDescrible
     * @param request
     * @param response
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/updateStoreInfos")
    @ResponseBody
    public  Result UpdateStoreInfos(@RequestParam(value = "storeId",required = false) String storeId,
                                    @RequestParam(value = "storeName",required = false)String storeName,
                                    @RequestParam(value = "storeAddress",required = false)String storeAddress,
                                    @RequestParam(value = "longitude",required = false)String longitude,
                                    @RequestParam(value = "lattitude",required = false)String lattitude,
                                    @RequestParam(value = "storeDescrible",required = false)String storeDescrible,
                                    HttpServletRequest request,
                                    HttpServletResponse response){
        logger.info("修改后的数据为:"+storeId+"\t"+storeName+"\t"+storeAddress+"\t"+longitude+"\t"+lattitude+"\t"+storeDescrible);
        int result = this.newStoreSettledService.UpdateStoreInfos(storeId,storeName,storeAddress,longitude,lattitude,storeDescrible);
        if (result > 0){
            return  Result.success(ResultCode.SUCCESS);
        }else {
            return  Result.failure(ResultCode.SYSTEM_INNER_ERROR);
        }

    }

    /**
     * @Method: DeleteStoreInfosByStoreId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:删除数据
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/19 17:08
     * @Param: * @param newStoreId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/deleteStoreInfosByStoreId")
    @ResponseBody
    public Result DeleteStoreInfosByStoreId(@RequestParam(value = "newStoreId",required = false) String newStoreId){
       logger.info("删除商品信息的编号"+newStoreId);
       int result = this.newStoreSettledService.DeleteStoreInfosByStoreId(newStoreId);
       if (result > 0){
           logger.info("成功删除");
           return  Result.success(ResultCode.SUCCESS);
       }else{
           return  Result.failure(ResultCode.RESULE_DATA_NONE);
       }

    }

}
