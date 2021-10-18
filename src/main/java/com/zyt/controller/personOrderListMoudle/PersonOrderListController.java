package com.zyt.controller.personOrderListMoudle;

import com.zyt.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.personOrderListMoudle
 * @ClassName: PersonOrderListController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 个人订单列表页控制层模块
 * @Date: 13:22 2021/3/4
 * @Version: 1.0
 */
@Controller
@RequestMapping("/personOrderList")
public class PersonOrderListController {

    /**
     * @Method: GetInitPersonOrderList
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得个人的订单列表页信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/3/4 13:24
     * @Param: * @param
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getInitPersonOrderList",method = RequestMethod.POST)
    @ResponseBody
    public Result GetInitPersonOrderList(){
        return  null;
    }
}
