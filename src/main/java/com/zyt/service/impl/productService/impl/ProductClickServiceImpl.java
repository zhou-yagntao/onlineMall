package com.zyt.service.impl.productService.impl;

import com.zyt.mapper.ProductClickMapper;
import com.zyt.service.productService.ProductClickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.productService.impl
 * @ClassName: ProductClickServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品点击服务层实现模块
 * @Date: 18:19 2021/4/17
 * @Version: 1.0
 */
@Service(value = "productClickService")
public class ProductClickServiceImpl  implements ProductClickService {

    @Autowired
    private ProductClickMapper productClickMapper;

    @Override
    public void updateProductClickCounts(Integer productId) {
        this.productClickMapper.updateProductClickCounts(productId);
    }
}
