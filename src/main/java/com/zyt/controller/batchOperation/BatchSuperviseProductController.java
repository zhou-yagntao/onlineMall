package com.zyt.controller.batchOperation;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.batchOperaionService.BatchSuperviseProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: BatchSuperviseProductController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 批量管理商品信息controller层
 * @Date: 16:17 2021/1/27
 * @Version: 1.0
 */
@Controller
@RequestMapping("/batchSuperviseProduct")
public class BatchSuperviseProductController {

    private Logger logger = LoggerFactory.getLogger(BatchSuperviseProductController.class);

    //注入商品管理服务层
    @Autowired
    private BatchSuperviseProductService batchSuperviseProductService;

    /**
     * @Method: BatchAddProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:批量添加商品信息
     * @Return: void
     * @Exception:
     * @Date: 2021/1/27 16:26
     * @Param: * @param file
     * @Return: void
     */
    @RequestMapping(value = "/batchAddProductInfos",method = RequestMethod.POST)
    @ResponseBody
    public void BatchAddProductInfos(@RequestParam(value = "file",required = false)MultipartFile file){
       logger.info("批量添加商品信息:"+file.getOriginalFilename());
       int result = this.batchSuperviseProductService.BatchAddProduct(file);
       if (result > 0){
           logger.info("恭喜您，批量添加商品信息成功");
       }else{
           logger.info("对不起，批量添加商品信息失败");
       }

    }

    /**
     * @Method: BatchDeleteProductInfos
     * @Author: Justin
     * @Version  1.0
     * @Description:批量删除商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/1 17:01
     * @Param: * @param params
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping("/batchDeleteProductInfos")
    @ResponseBody
    public Result BatchDeleteProductInfos(@RequestBody String [] params){
        logger.info("传入的批量删除参数为:"+params);
        int result =  this.batchSuperviseProductService.BatchDeleteProduct(params) > 0 ? this.batchSuperviseProductService.BatchDeleteProduct(params) : 0;
        //成功删除则返回结果
        return  Result.success(ResultCode.SUCCESS,result);
    }
}
