package com.zyt.service.productService;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.productService
 * @ClassName: ProductClickService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品点击服务层
 * @Date: 18:18 2021/4/17
 * @Version: 1.0
 */
public interface ProductClickService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品点击量
      * @Return:
      * @Exception:
      * @Date: 2021/4/17 18:20
      * @Param: * @param null
      * @Return:
      */
    public void  updateProductClickCounts(Integer productId);
}
