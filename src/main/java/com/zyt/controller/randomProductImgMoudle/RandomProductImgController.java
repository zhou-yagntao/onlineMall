package com.zyt.controller.randomProductImgMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.productService.ProductDetailsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.randomProductImgMoudle
 * @ClassName: RandomProductImgController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 随机商品图片信息展示
 * @Date: 21:58 2021/4/10
 * @Version: 1.0
 */
@Controller
//@RequestMapping(value = "/randomProductImg")
public class RandomProductImgController {

    @Autowired
    private ProductDetailsInfoService productDetailsInfoService;

    /**
     * @Method: GetRandomProductImgInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得随机商品展示数据信息
     * @Return: Result
     * @Exception:
     * @Date: 2021/4/10 21:59
     * @Param: * @param
     * @Return: Result
     */
    @PostMapping(value = "/getRandomProductImgInfo")
    @ResponseBody
    public Result GetRandomProductImgInfo(){
        List<String> ImgUrl = this.productDetailsInfoService.getRandomProductImgInfo();
        //不为空就返回数据
        return  ImgUrl != null ? Result.success(ResultCode.SUCCESS,ImgUrl) : Result.failure(ResultCode.FAILURE);
    }
}
