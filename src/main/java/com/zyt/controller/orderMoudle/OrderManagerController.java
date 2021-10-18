package com.zyt.controller.orderMoudle;

import com.zyt.entity.Result;
import com.zyt.entity.ResultCode;
import com.zyt.entity.vo.OrderSnAndOrderTotalAccountVo;
import com.zyt.entity.vo.SubmitOrderResponseTo;
import com.zyt.service.orderService.OrderManagerService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.controller.orderMoudle
 * @ClassName: OrderManagerController
 * @Author: zhou_yangtao@yeah.net
 * @Description: 订单管理controller层
 * @Date: 16:13 2021/2/25
 * @Version: 1.0
 */
@Controller
@RequestMapping("/orderManager")
public class OrderManagerController {

     private Logger logger = LoggerFactory.getLogger(OrderManagerController.class);

     @Autowired
     private  OrderManagerService orderManagerService;

     /**
      * @Method: SetAntiDuplicationTokenOfCurrentUser
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:生成防重复提交令牌
      * @Return: com.zyt.entity.Result
      * @Exception:
      * @Date: 2021/2/25 19:43
      * @Param: * @param userName
      * @Return: com.zyt.entity.Result
      */
     @RequestMapping(value = "/setAntiDuplicationTokenOfCurrentUser",method = RequestMethod.POST)
     @ResponseBody
     public Result SetAntiDuplicationTokenOfCurrentUser(@RequestParam(value = "userName",required = false)String userName){
        return  this.orderManagerService.setAntiDuplicationTokenOfCurrentUser(userName) != null
                ?Result.success(ResultCode.SUCCESS,this.orderManagerService.setAntiDuplicationTokenOfCurrentUser(userName))
                :Result.failure(ResultCode.FAILURE);
     }

     /**
      * @Method: GetSubmitOrderInfos
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得前台提交的订单信息
      * @Return: com.zyt.entity.Result
      * @Exception:
      * @Date: 2021/2/25 21:03
      * @Param: * @param loginUserName
      * @param antiDuplicationToken
      * @param leaveMessage
      * @param delivery
      * @param packingCharge
      * @param delayAddress
      * @param acceptUser
      * @param contactNum
      * @param shouldPayPrice
      * @param paymentMethods
      * @param isUseCouple
      * @param isUseIntegral
      * @param integralValue
      * @param coupleId
      * @Return: com.zyt.entity.Result
      */
     @RequestMapping(value = "/getSubmitOrderInfos",method = RequestMethod.POST)
     @ResponseBody
     public Result GetSubmitOrderInfos(@RequestParam(value = "loginUserName",required = false)String loginUserName,
                                       @RequestParam(value = "antiDuplicationToken",required = false)String antiDuplicationToken,
                                       @RequestParam(value = "leaveMessage",required = false)String leaveMessage,
                                       @RequestParam(value = "delivery",required = false)String delivery,
                                       @RequestParam(value = "packingCharge",required = false)String packingCharge,
                                       @RequestParam(value = "delayAddress",required = false)String delayAddress,
                                       @RequestParam(value = "acceptUser",required = false)String acceptUser,
                                       @RequestParam(value = "contactNum",required = false)String contactNum,
                                       @RequestParam(value = "shouldPayPrice",required = false)String shouldPayPrice,
                                       @RequestParam(value = "paymentMethods",required = false)String paymentMethods,
                                       @RequestParam(value = "isUseCouple",required = false)String isUseCouple ,
                                       @RequestParam(value = "isUseIntegral",required = false)String isUseIntegral,
                                       @RequestParam(value = "integralValue",required = false)String integralValue,
                                       @RequestParam(value = "coupleId",required = false)String coupleId,
                                       HttpServletRequest request,
                                       HttpServletResponse response,
                                       Model model,
                                       RedirectAttributes redirectAttributes) throws IOException {
          logger.info("封装上传的结果为:" + loginUserName + "\t"
                  + antiDuplicationToken + "\t" +
                  leaveMessage + "\t" +
                  delivery + "\t" +
                  packingCharge + "\t" +
                  delayAddress + "\t" +
                  acceptUser + "\t" +
                  contactNum + "\t" +
                  shouldPayPrice + "\t" +
                  paymentMethods + "\t" +
                  isUseCouple + "\t" +
                  integralValue + "\t" +
                  isUseIntegral + "\t" +
                  coupleId
          );
          SubmitOrderResponseTo submitOrderResponseTo = this.orderManagerService.getSubmitOrderInfos(
                  loginUserName, antiDuplicationToken, leaveMessage, delivery, packingCharge,
                  delayAddress, acceptUser, contactNum, shouldPayPrice,
                  paymentMethods, Boolean.valueOf(isUseCouple), integralValue, Boolean.valueOf(isUseIntegral), coupleId);
          logger.info("成功执行返回结果"+submitOrderResponseTo.getCode());

          //若返回结果为0则返回成功页
          if (submitOrderResponseTo.getCode() == 0) {
               logger.info("跳转页面了。。。。。。。。。。。。");
               // response.sendRedirect("http://127.0.0.1:9999/orderPay");
                return  Result.success(ResultCode.SUCCESS);
          } else {
               String msg = "";
               //返回到订单确认页
               switch (submitOrderResponseTo.getCode()) {
                    case 1:
                         msg += "订单信息过期 请刷新再次提交";
                         break;
                    case 2:
                         msg += "订单商品价格发生变化,请确认后在提交";
                         break;
               }
 //              redirectAttributes.addFlashAttribute("errorinfo","msg");
//               String url = "http://127.0.0.1:9999/Cart";
//               try {
//                    response.sendRedirect(url);
//               }catch (Exception e){
//                    e.printStackTrace();
//               }
//               return  "redirect:http://127.0.0.1:9999/Cart";
               //response.sendRedirect("http://127.0.0.1:9999/Cart");
               return  Result.failure(ResultCode.FAILURE,msg);
          }
     }

     /**
      * @Method: GetFinishOrderInfo
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获取订单信息
      * @Return: com.zyt.entity.Result
      * @Exception:
      * @Date: 2021/3/4 13:04
      * @Param: * @param userName
      * @Return: com.zyt.entity.Result
      */
     @RequestMapping(value = "/getFinishOrderInfo",method = RequestMethod.POST)
     @ResponseBody
     public Result GetFinishOrderInfo(String loginUserName){
          logger.info("获得的参数信息为:"+loginUserName);
          OrderSnAndOrderTotalAccountVo orderSnAndOrderTotalAccountVo = this.orderManagerService.getOrderSnAndOrderTotalAccountOfCurrentUser(loginUserName);
          if (orderSnAndOrderTotalAccountVo == null){
              return  Result.failure(ResultCode.FAILURE);
          }
          return Result.success(ResultCode.SUCCESS,orderSnAndOrderTotalAccountVo);
     }
}
