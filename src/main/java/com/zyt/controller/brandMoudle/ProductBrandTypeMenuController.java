package com.zyt.controller.brandMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.StoreBrand;
import com.zyt.service.brandService.StroreBrandPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: ProductBrandTypeMenuController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品品牌控制层
 * @Date: 16:10 2021/2/8
 * @Version: 1.0
 */
@Controller
@RequestMapping("/productBrandTypeMenu")
public class ProductBrandTypeMenuController {

     @Autowired
     private StroreBrandPushService stroreBrandPushService;

     /**
      * @Method: GetAllProductBrandTypeMenu
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询前端菜单信息
      * @Return: com.zyt.entity.Result
      * @Exception:
      * @Date: 2021/2/8 16:26
      * @Param: * @param
      * @Return: com.zyt.entity.Result
      */
     @RequestMapping(value = "/getAllProductBrandTypeMenu",method = RequestMethod.POST)
     @ResponseBody
     public Result GetAllProductBrandTypeMenu(){
         List<StoreBrand> brandInfoList = this.stroreBrandPushService.GetAllProductBrandTypeMenu();
         //若未查到数据则进行封装查询不到数据的状态码
         if (brandInfoList == null){
              return Result.failure(ResultCode.RESULE_DATA_NONE);
         }
         //若查询到数据，则封装数据并返回结果
         return  Result.success(ResultCode.SUCCESS,brandInfoList);

     }



}
