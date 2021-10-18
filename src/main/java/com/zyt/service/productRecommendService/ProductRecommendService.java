package com.zyt.service.productRecommendService;

import com.zyt.entity.vo.RecommendVo;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.productRecommendService
 * @ClassName: ProductRecommendService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 推荐模块服务层
 * @Date: 20:43 2021/4/20
 * @Version: 1.0
 */
public interface ProductRecommendService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:商品推荐
      * @Return:
      * @Exception:
      * @Date: 2021/4/20 20:48
      * @Param: * @param null
      * @Return:
      */
    public List<RecommendVo> productRecommendForUser(String username);
}
