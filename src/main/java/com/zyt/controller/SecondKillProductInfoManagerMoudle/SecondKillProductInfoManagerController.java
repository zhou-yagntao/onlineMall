package com.zyt.controller.SecondKillProductInfoManagerMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.to.secondKill.SecondKillProdDetailsRedisTo;
import com.zyt.service.seckillProductManagerService.SeckillProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller
 * @ClassName: SecondKillProductInfoManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀商品信息管理模块
 * @Date: 19:49 2021/3/12
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/secondKillProductInfo")
public class SecondKillProductInfoManagerController {

    private Logger logger = LoggerFactory.getLogger(SecondKillProductInfoManagerController.class);

    @Autowired
    private SeckillProductService seckillProductService;

    /**
     * @Method: GetSecondKillProdInfoOfCurrentTime
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前时间秒杀商品信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/12 20:02
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @PostMapping(value = "/getSecondKillProdInfoOfCurrentTime")
    @ResponseBody
    public Result GetSecondKillProdInfoOfCurrentTime(){
        List<SecondKillProdDetailsRedisTo> secondKillProdDetailsRedisToList = this.seckillProductService.getSecondKillProdInfoOfCurrentTime();

        if (secondKillProdDetailsRedisToList == null && secondKillProdDetailsRedisToList.size() == 0){
            return  Result.failure(ResultCode.SESSION_NOT_INCLUDE_DATA);
        }else{
            return Result.success(ResultCode.SUCCESS,secondKillProdDetailsRedisToList,secondKillProdDetailsRedisToList.size());
        }
    }


}
