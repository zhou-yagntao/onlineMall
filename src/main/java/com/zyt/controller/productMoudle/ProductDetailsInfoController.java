package com.zyt.controller.productMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo;
import com.zyt.entity.vo.ProductDetailsTo;
import com.zyt.service.impl.productService.impl.ProductDetailsInfoServiceImpl;
import com.zyt.service.seckillProductManagerService.SeckillProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.productMoudle
 * @ClassName: ProductDetailsInfoController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品详情页信息管理模块控制层
 * @Date: 12:35 2021/2/23
 * @Version: 1.0
 */
@Controller
@CrossOrigin
@RequestMapping("/productDetailsInfo")
public class ProductDetailsInfoController {

    private Logger logger = LoggerFactory.getLogger(ProductDetailsInfoController.class);

    @Autowired
    private ProductDetailsInfoServiceImpl productDetailsInfoService;

    @Autowired
    private SeckillProductService seckillProductService;


    /**
     * @Method: GetProductDetailsInfoByProdIdAndStoreName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据商品id+店铺名称查询商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/23 12:38
     * @Param: * @param productId
     * @param prodStoreName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getProductDetailsInfoByProdIdAndStoreName",method = RequestMethod.POST)
    @ResponseBody
    public Result GetProductDetailsInfoByProdIdAndStoreName(@RequestParam(value = "productId",required = false)String productId,
                                                            @RequestParam(value = "prodStoreName",required = false)String prodStoreName){

        List<ProductDetailsTo> productDetailsTos = this.productDetailsInfoService.GetProductDetailsInfoByProdIdAndStoreName(productId,prodStoreName);
        if (productDetailsTos == null && productDetailsTos.size() < 1){
            return  Result.failure(ResultCode.RESULE_DATA_NONE);
        }
        //封装结果并返回数据
        return  Result.success(ResultCode.SUCCESS,productDetailsTos);
    }


    /**
     * @Method: GetIsJoinSecondKillOfCurrentProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前商品是否参与秒杀  /secondKillProductInfo/getIsJoinSecondKillOfCurrentProduct
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/13 14:02
     * @Param: * @param prodId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getIsJoinSecondKillOfCurrentProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result GetIsJoinSecondKillOfCurrentProduct(@RequestParam(value = "prodId",required = false) String prodId){
        SecondKillProdDetailsRedisTo secondKillProdDetailsRedisTo =  this.seckillProductService.getIsJoinSecondKillOfCurrentProduct(prodId);
        
         logger.info("======》结果为：=======》"+secondKillProdDetailsRedisTo);
        if (secondKillProdDetailsRedisTo == null){
            return  Result.failure(ResultCode.SESSION_NOT_INCLUDE_DATA);
        }else{
            return  Result.success(ResultCode.SUCCESS,secondKillProdDetailsRedisTo);
        }
    }
}
