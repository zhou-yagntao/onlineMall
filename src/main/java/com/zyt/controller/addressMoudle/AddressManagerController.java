package com.zyt.controller.addressMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.userAddress.AddressManagerService;
import javafx.scene.chart.ValueAxis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.addressMoudle
 * @ClassName: AddressManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 地址管理controller层
 * @Date: 12:03 2021/2/25
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/addressManager")
public class AddressManagerController {

    private Logger logger  = LoggerFactory.getLogger(AddressManagerController.class);

    @Autowired
    private AddressManagerService addressManagerService;

    /**
     * @Method: AddPersonAcceptAddress
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/25 12:06
     * @Param: * @param addressInfo
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/addPersonAcceptAddress",method = RequestMethod.POST)
    @ResponseBody
    public Result AddPersonAcceptAddress(@RequestBody String [] addressInfo){
        for (int i = 0; i < addressInfo.length; i++) {
            logger.info("得到的参数为:"+addressInfo[i]);
        }
        return  this.addressManagerService.AddPersonAcceptAddress(addressInfo) > 0 ? Result.success(ResultCode.SUCCESS) : Result.failure(ResultCode.FAILURE);
    }

    /**
     * @Method: GetCurrUserAcceptAddress
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前用户收获地址信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/25 13:02
     * @Param: * @param loginUserName
     * @Return: com.zyt.entity.Result
     */
    @PostMapping(value = "/getCurrUserAcceptAddress")
    @ResponseBody
    public Result GetCurrUserAcceptAddress(@RequestParam(value = "loginUserName",required = false) String loginUserName){
        return this.addressManagerService.GetCurrUserAcceptAddress(loginUserName) != null ?
                Result.success(ResultCode.SUCCESS,this.addressManagerService.GetCurrUserAcceptAddress(loginUserName)):
                Result.failure(ResultCode.FAILURE);
    }

    /**
     * @Method: GetAddressInfoByAddressId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得选中的收货地址信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/25 13:30
     * @Param: * @param addressId
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getAddressInfoByAddressId",method = RequestMethod.POST)
    @ResponseBody
    public Result GetAddressInfoByAddressId(@RequestParam(value = "addressId",required = false)String addressId){
         return  this.addressManagerService.getAddressInfoByAddressId(addressId) != null ?
                 Result.success(ResultCode.SUCCESS,this.addressManagerService.getAddressInfoByAddressId(addressId))
                :Result.failure(ResultCode.FAILURE);
    }

    /**
     * @Method: GetInitUserAcceptAddress
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得默认收货地址
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/25 18:30
     * @Param: * @param userName
     * @Return: com.zyt.entity.Result
     */
    @PostMapping(value = "/getInitUserAcceptAddress")
    @ResponseBody
    public Result GetInitUserAcceptAddress(@RequestParam(value = "userName",required = false)String userName){
       return this.addressManagerService.GetInitUserAcceptAddress(true,userName) != null ?
               Result.success(ResultCode.SUCCESS,this.addressManagerService.GetInitUserAcceptAddress(true,userName)):
               Result.failure(ResultCode.FAILURE);

    }
}
