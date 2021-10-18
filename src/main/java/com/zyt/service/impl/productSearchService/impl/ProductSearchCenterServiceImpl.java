package com.zyt.service.impl.productSearchService.impl;

import com.zyt.entity.StoreBrand;
import com.zyt.mapper.BrandMapper;
import com.zyt.service.productSearchService.ProductSearchCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: ProductSearchCenterServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品服务实现层
 * @Date: 19:42 2021/2/16
 * @Version: 1.0
 */
@Service(value = "productSearchCenterService")
public class ProductSearchCenterServiceImpl implements ProductSearchCenterService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @Method: GetProductBrandInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:商品搜索服务商品品牌信息
     * @Return: java.util.List<com.zyt.entity.StoreBrand>
     * @Exception:
     * @Date: 2021/2/16 19:44
     * @Param: * @param
     * @Return: java.util.List<com.zyt.entity.StoreBrand>
     */
    @Override
    public List<StoreBrand> GetProductBrandInfo() {
        return this.brandMapper.getAllProductBrandName() != null ? this.brandMapper.getAllProductBrandName() : null;

    }
}
