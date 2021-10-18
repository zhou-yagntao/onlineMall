package com.zyt.service.impl.memberService.impl.addMemberInfoByWeiBo.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyt.entity.MemeberLeave;
import com.zyt.entity.SocailUser;
import com.zyt.entity.VipMember;
import com.zyt.mapper.MemberLeaveMapper;
import com.zyt.mapper.VipMemberMapper;
import com.zyt.service.memberService.addMemberInfoByWeiBo.AddMemberInfoFromSocialUserService;
import com.zyt.utils.ChangeJsonTools;
import com.zyt.utils.DateUtils;
import com.zyt.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.memberService.impl.addMemberInfoByWeiBo.impl
 * @ClassName: AddMemberInfoFromSocialUserImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 14:37 2021/2/21
 * @Version: 1.0
 */
@Service(value = "addMemberInfoFromSocialUserService")
public class AddMemberInfoFromSocialUserServiceImpl implements AddMemberInfoFromSocialUserService {

    private Logger logger  = LoggerFactory.getLogger(AddMemberInfoFromSocialUserServiceImpl.class);

    @Autowired
    private VipMemberMapper vipMemberMapper;

    @Autowired
    private MemberLeaveMapper memberLeaveMapper;

    /**
     * @Method: addMemberInfoFromWeibo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:添加微博授权获得的信息到会员信息表中
     * @Return: com.zyt.entity.VipMember
     * @Exception:
     * @Date: 2021/2/21 14:40
     * @Param: * @param socailUser
     * @Return: com.zyt.entity.VipMember
     */
    @Override
    @Transactional
    public VipMember addMemberInfoFromWeibo(SocailUser socailUser) {
        //社交账号合并了登录注册两部分
        //1.获得社交登录的用户id
        String userId = socailUser.getUid();
        //判断当前社交用户是否已经登录过系统
        VipMember vipMember  = this.vipMemberMapper.getVipMemberInfoBySocialId(userId);
        //若查询的数据不为空 则表示已经注册过
        if(null != vipMember){
            //若已经有了当前用户信息  我们只需要修改一下令牌信息以及过期时间即可
            //1.获得当前社交id已有的会员信息编号
            Integer vInfoId = vipMember.getVid();
            //获得新的令牌信息
            String newAccessToken = socailUser.getAccess_token();
            //获得新的过期时间
            String newExpiresIn = String.valueOf(socailUser.getExpires_in());
            //调用更新数据方法更新数据库中的数据
            this.vipMemberMapper.updateVipMemeberInfoByVid(vInfoId,newAccessToken,newExpiresIn);
            //同时更新会员对象中的令牌以及过期时间的信息
            vipMember.setAccessToken(newAccessToken);
            vipMember.setExpiressIn(newExpiresIn);
            //修改会员等级信息以及会员信息
            this.UpdateVipMemberInfoAndMemberLeaveInfo(socailUser);
            //返回我们获得的会员信息
            return  vipMember;
        }else{
            //未查到当前社交账号对应的会员信息
            VipMember socialUserRegister = new VipMember();
            //查询当前社交账号的用户信息
            Map<String,String> query = new HashMap<>();
            //获得传入的设社交账号的令牌信息
            query.put("access_token",socailUser.getAccess_token());
            query.put("uid",socailUser.getUid());
            HttpResponse response  = null;
            try {
                response = HttpUtils.doGet("https://api.weibo.com","/2/users/show.json","get",new HashMap<String,String>(),query);
            } catch (Exception e) {
                e.printStackTrace();
            }
            VipMember socialUserInfo = new VipMember();
            //获取查询数据状态
            if(response.getStatusLine().getStatusCode() == 200){
                //查询数据查询成功
                //1.获取的数据为json格式
                String json  = null;
                try {
                   json = EntityUtils.toString(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //将json格式的数据转化为json对象格式数据
                JSONObject jsonObject  = JSON.parseObject(json);
                //对应的姓名
                String name = jsonObject.getString("screen_name");
                socialUserInfo.setUsername(name);
                //对应的昵称姓名
                String nickName = jsonObject.getString("name");
                socialUserInfo.setNickname(nickName);
                //用户头像
                String userHeader = String.valueOf(jsonObject.get("profile_image_url"));
                socialUserInfo.setHeader(userHeader);
                //用户性别
                String userSex  = jsonObject.getString("gender");
                socialUserInfo.setGender("m".equals(userSex) ? "男" : "女");
                //用户所在城市
                String userCity = jsonObject.getString("location");
                socialUserInfo.setCity(userCity);
                //用户个性签名
                String userSigns  = jsonObject.getString("description");
                socialUserInfo.setSigns(userSigns);
                int month = new Random().nextInt(12);
                System.out.println(month);
                int day  =  0;
                if(month == 2){
                    day  = new Random().nextInt(28);
                }else{
                    day = new Random().nextInt(31);
                }
                String createTime = "1998"+"-"+month+"-"+day;
                logger.info("创建时间为:"+createTime);
                Date birth  = null;
                try {
                    birth = DateUtils.stringDate(createTime,"yyyy-MM-dd");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("格式话后的创建时间为:"+birth);
                socialUserInfo.setBirth(birth);
                //设置注册时间
                Date registerTime = new Date();
                String registerTime_str = DateUtils.dateString(registerTime,"yyyy-MM-dd HH:mm:ss");
                logger.info("注册时间为:"+registerTime);
                logger.info("转化后的时间格式为:"+registerTime_str);
                Date newregisterTime = null;
                try {
                    newregisterTime = DateUtils.stringDate(registerTime_str.substring(0,10),"yyyy-MM-dd");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                socialUserInfo.setCreate_time(newregisterTime);
                //设置是否启用
                socialUserInfo.setStatuss(true);
                //设置等级编号
                Integer leave_id  = this.vipMemberMapper.getLeaveIdOfFinally();
                int leaveId = 0;
                if (leave_id == null){
                    leaveId = 1;
                }else{
                    leaveId  = (int)leave_id + 1;
                }
                //int leaveId = this.vipMemberMapper.getLeaveIdOfFinally() == null ? 1:this.vipMemberMapper.getLeaveIdOfFinally()+1;
                logger.info("等级编号为:"+leaveId);
                socialUserInfo.setLevelId(leaveId);
            }
            //社交账号编号Id
            String socialId  = socailUser.getUid();
            socialUserInfo.setSocialUserId(socialId);
            //社交账号令牌
            String access_token =  socailUser.getAccess_token();
            socialUserInfo.setAccessToken(access_token);
            //过期时间
            String expires_in = String.valueOf(socailUser.getExpires_in());
            socialUserInfo.setExpiressIn(expires_in);
            logger.info("获取的结果为:"+socialUserInfo.toString());
            //1.插入社交账号信息到数据到数据库
            int result1 = this.vipMemberMapper.addVipMemeberInfo(socialUserInfo);
            //2.插入会员等级数据到数据库
            //2.1.根据社交账号查询当前用户的等级编号
            int levelId  = this.vipMemberMapper.getLeaveIdOfCurrentSocialId(socailUser.getUid());
            String leaeveName = "1级";
            int currGrouth = 0;
            int grouthPoint  = 50;
            Boolean defaultStatus  = false;
            Boolean priviledgeMemberPrice = true;
            Boolean priviledgeBirthday = true;
            String note = "";
            MemeberLeave memeberLeave = new MemeberLeave();
            memeberLeave.setLname(leaeveName);
            memeberLeave.setCurrGrouth(currGrouth);
            memeberLeave.setGrowthPoint(grouthPoint);
            memeberLeave.setDefaultStatus(defaultStatus);
            memeberLeave.setPriviledgeBirthday(priviledgeBirthday);
            memeberLeave.setPriviledgeBirthday(priviledgeBirthday);
            memeberLeave.setNote(note);
            memeberLeave.setMemberLeaveId(levelId);
            logger.info("会员等级结果为:"+memeberLeave.toString());
            int result2 =  this.memberLeaveMapper.addMemberLeaveInfo(memeberLeave);
            //int result2 = this.memberLeaveMapper.addMemberLeaveInfo(leaeveName,currGrouth,grouthPoint,defaultStatus,priviledgeMemberPrice,priviledgeBirthday,note,levelId);
            if(result1 > 0 && result2 > 0){
                return  socialUserInfo;
            }
        }
        return null;
    }

     /**
      * @Method: UpdateVipMemberInfoAndMemberLeaveInfo
      * @Author: Justin
      * @Version  1.0
      * @Description:封装修改登录信息相关数据
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 20:28
      * @Param: * @param null
      * @Return:
      */
    private  void UpdateVipMemberInfoAndMemberLeaveInfo(SocailUser socailUser){
        //1.根据会员社交账号id查询会员的等级并进行修改
        //1.1、通过id拿到拿到成长值
        Integer grouthOfSocailAccount = this.vipMemberMapper.getGrouthOfSocailAccount(socailUser.getUid());
        int grouth = 0;
        if(grouthOfSocailAccount != null){
            grouth = grouthOfSocailAccount.intValue();
        }
        //1.1.1.修改会员等级表成长值+通过id拿到拿到成长值
        //1.1.1.1.获得当前社交账号对应的id
        int leaveId = this.vipMemberMapper.getLeaveIdOfCurrentSocialId(socailUser.getUid());
        //1.1.1.2.修改当前id的当前成长值
        int currGrouth = this.memberLeaveMapper.getCurrentGrouthOfLeaveId(leaveId) + grouth;
        //1.1.2.会员成长值与当前等级所需要的成长值进行比较 若大于所需的成长值则升级(1.当前成长值 = 修改会员等级表成长值+通过id拿到拿到成长值 - id当前等级所需的成长值)
        //获得成长值
        int growthPoint = this.memberLeaveMapper.getGrowthPointOfCurLeaveId(leaveId);
        //获得当前成长值的等级名称
        String nameOfCurrLeaveId = this.memberLeaveMapper.getLnameOfCurLeaveId(leaveId);
        //大于所需的成长值则升级
        if(currGrouth > growthPoint){
            //1.1.2.1.修改等级名称 + 当前等级所需的成长值
            int newCurrGrouth  = currGrouth - growthPoint;
            String newNameOfCurrLeaveId = nameOfCurrLeaveId.substring(0,1);
            //升级
            //将上一等级的成长值置为0
            this.memberLeaveMapper.updateMemberLeaveGrouthPointToZero(leaveId);
            //修改升级之后的数据
            String NameOfMemAfterUpgrade = String.valueOf((Integer.parseInt(nameOfCurrLeaveId)+1));
            int newGrouthPoint = Integer.valueOf(growthPoint + 50 * (Integer.parseInt(nameOfCurrLeaveId)+1));
            this.memberLeaveMapper.updateMemberLeaveInfoOfCurrentLeaveId(leaveId,NameOfMemAfterUpgrade,newCurrGrouth,newGrouthPoint);
        }else{
            this.memberLeaveMapper.updateCurrGrouthOfCurrLeaveId(currGrouth,leaveId);
        }
        //1.1.1.3.修改会员表中成长值 = 会员表中成长值 - 修改当前id的当前成长值
        //拿到即将修改的用户成长值
        Integer soonUpdateGrouth = this.vipMemberMapper.getSoonUpdateGrouth(socailUser.getUid());
        int soonGrouth = 0;
        if (soonUpdateGrouth != null){
            soonGrouth = soonUpdateGrouth.intValue();
        }else{
            soonGrouth = 0;
        }
        int submitcurrGrouth = soonGrouth >= currGrouth ? soonGrouth -currGrouth : 0;

        this.vipMemberMapper.updateGrouthOfVipMemberOfCurrSocialId(submitcurrGrouth,socailUser.getUid());
    }
}
