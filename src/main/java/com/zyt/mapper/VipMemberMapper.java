package com.zyt.mapper;

import com.zyt.entity.VipMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: VipMemberMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员信息持久层
 * @Date: 13:56 2021/2/21
 * @Version: 1.0
 */
@Mapper
@Repository(value = "vipMemberMapper")
public interface VipMemberMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据社交账号id获取用户会员信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 14:58
      * @Param: * @param null
      * @Return:
      */
    public VipMember getVipMemberInfoBySocialId(@Param("socialId") String socialId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:当前用户以及注册过只需要修改当前社交账号令牌信息以及过期时间
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 15:06
      * @Param: * @param null
      * @Return:
      */
    public int updateVipMemeberInfoByVid(@Param("vInfoId")Integer vInfoId,@Param("newAccessToken")String newAccessToken,@Param("newExpiresIn")String newExpiresIn);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加社交信息到数据库
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 18:52
      * @Param: * @param null
      * @Return:
      */
    public int addVipMemeberInfo(@Param("vipMember") VipMember vipMember);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得用户会员等级编号
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 18:53
      * @Param: * @param null
      * @Return:
      */
    public Integer getLeaveIdOfFinally();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户的等级编号
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 18:54
      * @Param: * @param null
      * @Return:
      */
    public Integer getLeaveIdOfCurrentSocialId(String socialId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据社交账号查询当前社交用户的成长值
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 19:29
      * @Param: * @param null
      * @Return:
      */
    public int getGrouthOfSocailAccount(String socialId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:拿到要修改的成长值
      * @Return:
      * @Exception:
      * @Date: 2021/2/22 12:25
      * @Param: * @param null
      * @Return:
      */
    public  void updateGrouthOfVipMemberOfCurrSocialId(@Param("currGrouth") int currGrouth,@Param("socialId") String socialId);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return:
     * @Exception:
     * @Date: 2021/2/22 12:26
     * @Param: * @param null
     * @Return:
     */
    public Integer getSoonUpdateGrouth(@Param("socialId") String socialId);


    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前用户的id
     * @Return:
     * @Exception:
     * @Date: 2021/2/24 16:45
     * @Param: * @param null
     * @Return:
     */
    public int getUserIdByUserName(@Param("userName")String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据用户id获得用户真实姓名
      * @Return: 
      * @Exception: 
      * @Date: 2021/2/27 12:25
      * @Param: * @param null
      * @Return: 
      */
    public String getUserNameByUserId(@Param("userId") int userId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改会员信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/5 15:10
      * @Param: * @param null
      * @Return:
      */
    public void updateGrouthAndInternalOfCuurentUser(@Param("memberId") Integer memberId, @Param("growth") Integer growth, @Param("integration") Integer integration);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得是否存在当前用户信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/5 15:15
      * @Param: * @param null
      * @Return:
      */
    public VipMember getVipMemberInfoOfCurrMemeberName(@Param("subStrBirth") String subStrBirth, @Param("memeberName") String memeberName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户的id信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/20 20:52
      * @Param: * @param null
      * @Return:
      */
    public int getUserIdOfCurrVipMember(@Param("username") String username);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/5/29 17:03
      * @Param: * @param null
      * @Return:
      */
    public void addRelativeVipInfo(@Param("username")String username,@Param("nickname")String nickname);
}


