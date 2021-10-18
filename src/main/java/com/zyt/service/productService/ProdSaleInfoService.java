package com.zyt.service.productService;

import com.zyt.entity.Product;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: ProdSaleInfoService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 猜你喜欢商品信息服务层
 * @Date: 12:09 2021/2/16
 * @Version: 1.0
 */
public interface ProdSaleInfoService {

    public List<Product> getProdTopSaleInfo();
}
