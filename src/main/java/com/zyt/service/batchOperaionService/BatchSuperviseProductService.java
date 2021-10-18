package com.zyt.service.batchOperaionService;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: BatchSuperviseProductService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 批量管理商品信息服务层
 * @Date: 16:23 2021/1/27
 * @Version: 1.0
 */
public interface BatchSuperviseProductService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 批量管理商品信服务层
      * @Return:
      * @Exception:
      * @Date: 2021/1/27 16:23
      * @Param: * @param null
      * @Return:
      */
    public int BatchAddProduct(MultipartFile file);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 批量删除商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:06
      * @Param: * @param null
      * @Return:
      */
    public int BatchDeleteProduct(String [] params);
}
