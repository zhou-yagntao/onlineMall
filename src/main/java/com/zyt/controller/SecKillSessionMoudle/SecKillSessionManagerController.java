package com.zyt.controller.SecKillSessionMoudle;

import com.zyt.entity.*;
import com.zyt.service.SecKillProdRelationManagerService.SecKillProdRelationService;
import com.zyt.service.secKillSessionManagerService.SecKillSessionManagerService;
import com.zyt.service.seckillProductScheduleManagerService.SeckillProductScheduleService;
import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.SecKillSessionMoudle
 * @ClassName: SecKillSessionManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀服务controller层模块
 * @Date: 11:33 2021/3/6
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/secKillSessionManager")
public class SecKillSessionManagerController {

    private Logger logger = LoggerFactory.getLogger(SecKillSessionManagerController.class);

    @Autowired
    private SecKillSessionManagerService secKillSessionManagerService;

    @Autowired
    private SecKillProdRelationService secKillProdRelationService;

    @Autowired
    private SeckillProductScheduleService seckillProductScheduleService;

    /**
     * @Method: SaveSecKillSessionInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:保存秒杀场次信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/6 13:00
     * @Param: * @param seckillSessions
     * @param userName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/saveSecKillSessionInfos",method = RequestMethod.POST)
    @ResponseBody
    public Result SaveSecKillSessionInfos(String []seckillSessions,String userName){
       int result = this.secKillSessionManagerService.saveSecKillSessionInfos(seckillSessions,userName);
       if (result == 0){
           return  Result.failure(ResultCode.FAILURE);
       }else{
           return Result.success(ResultCode.SUCCESS);
       }
    }

    /**
     * @Method: GetAllSecKillInfoOfCurrStore
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前店铺所有秒杀场次信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/6 16:41
     * @Param: * @param pageSize
     * @param currentPage
     * @param userName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getAllSecKillInfoOfCurrStore",method = RequestMethod.POST)
    @ResponseBody
    public Result GetAllSecKillInfoOfCurrStore(@RequestParam(value = "pageSize",required = false)String pageSize,
                                               @RequestParam(value = "currentPage",required = false)String currentPage,
                                               @RequestParam(value = "userName",required = false)String userName){

        List<SecKillSession> secKillSessionList = this.secKillSessionManagerService.getAllSecKillInfoOfCurrStore(pageSize,currentPage,userName);
        int count  =this.secKillSessionManagerService.getSecKillInfoCountsOfCurrStore(userName);
        //若没有查到数据 则返回错误状态码
        if (secKillSessionList == null && secKillSessionList.size() == 0 && count == 0){
            return  Result.failure(ResultCode.RESULE_DATA_NONE);
        }else{
            //成功 则封装数据并返回成功状态码
            return  Result.success(ResultCode.SUCCESS,secKillSessionList,count);
        }
    }


    /**
     * @Method: GetKillInfoByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询秒杀场次信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/8 13:29
     * @Param: * @param pageSize
     * @param currentPage
     * @param userName
     * @param killName
     * @param isEnable
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getKillInfoByItems",method = RequestMethod.POST)
    @ResponseBody
    public  Result GetKillInfoByItems(@RequestParam(value = "pageSize",required = false)String pageSize,
                                      @RequestParam(value = "currentPage",required = false)String currentPage,
                                      @RequestParam(value = "userName",required = false)String userName,
                                      @RequestParam(value = "killName",required = false)String killName,
                                      @RequestParam(value = "isEnable",required = false)String isEnable){
       List<SecKillSession> secKillSessionList  = this.secKillSessionManagerService.getKillInfoByItems(pageSize,currentPage,userName,killName,isEnable);
       int secKillCounts  =  this.secKillSessionManagerService.getKillInfoCountsByItems(pageSize,currentPage,userName,killName,isEnable);
       if (secKillSessionList != null && secKillSessionList.size() > 0 && secKillCounts > 0){
           return  Result.success(ResultCode.SUCCESS,secKillSessionList,secKillCounts);
       }else{
           return  Result.failure(ResultCode.FAILURE);
       }

    }

    /**
     * @Method: BatchDeleteKillInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:批量删除秒杀信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/8 14:34
     * @Param: * @param killId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/batchDeleteKillInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result BatchDeleteKillInfo(@RequestBody String [] killId){
         logger.info("获得批量删除信息为:"+killId);
         int result = this.secKillSessionManagerService.BatchDeleteKillInfo(killId);
         return result > 0 ? Result.success(ResultCode.SUCCESS) : Result.failure(ResultCode.FAILURE);
    }

    /**
     * @Method: DeleteKillInfoByKillId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据id删除秒杀场次信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/8 14:45
     * @Param: * @param killId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/deleteKillInfoByKillId",method = RequestMethod.POST)
    @ResponseBody
    public  Result DeleteKillInfoByKillId(@RequestParam(value = "sec_kill_id",required = false) String sec_kill_id){
        int result = this.secKillSessionManagerService.DeleteKillInfoByKillId(sec_kill_id);
        return  result > 0 ? Result.success(ResultCode.SUCCESS) : Result.failure(ResultCode.FAILURE);
    }

    /**
     * @Method: GetSeckillProRelationInfoBySessionId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前场次下关联的商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/8 15:47
     * @Param: * @param sec_kill_id
     * @param currentPage
     * @param pageSize
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getSeckillProRelationInfoBySessionId")
    @ResponseBody
    public Result GetSeckillProRelationInfoBySessionId(@RequestParam(value = "sec_kill_id",required = false)String sec_kill_id,
                                                       @RequestParam(value = "currentPage",required = false)String currentPage,
                                                       @RequestParam(value = "pageSize",required = false)String pageSize){

      logger.info("传入参数为:"+sec_kill_id+"\t"+currentPage+"\t"+pageSize);
      List<SecKillProdRelation> secKillProdRelationList = this.secKillProdRelationService.GetSeckillProRelationInfoBySessionId(sec_kill_id,currentPage,pageSize);
      int count = this.secKillProdRelationService.GetSeckillProRelationCountsBySessionId(sec_kill_id);
      //未查询到数据 则返回失败状态码
      if (secKillProdRelationList == null && secKillProdRelationList.size() == 0 && count == 0) {
          return Result.failure(ResultCode.RESULE_DATA_NONE);
      }else{
          return  Result.success(ResultCode.SUCCESS,secKillProdRelationList,count);
      }
    }

    /**
     * @Method: GetRelativeProductInfoOfCurrKillSessionByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询秒杀商品信信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/8 20:35
     * @Param: * @param currentPage
     * @param pageSize
     * @param prodId
     * @param sessionId
     * @param userName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getRelativeProductInfoOfCurrKillSessionByItems",method = RequestMethod.POST)
    @ResponseBody
    public Result GetRelativeProductInfoOfCurrKillSessionByItems(@RequestParam(value = "currentPage",required = false)String currentPage,
                                                                 @RequestParam(value = "pageSize",required = false)String pageSize,
                                                                 @RequestParam(value = "prodId",required = false)String prodId,
                                                                 @RequestParam(value = "sessionId",required = false)String sessionId,
                                                                 @RequestParam(value = "sec_kill_id",required = false)String sec_kill_id,
                                                                 @RequestParam(value = "userName",required = false)String userName){
       if (!StringUtils.isEmpty(currentPage) && !StringUtils.isEmpty(pageSize)  && !StringUtils.isEmpty(prodId) && !StringUtils.isEmpty(sessionId) && !StringUtils.isEmpty( sec_kill_id) && !StringUtils.isEmpty(userName)){
           List<SecKillProdRelation>secKillProdRelationList = this.secKillProdRelationService.GetRelativeProductInfoOfCurrKillSessionByItems(currentPage,pageSize,prodId,sessionId,sec_kill_id,userName);
           int killInfoCounts  = this.secKillProdRelationService.GetRelativeProductCountsOfCurrKillSessionByItems(prodId,sessionId,sec_kill_id,userName);
           return  secKillProdRelationList != null && secKillProdRelationList.size() > 0 && killInfoCounts > 0 ?
                   Result.success(ResultCode.SUCCESS,secKillProdRelationList,killInfoCounts) :
                   Result.failure(ResultCode.RESULE_DATA_NONE);
       }else{
           return  Result.failure(ResultCode.FAILURE);
       }
    }

    /**
     * @Method: GetAllProdInfoOfCurrStore
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件场店铺商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/8 21:33
     * @Param: * @param userName
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getAllProdInfoOfCurrStore",method = RequestMethod.POST)
    @ResponseBody
    public  Result GetAllProdInfoOfCurrStore(@RequestParam(value = "userName",required = false)String userName){
        if (!StringUtils.isEmpty(userName)){
            List<Product> productList = this.secKillProdRelationService.GetAllProdInfoOfCurrStore(userName);
            return  productList != null && productList.size() > 0 ? Result.success(ResultCode.SUCCESS,productList) : Result.failure(ResultCode.FAILURE);
        }else{
            return  Result.failure(ResultCode.FAILURE);
        }
    }

     /**
     * @Method: SaveRelativeProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:保存秒杀的关联商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/9 16:37
     * @Param: * @param userName
     * @param kill_session_id
     * @param prod_id
     * @param kill_price
     * @param kill_count
     * @param sort
     * @param limit_buy_count
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/saveRelativeProduct",method = RequestMethod.POST)
    @ResponseBody
    public Result SaveRelativeProduct(@RequestParam(value = "userName",required = false)String userName,
                                      @RequestParam(value = "kill_session_id",required = false)String kill_session_id,
                                      @RequestParam(value = "prod_id",required = false)String prod_id,
                                      @RequestParam(value = "kill_price",required = false)String kill_price,
                                      @RequestParam(value = "kill_count",required = false)String kill_count,
                                      @RequestParam(value = "sort",required = false)String sort,
                                      @RequestParam(value = "limit_buy_count",required = false)String limit_buy_count){
        logger.info("传入的参数信息为:"+userName+"\t"+kill_session_id+"\t"+prod_id+"\t"+kill_price+"\t"+kill_count+"\t"+sort+"\t"+limit_buy_count);
        int result = this.secKillProdRelationService.SaveRelativeProduct(userName,kill_session_id,prod_id,kill_price,kill_count,sort,limit_buy_count);
        return result > 0 ? Result.success(ResultCode.SUCCESS) : Result.failure(ResultCode.FAILURE);
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 根据编号删除关联商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 13:09
      * @Param: * @param null
      * @Return:
      */
    @RequestMapping(value = "/deleteKillRelativeProdInfo",method = RequestMethod.POST)
    @ResponseBody
    public Result DeleteKillRelativeProdInfo(@RequestParam(value = "relation_id",required = false) String relation_id){
        int result = this.secKillProdRelationService.DeleteKillRelativeProdInfo(relation_id);
        return  result > 0 ? Result.success(ResultCode.SUCCESS) : Result.failure(ResultCode.FAILURE);

    }



}
