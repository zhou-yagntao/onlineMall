package com.zyt.service.impl.topFiveProductService.impl;

import com.zyt.entity.Product;
import com.zyt.entity.vo.TopFiveProductVo;
import com.zyt.mapper.BrandMapper;
import com.zyt.mapper.ProductClickMapper;
import com.zyt.service.productService.ProductDetailsInfoService;
import com.zyt.service.topFiveProductService.TopFiveProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.topFiveProductService.impl
 * @ClassName: TopFiveProductServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description:
 * @Date: 12:30 2021/4/17
 * @Version: 1.0
 */
@Service(value = "topFiveProductService")
public class TopFiveProductServiceImpl implements TopFiveProductService {

    private Logger logger = LoggerFactory.getLogger(TopFiveProductServiceImpl.class);

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ProductDetailsInfoService productDetailsInfoService;

    @Autowired
    private ProductClickMapper productClickMapper;

    /**
     * @Method: GetTopFiveProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得topN商品信息
     * @Return: java.util.List<com.zyt.entity.vo.TopFiveProductVo>
     * @Exception:
     * @Date: 2021/4/17 12:37
     * @Param: * @param brandName
     * @Return: java.util.List<com.zyt.entity.vo.TopFiveProductVo>
     */
    @Override
    public List<TopFiveProductVo> GetTopFiveProduct(String brandName) {
        if (brandName == null){return  new ArrayList<>();}
        //根据品牌名称获得品牌id
        Integer brandId = this.brandMapper.getBrandIdOfCurrBrandName(brandName);
        //根据品牌id获得当前品牌下的所有商品信息
        List<Product> productsLists = this.productDetailsInfoService.getProductInfoOfCurrBrandId(brandId);
        List<TopFiveProductVo> collect = productsLists.stream().map(product -> {
            TopFiveProductVo productVo = new TopFiveProductVo();
            productVo.setProId(product.getProdId());
            productVo.setProdName(product.getProdName());
            productVo.setQuotations(product.getProdMoral());
            int productClick = this.productClickMapper.getProductClick(product.getProdId());
            productVo.setClickCounts(productClick);
            productVo.setProdImg(product.getProdImage());
            productVo.setStoreName(product.getProStoreName());
            return productVo;
        }).collect(Collectors.toList());
        for (TopFiveProductVo topFiveProductVo : collect){
            logger.info("商品信息:"+topFiveProductVo);
        }
        int start = (int)Math.random()*1000;
        collect.subList(start,start+6);
        logger.info("商品信息为:"+collect);
        return collect != null ? collect : null;
    }
}
