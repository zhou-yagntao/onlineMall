package com.zyt.service.topFiveProductService;

import com.zyt.entity.vo.TopFiveProductVo;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.topFiveProductService
 * @ClassName: TopFiveProductService
 * @Author: zhou_yangtao@yeah.net
 * @Description: topN商品信息
 * @Date: 12:29 2021/4/17
 * @Version: 1.0
 */
public interface TopFiveProductService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:topN商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/17 12:36
      * @Param: * @param null
      * @Return:
      */
    public List<TopFiveProductVo> GetTopFiveProduct(String brandName);
}
