package com.zyt.controller.recommendMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.vo.RecommendVo;
import com.zyt.service.productRecommendService.ProductRecommendService;
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
 * @Package: com.zyt.controller.recommendMoudle
 * @ClassName: ProductRecommendController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品推荐controller层
 * @Date: 20:26 2021/4/20
 * @Version: 1.0
 */
@Controller
@RequestMapping("/productRecommend")
public class ProductRecommendController {

       private Logger logger = LoggerFactory.getLogger(ProductRecommendController.class);

       @Autowired
       private ProductRecommendService productRecommendService;

        /**
         * @Method: ProductRecommendForUser
         * @Author: zhou_yangtao@yeah.net
         * @Version  1.0
         * @Description: 商品推荐模块controller
         * @Return: com.zyt.entity.Result
         * @Exception:
         * @Date: 2021/4/20 20:44
         * @Param: * @param username
         * @Return: com.zyt.entity.Result
         */
       @RequestMapping(value = "/productRecommendForUser",method = RequestMethod.POST)
       @ResponseBody
       public Result ProductRecommendForUser(@RequestParam("username") String username){
           List<RecommendVo> recommendVos = this.productRecommendService.productRecommendForUser(username);
           if (recommendVos == null){
               return  Result.failure(ResultCode.FAILURE);
           }
           return  Result.success(ResultCode.SUCCESS,recommendVos);
       }
}
