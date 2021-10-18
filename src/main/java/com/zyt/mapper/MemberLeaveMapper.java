package com.zyt.mapper;

import com.zyt.entity.MemeberLeave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: MemberLeave
 * @Author: zhou_yangtao@yeah.net
 * @Description: 会员等级持久层
 * @Date: 13:54 2021/2/21
 * @Version: 1.0
 */
@Mapper
@Repository(value = "memberLeaveMapper")
public interface MemberLeaveMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加会员等级信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/21 19:34
      * @Param: * @param null
      * @Return:
      */
     public  int addMemberLeaveInfo(@Param("memeberLeave")MemeberLeave memeberLeave);
     //public int addMemberLeaveInfo(@Param("leaeveName")String leaeveName,@Param("currGrouth") int currGrouth,@Param("grouthPoint")int grouthPoint,@Param("defaultStatus")Boolean defaultStatus,@Param("priviledgeMemberPrice") Boolean priviledgeMemberPrice,@Param("priviledgeBirthday")Boolean priviledgeBirthday,@Param("note")String note,@Param("levelId")int levelId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:通过会员等级Id查询当前成长值
       * @Return:
       * @Exception:
       * @Date: 2021/2/21 19:35
       * @Param: * @param null
       * @Return:
       */
     public int getCurrentGrouthOfLeaveId(int leaveId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据当前用户等级id查询到满级的成长值
       * @Return:
       * @Exception:
       * @Date: 2021/2/21 19:39
       * @Param: * @param null
       * @Return:
       */
     public int getGrowthPointOfCurLeaveId(@Param("leaveId") int leaveId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:获取当前用户等级id对应的等级名称
       * @Return:
       * @Exception:
       * @Date: 2021/2/21 19:40
       * @Param: * @param null
       * @Return:
       */
     public String getLnameOfCurLeaveId(int leaveId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:修改当前用户等级表中等级信息
       * @Return:
       * @Exception:
       * @Date: 2021/2/21 19:54
       * @Param: * @param null
       * @Return:
       */
     public int updateMemberLeaveInfoOfCurrentLeaveId(@Param("leaveId") int leaveId,@Param("nameOfCurLeadId")String nameOfCurLeadId,@Param("newCurrGrouth")int newCurrGrouth,@Param("newGrouthPoint") int newGrouthPoint);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据编号将前一等级所需的等级成长值置为0
       * @Return:
       * @Exception:
       * @Date: 2021/2/21 20:10
       * @Param: * @param null
       * @Return:
       */
     public  void  updateMemberLeaveGrouthPointToZero(int leaveId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据当前等级编号修改等级值
       * @Return:
       * @Exception:
       * @Date: 2021/2/21 20:10
       * @Param: * @param null
       * @Return:
       */
     public void  updateCurrGrouthOfCurrLeaveId(@Param("currGrouth") int currGrouth,@Param("leaveId") int  leaveId);


      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据编号查询当前成长值占比
       * @Return:
       * @Exception:
       * @Date: 2021/2/22 11:48
       * @Param: * @param null
       * @Return:
       */
     public Double  getCurrGroupProportion(@Param("leaveId")int  leaveId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description: 获得当前社交账号的等级名称
       * @Return:
       * @Exception:
       * @Date: 2021/2/22 11:53
       * @Param: * @param null
       * @Return:
       */
     public String getLeaveNameOfCurrSocialUser(@Param("leaveId")int  leaveId);

}
