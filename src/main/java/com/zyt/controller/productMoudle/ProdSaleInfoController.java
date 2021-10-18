package com.zyt.controller.productMoudle;

import com.zyt.entity.Product;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.productService.ProdSaleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: ProdSaleInfoController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品销售管理控制层
 * @Date: 11:58 2021/2/16
 * @Version: 1.0
 */
@Controller
@RequestMapping("/prodSaleInfo")
public class ProdSaleInfoController {

    @Autowired
    private ProdSaleInfoService prodSaleInfoService;


    /**
     * @Method: GetProdSaleInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得猜你喜欢商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/16 12:08
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getProdSaleInfo",method = RequestMethod.POST)
    //@RequestMapping(value = "/getProdSaleInfo")
    @ResponseBody
    public Result GetProdSaleInfo(){
       List<Product> productList = this.prodSaleInfoService.getProdTopSaleInfo();
       if (productList== null){
           return Result.failure(ResultCode.RESULE_DATA_NONE);
       }
       //若查询到数据则进行封装返回结果
       return  Result.success(ResultCode.SUCCESS,productList);
    }

}
