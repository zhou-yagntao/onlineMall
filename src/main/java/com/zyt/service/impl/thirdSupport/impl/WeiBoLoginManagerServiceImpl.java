package com.zyt.service.impl.thirdSupport.impl;

import com.zyt.entity.SocailUser;
import com.zyt.entity.VipMember;
import com.zyt.entity.vo.UserFinishLoginResult;
import com.zyt.mapper.MemberLeaveMapper;
import com.zyt.service.memberService.addMemberInfoByWeiBo.AddMemberInfoFromSocialUserService;
import com.zyt.service.thirdSupport.WeiBoLoginManagerService;
import com.zyt.utils.ChangeJsonTools;
import com.zyt.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.thirdSupport.impl
 * @ClassName: WeiBoLoginManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 微博登录服务层实现模块
 * @Date: 12:56 2021/2/21
 * @Version: 1.0
 */
@Service(value = "weiBoLoginManagerService")
public class WeiBoLoginManagerServiceImpl implements WeiBoLoginManagerService {
   private Logger logger = LoggerFactory.getLogger(WeiBoLoginManagerService.class);

    @Autowired
    private AddMemberInfoFromSocialUserService addMemberInfoFromSocialUserService;

    @Autowired
    private MemberLeaveMapper memberLeaveMapper;

    /**
     * @Method: WeiBoAuthorize
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:微博授权成功后完成对所需数据的组装
     * @Return: com.zyt.entity.SocailUser
     * @Exception:
     * @Date: 2021/2/21 14:17
     * @Param: * @param code
     * @Return: com.zyt.entity.SocailUser
     */
    @Override
    public UserFinishLoginResult WeiBoAuthorize(String code)  {


        //根据code获得对应的access code
        //https://api.weibo.com/oauth2/access_token?client_id=YOUR_CLIENT_ID&client_secret=YOUR_CLIENT_SECRET&grant_type=authorization_code&redirect_uri=YOUR_REGISTERED_REDIRECT_URI&code=CODE
        /*
         * host:api.weibo.com
         * path:/oauth2/access_token
         * client_id:2349338179
         * client_secret:5722376ad9a6e686a988c9480d412a38
         * grant_type:authorization_code
         * redirect_uri:http://127.0.0.1:9000/oauth2/weibo/success
         * code:code
         * */
        Map<String,String> header = new HashMap<>();
        Map<String,String> query = new HashMap<>();
        //跳转到其他页面
        Map<String,String> map = new HashMap<>();
        map.put("client_id","2349338179");
        map.put("client_secret","5722376ad9a6e686a988c9480d412a38");
        map.put("grant_type","authorization_code");
        map.put("redirect_uri","http://127.0.0.1:9000/oauth2/weibo/success");
        map.put("code",code);
        SocailUser socailUser  =null;
        HttpResponse response  = null;
        try {
            response = HttpUtils.doPost("https://api.weibo.com","/oauth2/access_token","post",header,query,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(response.getStatusLine().getStatusCode() == 200){
            //成功获取到了accessToken
            String jsonStr = null;
            try {
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获取的结果转化为对象格式
            socailUser = ChangeJsonTools.stringToObj(jsonStr, SocailUser.class);
            System.out.println("返回结果为:"+socailUser);
            //获得了当前授权登录的用户信息
            //1.若当前用户是第一次进入网站,自动注册进来 => 为当前社交用户生成一个会员信息账号，以后这个社交账号就对应对应的会员
            VipMember vipMember =  this.addMemberInfoFromSocialUserService.addMemberInfoFromWeibo(socailUser);
            if (vipMember == null){
               return  null;
            }
            //不为空 就封装用户登录信息
            UserFinishLoginResult userFinishLoginResult = new UserFinishLoginResult();
            //设置编号
            String uid = UUID.randomUUID().toString().replaceAll("-","");
            userFinishLoginResult.setUid(uid);
            //拿到第三方登录用户的昵称
            userFinishLoginResult.setUserName(vipMember.getNickname());
            //拿到第三方登录用户的头像
            userFinishLoginResult.setUserHeader(vipMember.getHeader());
            //拿到第三方登录用户
            Double currGrouthProportion  = this.memberLeaveMapper.getCurrGroupProportion(vipMember.getLevelId());
            logger.info("评分:"+currGrouthProportion);
            logger.info("获得评分比例:"+currGrouthProportion * 100);
            userFinishLoginResult.setCurrGrouthproportion(currGrouthProportion * 100);
            //拿到第三方登录用户的等级名称
            String leaveName  = this.memberLeaveMapper.getLeaveNameOfCurrSocialUser(vipMember.getLevelId());
            userFinishLoginResult.setUserMemberLname(leaveName);
            logger.info("封装后的结果为:"+userFinishLoginResult);
            return  userFinishLoginResult;
        }else{
            return  null;
        }
    }
}
