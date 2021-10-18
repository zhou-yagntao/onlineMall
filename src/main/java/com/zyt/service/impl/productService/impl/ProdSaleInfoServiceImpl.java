package com.zyt.service.impl.productService.impl;

import com.zyt.entity.ProdSaleInfo;
import com.zyt.entity.Product;
import com.zyt.mapper.ProdSaleInfoMapper;
import com.zyt.mapper.ProductMapper;
import com.zyt.service.productService.ProdSaleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: ProdSaleInfoServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 猜你喜欢商品信息服务实现层
 * @Date: 12:09 2021/2/16
 * @Version: 1.0
 */
@Service(value = "prodSaleInfoService")
public class ProdSaleInfoServiceImpl implements ProdSaleInfoService {

    private Logger logger = LoggerFactory.getLogger(ProdSaleInfoServiceImpl.class);

    @Autowired
    private ProdSaleInfoMapper prodSaleInfoMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getProdTopSaleInfo() {
        //获取前top 商品信息
        List<ProdSaleInfo> prodSaleInfos = this.prodSaleInfoMapper.getProdSaleInfo();
        int [] saleCounts  = new int[prodSaleInfos.size()];
        for (int i = 0; i < prodSaleInfos.size(); i++) {
            saleCounts[i] = prodSaleInfos.get(i).getSaleCount();
        }
        Arrays.sort(saleCounts);
        for (int i = saleCounts.length-1; i >=0 ; i--) {
            logger.info("排序后的结果为:"+saleCounts[i]);
        }
        int [] topNineProdInfo = new int[9];
        topNineProdInfo[0] = saleCounts[saleCounts.length-1];
        topNineProdInfo[1] = saleCounts[saleCounts.length-2];
        topNineProdInfo[2] = saleCounts[saleCounts.length-3];
        topNineProdInfo[3] = saleCounts[saleCounts.length-4];
        topNineProdInfo[4] = saleCounts[saleCounts.length-5];
        topNineProdInfo[5] = saleCounts[saleCounts.length-6];
        topNineProdInfo[6] = saleCounts[saleCounts.length-7];
        topNineProdInfo[7] = saleCounts[saleCounts.length-8];
        topNineProdInfo[8] = saleCounts[saleCounts.length-9];
        for (int i = 0; i < topNineProdInfo.length; i++) {
            logger.info("转化后结果为:"+topNineProdInfo[i]);
        }
        List<Integer> prodSalePrdId = new ArrayList<>();
        for (int i = 0; i < topNineProdInfo.length; i++) {
            prodSalePrdId.add(this.prodSaleInfoMapper.getProdSaleInfoProdId(topNineProdInfo[i]));
        }
        logger.info("获得商品信息编号为:"+prodSalePrdId.size());
        List<Product> topNineProductsInfo = this.productMapper.getProductsInfoByProdId(prodSalePrdId);
         logger.info("查询结果为:"+topNineProductsInfo.toString());
        return  topNineProductsInfo != null ? topNineProductsInfo : null;
    }
}
