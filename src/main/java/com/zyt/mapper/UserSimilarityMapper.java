package com.zyt.mapper;

import com.zyt.entity.UserSimilarity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: UserSimilarityMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 22:49 2021/4/20
 * @Version: 1.0
 */
@Mapper
@Repository
public interface UserSimilarityMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加商品相似度信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/20 22:51
      * @Param: * @param null
      * @Return:
      */
    public int createUserSimilarity(@Param("similarities") List<UserSimilarity> similarities);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 获得当前用户的相似度
      * @Return:
      * @Exception:
      * @Date: 2021/4/27 14:43
      * @Param: * @param null
      * @Return:
      */
    public List<UserSimilarity> getUserSimilarityInfo();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 删除所有的信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/27 14:47
      * @Param: * @param null
      * @Return:
      */
    public void deleteUserSimilarityInfo();
}
