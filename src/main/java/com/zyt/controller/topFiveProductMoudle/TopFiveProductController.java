package com.zyt.controller.topFiveProductMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.vo.TopFiveProductVo;
import com.zyt.service.topFiveProductService.TopFiveProductService;
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
 * @ClassName: TopFiveProductController
 * @Author: zhou_yangtao@yeah.net
 * @Description: TopN商品信息
 * @Date: 12:24 2021/4/17
 * @Version: 1.0
 */
@Controller
@RequestMapping("/topFiveProduct")
public class TopFiveProductController {

    private Logger logger = LoggerFactory.getLogger(TopFiveProductController.class);

    @Autowired
    private TopFiveProductService topFiveProductService;

    /**
     * @Method: GetTopFiveProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得前五的商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/4/17 12:27
     * @Param: * @param brandName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getTopFiveProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result GetTopFiveProduct(@RequestParam("brandName")String brandName){
         logger.info("商品品牌信息为:"+brandName);
         List<TopFiveProductVo> topFiveProductVos = this.topFiveProductService.GetTopFiveProduct(brandName);
         return  topFiveProductVos != null ? Result.success(ResultCode.SUCCESS,topFiveProductVos): Result.failure(ResultCode.FAILURE);
    }
}
