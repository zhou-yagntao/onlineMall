package com.zyt.service.impl.productRecommendService.impl;

import com.zyt.algorithm.CollaborativeFilteringRecommendAlgorith;
import com.zyt.entity.Product;
import com.zyt.entity.ProductClick;
import com.zyt.entity.UserActive;
import com.zyt.entity.UserSimilarity;
import com.zyt.entity.vo.RecommendVo;
import com.zyt.mapper.*;
import com.zyt.service.productRecommendService.ProductRecommendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.productRecommendService.impl
 * @ClassName: ProductRecommendServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品推荐服务实现层
 * @Date: 20:48 2021/4/20
 * @Version: 1.0
 */
@Service(value = "productRecommendService")
public class ProductRecommendServiceImpl implements ProductRecommendService {

    private Logger logger = LoggerFactory.getLogger(ProductRecommendServiceImpl.class);

    @Autowired
    private VipMemberMapper vipMemberMapper;

    @Autowired
    private ProductClickMapper productClickMapper;

    @Autowired
    private UserSimilarityMapper userSimilarityMapper;


    @Autowired
    private ProductMapper productMapper;

    /**
     * @Method: productRecommendForUser
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得为用户推荐的商品信息
     * @Return: java.util.List<com.zyt.entity.vo.RecommendVo>
     * @Exception:
     * @Date: 2021/4/20 20:49
     * @Param: * @param username
     * @Return: java.util.List<com.zyt.entity.vo.RecommendVo>
     */
    @Override
    @Transactional
    public List<RecommendVo> productRecommendForUser(String username) {
        //根据用户名去获得用户id
        int userId = this.vipMemberMapper.getUserIdOfCurrVipMember(username);
        logger.info("userId  = "+userId);
        List<UserSimilarity> userSimilarityInfo = this.userSimilarityMapper.getUserSimilarityInfo();
        logger.info("--------------------->"+userSimilarityInfo);
        if (userSimilarityInfo == null){
            addUserSimilarityInfo();
        }else{
            this.userSimilarityMapper.deleteUserSimilarityInfo();
            addUserSimilarityInfo();
        }
        Long user_id = 1L;
        List<UserSimilarity> collect = userSimilarityInfo.stream().filter(userSimilarity -> userSimilarity.getUserId().equals(user_id)).collect(Collectors.toList());
        logger.info("获得的用户相似度信息为:"+collect);
        Long user_Id  = Long.valueOf(String.valueOf(userId));
        List<Long> similarityBetweenUsers = CollaborativeFilteringRecommendAlgorith.getSimilarityBetweenUsers(user_Id, userSimilarityInfo, 10);
        logger.info("获得的与当前用户top10的相似度信息:"+similarityBetweenUsers);
        List<Integer> productId = this.productClickMapper.getProductIdByUserId(similarityBetweenUsers);
        logger.info("获得的商品id为:"+productId);
        //根据商品id获得商品信息
        List<Product> productInfo = this.productMapper.getProductInfosByProdId(productId);
        List<RecommendVo> recommedVoList = productInfo.stream().map(product -> {
            RecommendVo recommendVo = new RecommendVo();
            recommendVo.setProdId(product.getProdId());
            recommendVo.setProdImg(product.getProdImage());
            recommendVo.setProdName(product.getProdName());
            recommendVo.setProdPrice(product.getProdNvipAllPrice());
            recommendVo.setStoreName(product.getProStoreName());
            return recommendVo;
        }).collect(Collectors.toList());
        return recommedVoList != null ? recommedVoList : null;

    }

    private  void addUserSimilarityInfo(){
        List<ProductClick> userActiveInfo = this.productClickMapper.getUserActiveInfo();
        logger.info("=====>"+userActiveInfo.toString());
        //将用户的购买行为组装成一个map,key为userId，value也是一个map，这个map记录的是每个类目下的商品信息以及它对应的点击量
        ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> concurrentHashMapConcurrentHashMap = CollaborativeFilteringRecommendAlgorith.assembleBehaviors(userActiveInfo);
        logger.info("------------->"+concurrentHashMapConcurrentHashMap);
        //计算用户与用户之间的相似性，返回计算出的用户与用户之间的相似度对象
        List<UserSimilarity> similarities = CollaborativeFilteringRecommendAlgorith.calcSimilarityBetweenUsers(concurrentHashMapConcurrentHashMap);
        for(UserSimilarity value : similarities){
            logger.info("封装的相似度对象商品信息为:"+value.toString());
        }
        this.userSimilarityMapper.createUserSimilarity(similarities);
    }
}
