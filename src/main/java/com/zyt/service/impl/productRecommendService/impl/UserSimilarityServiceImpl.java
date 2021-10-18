package com.zyt.service.impl.productRecommendService.impl;

import com.zyt.entity.UserSimilarity;
import com.zyt.service.productRecommendService.UserSimilarityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.productRecommendService.impl
 * @ClassName: UserSimilarityServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 21:01 2021/4/20
 * @Version: 1.0
 */
@Service(value = "UserSimilarityService")
public class UserSimilarityServiceImpl implements UserSimilarityService {

    @Override
    public List<UserSimilarity> UserSimilarityByUId(int uid) {

        return null;
    }
}
