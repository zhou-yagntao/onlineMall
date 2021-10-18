package com.zyt.controller.productMoudle;

import com.zyt.entity.Product;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.productService.StoreProductInfosService;
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
 * @ClassName: StoreProductInfosController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺商品信息管理controller层
 * @Date: 11:53 2021/1/25
 * @Version: 1.0
 */
@Controller
@RequestMapping("/storeProductInfos")
public class StoreProductInfosController {

    private Logger logger  = LoggerFactory.getLogger(StoreProductInfosController.class);

    @Autowired
    private StoreProductInfosService storeProductInfosService;

    private MultipartFile productImg;

    /**
     * @Method: GetProductImg
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得发布商品的图片信息
     * @Return: void
     * @Exception:
     * @Date: 2021/1/25 12:28
     * @Param: * @param productImgInfos
     * @Return: void
     */
    @RequestMapping(value = "/getProductImg",method = RequestMethod.POST)
    @ResponseBody
    public void GetProductImg(@RequestParam(value = "productImgInfos",required = false)MultipartFile productImgInfos){
          logger.info("获得商品品牌信息为"+productImgInfos.getOriginalFilename());
          this.productImg  = productImgInfos;
    }

    /**
     * @Method: AddNewStoreProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/26 13:28
     * @Param: * @param productList
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/addNewStoreProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result AddNewStoreProduct(@RequestBody String [] productList) {
        logger.info("获得前台上传的商品信息有:" + productList);
        //将获得的数据传递给service层
        int result  = this.storeProductInfosService.AddNewProductInfos(productImg,productList);
        if(result == 1){
            //返回结果为1则表示添加数据成功  返回成功状态码
            return Result.success(ResultCode.SUCCESS);
        }
        //否则表示添加数据失败，则返回失败状态码
        return Result.failure(ResultCode.FAILURE);

    }

    /**
     * @Method: GetCurrentStoreAllProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查收商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/1/27 18:26
     * @Param: * @param currentPage
     * @param pageLimitData
     * @param shopOwnerName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getCurrentStoreAllProductInfos",method = RequestMethod.POST)
    @ResponseBody
    public Result GetCurrentStoreAllProductInfos(@RequestParam(value = "currentPage",required = false) String currentPage ,
                                                 @RequestParam(value = "pageLimitData",required = false) String pageLimitData,
                                                 @RequestParam(value = "shopOwnerName",required = false)String shopOwnerName){
     logger.info("传入控制层的参数为:"+currentPage+"\t"+pageLimitData+"\t"+shopOwnerName);
     //1.获得商品信息列表
     List<Product> productInfosList =  this.storeProductInfosService.GetCurrentStoreAllProductInfos(currentPage,pageLimitData,shopOwnerName);
     //List<Product> productInfosList =  this.storeProductInfosService.GetCurrentStoreAllProductInfos(currentPage,pageLimitData,shopOwnerName);
     //2.查询该店家名下商品信息的数量
     int counts = this.storeProductInfosService.GetCurrrentStoreAllProductsCounts(shopOwnerName);
     logger.info("商品信息的列表信息为:"+productInfosList);
     logger.info("该用户下商品信息的数量为:"+counts);
     if(productInfosList.size()<= 0 && counts <= 0){
         //未查到数据则返回未找到数据
         return  Result.failure(ResultCode.RESULE_DATA_NONE);
     }
      //查询到数据 则返回到前端页面进行渲染
      return Result.success(ResultCode.SUCCESS,productInfosList,counts);
    }

    /**
     * @Method: GetProductInfosByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:根据条件查询商品信息
     * @Date: 2021/2/1 14:44
     * @Param: * @param currentPage
     * @param pageLimitData
     * @param productName
     * @param storeName
     * @param prodBrandId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getProductInfosByItems",method = RequestMethod.POST)
    @ResponseBody
    public Result GetProductInfosByItems(@RequestParam(value = "currentPage",required = false) String currentPage ,
                                         @RequestParam(value = "pageLimitData",required = false) String pageLimitData,
                                         @RequestParam(value = "productName",required = false)String productName,
                                         @RequestParam(value = "storeName",required = false)String storeName,
                                         @RequestParam(value = "prodBrandId",required = false) String prodBrandId
                                         ){

       logger.info("传入控制层的参数信息为:"+currentPage+"\t\t"+pageLimitData+"\t\t"+productName+"\t\t"+storeName+"\t\t"+prodBrandId);
       List<Product> productInfosList = this.storeProductInfosService.GetProductInfosByItems(currentPage,pageLimitData,productName,storeName,prodBrandId) != null ?this.storeProductInfosService.GetProductInfosByItems(currentPage,pageLimitData,productName,storeName,prodBrandId):null;
       int counts = this.storeProductInfosService.GetProductInfoCountsBYItems(productName,storeName,prodBrandId);
       //成功获得数据  返回数据
       return  Result.success(ResultCode.SUCCESS,productInfosList,counts);
    }

    /**
     * @Method: DeleteProductByProdId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:删除商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/1 17:32
     * @Param: * @param productId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/deleteProductByProdId")
    @ResponseBody
    public Result DeleteProductByProdId(@RequestParam(value = "productId",required = false) String productId){
        logger.info("即将删除的商品信息编号:"+productId);
        int result = this.storeProductInfosService.DeleteProductByProdId(productId) > 0 ? this.storeProductInfosService.DeleteProductByProdId(productId) : 0;
        //删除成功，返回给前台页面对应的数据
        return  Result.success(ResultCode.SUCCESS,result);
    }

    /**
     * @Method: UpdateProductCoupleState
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/4 11:13
     * @Param: * @param productId
     * @param coupleState
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/updateProductCoupleState")
    @ResponseBody
    public Result UpdateProductCoupleState(@RequestParam(value = "productId",required = false)String productId,
                                           @RequestParam(value = "coupleState",required = false)String coupleState){
       logger.info("传入修改商品信息编号为:"+productId);
       int result = this.storeProductInfosService.UpdateProductCoupleState(productId,coupleState) > 0 ? this.storeProductInfosService.UpdateProductCoupleState(productId,coupleState) : 0;
       //修改成功 则返回结果
       return  Result.success(ResultCode.SUCCESS,result);

    }

    /**
     * @Method: UpdateProductIntegeral
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/4 11:12
     * @Param: * @param productId
     * @param integralState
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/updateProductIntegeral")
    @ResponseBody
    public Result UpdateProductIntegeral(@RequestParam(value = "productId",required = false)String productId,
                                         @RequestParam(value = "integralState",required = false)String integralState){
        logger.info("传入修改商品信息编号为:"+productId);
        int result  = this.storeProductInfosService.UpdateProductIntegeral(productId,integralState) > 0 ? this.storeProductInfosService.UpdateProductIntegeral(productId,integralState): 0;
        //修改成功 则返回结果
        return  Result.success(ResultCode.SUCCESS,result);
    }

    /**
     * @Method: ProductUp
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:商品上架业务控制层
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/4 11:41
     * @Param: * @param productId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/productUp",method = RequestMethod.POST)
    @ResponseBody
    public Result ProductUp(@RequestParam(value = "prodId",required = false)String prodId,
                            @RequestParam(value = "brandId",required = false)String brandId) {
        int result = this.storeProductInfosService.ProductUp(prodId,brandId);
        //上架成功，直接返回成功编号
        if(result > 0){
            return Result.success(ResultCode.SUCCESS);
        }
        //上架失败 返回失败状态码
        return  Result.failure(ResultCode.FAILURE);
    }

    /**
     * @Method: SearchProInfoByItems
     * @Author: Justin
     * @Version  1.0
     * @Description:根据条件查询商品上架的信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/6 10:00
     * @Param: * @param currentPage
     * @param pageLimitData
     * @param params
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/searchProInfoByItems",method = RequestMethod.POST)
    @ResponseBody
    public Result SearchProInfoByItems(@RequestParam(value = "currentPage",required = false)String currentPage,
                                       @RequestParam(value = "pageLimitData",required = false)String pageLimitData,
                                       @RequestParam(value = "params",required = false)String params){
        //获得商品的信息
        List<Product> productList = this.storeProductInfosService.SearchProInfoByItems(currentPage,pageLimitData,params);
        //获得商品的数量
        int counts = this.storeProductInfosService.SearchProInfoCountsByItems(params);
        //组装数据并返回
        return Result.success(ResultCode.SUCCESS,productList,counts);
    }







}
