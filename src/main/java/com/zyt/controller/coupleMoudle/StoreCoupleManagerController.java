package com.zyt.controller.coupleMoudle;

import com.zyt.controller.productMoudle.StoreProductInfosController;
import com.zyt.entity.Couple;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.coupleService.StoreCoupleManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: StoreCoupleController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺优惠券管理controller
 * @Date: 12:54 2021/2/6
 * @Version: 1.0
 */
@Controller
@RequestMapping("/storeCouple")
public class StoreCoupleManagerController {

    private Logger logger = LoggerFactory.getLogger(StoreProductInfosController.class);

    @Autowired
    private StoreCoupleManagerService coupleManagerService;

    private MultipartFile file;

    /**
     * @Method: GetCoupleImg
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得优惠券图片信息
     * @Return: void
     * @Exception:
     * @Date: 2021/2/6 19:47
     * @Param: * @param coupleImg
     * @Return: void
     */
    @RequestMapping(value = "/getCoupleImg",method = RequestMethod.POST)
    @ResponseBody
    public void GetCoupleImg(@RequestParam(value = "coupleImg",required = false)MultipartFile coupleImg){
        this.file  = coupleImg;
        logger.info("获得的优惠券图片为:"+coupleImg+"\t\t"+file);
    }

    /**
     * @Method: AddCoupleInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 添加优惠券信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/6 19:47
     * @Param: * @param coupleInfoList
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/addCoupleInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result AddCoupleInfo(@RequestBody String [] coupleInfoList){
        int result = this.coupleManagerService.AddCoupleInfo(coupleInfoList,file);
        if (result == 0){
            //否则发布优惠券信息失败状态码
            return  Result.failure(ResultCode.FAILURE);
        }
        //表示添加成功，返回成功的状态码
        return  Result.success(ResultCode.SUCCESS);

    }

    /**
     * @Method: GetAllCoupleInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询所有的优惠券信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/6 19:50
     * @Param: * @param storeOwnerName
     * @param currentPage
     * @param pageSize
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/getAllCoupleInfos")
    @ResponseBody
    public Result GetAllCoupleInfos(@RequestParam(value = "storeOwnerName",required = false)String storeOwnerName,
                                    @RequestParam(value = "currentPage",required = false)String currentPage,
                                    @RequestParam(value = "pageSize",required = false)String pageSize){
         //查询当前拥有者的优惠券信息
        List<Couple> couplesInfo = this.coupleManagerService.GetAllCoupleInfos(storeOwnerName,currentPage,pageSize);
        //查询当前拥有者的优惠券条数
        int coupleCounts = this.coupleManagerService.GetAllCoupleInfoCounts(storeOwnerName);
        //如果查询数据的信息为且查询的条数为0，
        if ( couplesInfo == null){
            if (coupleCounts == 0){
                return  Result.failure(ResultCode.RESULE_DATA_NONE);
            }
        }
        //查询到数据，则进行返回数据的信息，条数以及状态码
        return  Result.success(ResultCode.SUCCESS,couplesInfo,coupleCounts);
    }


}
