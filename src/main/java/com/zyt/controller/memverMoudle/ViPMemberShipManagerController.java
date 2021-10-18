package com.zyt.controller.memverMoudle;

import com.zyt.entity.MemberShip;
import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.service.memberService.ViPMemberShipManagerService;
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
 * @Package: com.zyt.controller
 * @ClassName: ViPMemberShipManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员信息管理控制层
 * @Date: 12:14 2021/2/8
 * @Version: 1.0
 */
@Controller
@RequestMapping("/viPMemberShipManager")
public class ViPMemberShipManagerController {

    private Logger logger = LoggerFactory.getLogger(ViPMemberShipManagerController.class);

    @Autowired
    private ViPMemberShipManagerService viPMemberShipManagerService;

    /**
     * @Method: GetAllVipMemberInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询所有会员信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/8 12:17
     * @Param: * @param currentPage
     * @param pageSize
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getAllVipMemberInfosAndCounts",method = RequestMethod.POST)
    @ResponseBody
    public Result GetAllVipMemberInfosAndCounts(@RequestParam(value = "currentPage",required = false)String currentPage,
                                                @RequestParam(value = "pageSize",required = false)String  pageSize){
        logger.info("传入参数为:"+currentPage+"\t"+pageSize);
        int count = 0;
        List<MemberShip> memberShipList = null;
        //若传入的条件都成立
        if (currentPage != null && pageSize != null){
            //查询数据并赋值给定义的变量
            memberShipList = this.viPMemberShipManagerService.GetAllVipMemberInfos(currentPage,pageSize);
            count = this.viPMemberShipManagerService.GetAllVipMemberInfosCounts();
        }
        //未查询到数据，则返回错误状态码
        if (memberShipList.size() == 0){
            if (count == 0){
                 return  Result.failure(ResultCode.RESULE_DATA_NONE);
            }
        }
        //查询到数据,则进行分装并返回的页面
        return  Result.success(ResultCode.SUCCESS,memberShipList,count);
    }

    /**
     * @Method: GetVipMemeberInfosAnCountsByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询会员信息
     * @Return: com.zyt.entity.Result
     * @Exception:
     * @Date: 2021/2/8 12:47
     * @Param: * @param currentPage
     * @param pageSize
     * @param memberName
     * @param registerDate
     * @Return: com.zyt.entity.Result
     */
    @RequestMapping(value = "/getVipMemeberInfosAnCountsByItems",method = RequestMethod.POST)
    @ResponseBody
    public Result GetVipMemeberInfosAnCountsByItems(@RequestParam(value = "currentPage",required = false)String currentPage,
                                                    @RequestParam(value = "pageSize",required = false)String  pageSize,
                                                    @RequestParam(value = "memberName",required = false)String memberName,
                                                    @RequestParam(value = "registerDate",required = false)String registerDate){
         logger.info("传入的参数为:"+currentPage+"\t\t"+pageSize+"\t\t"+memberName+"\t\t"+registerDate);
         int count = 0;
         List<MemberShip> memberShipList = null;
         //传入参数判空
         if (currentPage != null && pageSize != null && memberName != null && registerDate != null){
              memberShipList = this.viPMemberShipManagerService.getVipMemeberInfosByItems(currentPage,pageSize,memberName,registerDate);
              count = this.viPMemberShipManagerService.getVipMemeberInfosCountsByItems(memberName,registerDate);
         }
         //未查询到数据则返回错误状态码信息
         if ( memberShipList == null){
             if (count == 0){
                 return  Result.failure(ResultCode.RESULE_DATA_NONE);
             }
         }
         //查询到数据则进行数据组装
         return  Result.success(ResultCode.SUCCESS,memberShipList,count);

    }
}
