package com.zyt.service.impl.productService.impl;

import com.zyt.entity.Product;
import com.zyt.entity.ProductSaleInfo;
import com.zyt.entity.vo.ProductDetailsTo;
import com.zyt.mapper.BrandMapper;
import com.zyt.mapper.ProductMapper;
import com.zyt.mapper.ProductSaleInfoMapper;
import com.zyt.service.productService.ProductClickService;
import com.zyt.service.productService.ProductDetailsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.productService.impl
 * @ClassName: ProductDetailsInfoServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品详情服务层实现模块
 * @Date: 12:39 2021/2/23
 * @Version: 1.0
 */
@Service(value = "productDetailsInfoService")
public class ProductDetailsInfoServiceImpl implements ProductDetailsInfoService {

    private Logger logger = LoggerFactory.getLogger(ProductDetailsInfoServiceImpl.class);

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private ProductSaleInfoMapper productSaleInfoMapper;

    @Autowired
    private ProductClickService productClickService;

    /**
     * @Method: GetProductDetailsInfoByProdIdAndStoreName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得商品详细信息
     * @Return: java.util.List<java.lang.Object>
     * @Exception:
     * @Date: 2021/2/24 10:06
     * @Param: * @param prodId
     * @param storeName
     * @Return: java.util.List<java.lang.Object>
     */
    @Override
    public List<ProductDetailsTo> GetProductDetailsInfoByProdIdAndStoreName(String prodId, String storeName) {
        Integer productId = Integer.parseInt(prodId);
        List<Product> productList = this.productMapper.getProductDetailsByProdIdAndStoreName(productId,storeName);
        List<ProductDetailsTo> productDetailsTosInfo = productList.stream().map(productInfo ->{
            ProductDetailsTo productDetailsTo = new ProductDetailsTo();
            //分组商品id
            productDetailsTo.setProductId(productInfo.getProdId());
            //封装商品名称
            productDetailsTo.setProductName(productInfo.getProdName());
            //封装店铺名称
            productDetailsTo.setStoreName(storeName);
            //封装品牌名称
            String  brandName = this.brandMapper.getBrandNameByBrandId(productInfo.getStoreBrandId());
            productDetailsTo.setBrandName(brandName);
            //封装商品价格
            String NvipPrice = productInfo.getProdNvipAllPrice().replaceAll("元","");
            String VipPrice = productInfo.getProdVipAllPrice().replaceAll("元","");
            productDetailsTo.setProdNvipPrice(NvipPrice);
            productDetailsTo.setProdVipPrice(VipPrice);
            //封装商品推荐与
            productDetailsTo.setProdMoral(productInfo.getProdMoral());
            //封装商品图片
            productDetailsTo.setProductImg(productInfo.getProdImage());
            //封装商品销售数量
            List<ProductSaleInfo>productSaleInfo = this.productSaleInfoMapper.getProductSaleCountByProdId(productId);
            if (productSaleInfo.size() > 0){
                productDetailsTo.setSaleCounts(productSaleInfo.iterator().next().getSaleCount());
            }else{
                logger.info("查询到商品销售数量为0");
                productDetailsTo.setSaleCounts(0);
            }
            return productDetailsTo;
        }).collect(Collectors.toList());
        logger.info("获得封装的结果为:"+productDetailsTosInfo);
        //修改商品的点击量信息
        //this.productClickService.updateProductClickCounts(productId);
        return productDetailsTosInfo;

    }

    /**
     * @Method: getProductInfoOfCurrStore
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件获得所有的商品详细信息
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/3/8 21:23
     * @Param: * @param storeName
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> getProductInfoOfCurrStore(String storeName) {
        return this.productMapper.getProductInfoOfCurrStore(storeName) != null ?
               this.productMapper.getProductInfoOfCurrStore(storeName) :
               null;

    }

    /**
     * @Method: getProdIdByProdName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据商品名称获得商品id
     * @Return: int
     * @Exception:
     * @Date: 2021/3/9 16:15
     * @Param: * @param prodId
     * @Return: int
     */
    @Override
    public int getProdIdByProdName(String prodId,String storename) {
        return this.productMapper.getProdIdByProdName(prodId,storename) > 0 ?
               this.productMapper.getProdIdByProdName(prodId,storename) :
                0;

    }

    /**
     * @Method: getProductInfoOfCurrSecKillProdByProdId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据id获得当前商品信息
     * @Return: com.zyt.entity.Product
     * @Exception:
     * @Date: 2021/3/12 17:05
     * @Param: * @param prodId
     * @Return: com.zyt.entity.Product
     */
    @Override
    public Product getProductInfoOfCurrSecKillProdByProdId(Integer prodId) {
        return this.productMapper.getProductInfoOfCurrSecKillProdByProdId(prodId) != null ? this.productMapper.getProductInfoOfCurrSecKillProdByProdId(prodId) : null;
    }

    /**
     * @Method: getRandomProductImgInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:随机展示商品信息
     * @Return: java.util.List<java.lang.String>
     * @Exception:
     * @Date: 2021/4/10 22:01
     * @Param: * @param
     * @Return: java.util.List<java.lang.String>
     */
    @Override
    public List<String> getRandomProductImgInfo() {
        Integer [] prodId = new Integer[5];
        for (int i = 0; i < prodId.length; i++) {
            prodId[i] = (int)(Math.random()*1199)+1;
        }
        List<String> prodImgInfoByProdId = this.productMapper.getProdImgInfoByProdId(prodId);
        logger.info("=========>"+prodImgInfoByProdId);
        return prodImgInfoByProdId != null ? prodImgInfoByProdId : null;
    }

    /**
     * @Method: /
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获取当前品牌id的商品信息
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/4/17 12:51
     * @Param: * @param brandId
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> getProductInfoOfCurrBrandId(Integer brandId) {
        List<Product> products = this.productMapper.getProductInfoOfCurrBrandId(brandId);
        return products != null ? products : null;
    }
}
