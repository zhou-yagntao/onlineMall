package com.zyt.service.productRecommendService;

import com.zyt.entity.UserSimilarity;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.productRecommendService
 * @ClassName: UserSimilarityService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户相似度计算
 * @Date: 20:56 2021/4/20
 * @Version: 1.0
 */
public interface UserSimilarityService {

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:查询与当前用户相似的用户信息
       * @Return:
       * @Exception:
       * @Date: 2021/4/20 21:00
       * @Param: * @param null
       * @Return:
       */
     public List<UserSimilarity> UserSimilarityByUId(int uid);
}
