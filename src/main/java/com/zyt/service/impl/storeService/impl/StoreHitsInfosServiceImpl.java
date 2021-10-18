package com.zyt.service.impl.storeService.impl;

import com.zyt.entity.Store;
import com.zyt.entity.StoreHits;
import com.zyt.entity.vo.TopFiveStoreInfoTo;
import com.zyt.mapper.StoreHitsMapper;
import com.zyt.mapper.StoreMapper;
import com.zyt.service.storeService.StoreHitsInfosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: StoreHitsInfosServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品点击量服务层实现层
 * @Date: 10:46 2021/2/10
 * @Version: 1.0
 */
@Service(value = "storeHitsInfosService")
public class StoreHitsInfosServiceImpl implements StoreHitsInfosService {

    private Logger logger = LoggerFactory.getLogger(StoreHitsInfosServiceImpl.class);

    @Autowired
    private StoreHitsMapper storeHitsMapper;

    @Autowired
    private StoreMapper storeMapper;

    /**
     * @Method: GetStoreHitsInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得店铺点击量信息
     * @Return: java.util.List<com.zyt.entity.StoreHits>
     * @Exception:
     * @Date: 2021/2/10 11:07
     * @Param: * @param
     * @Return: java.util.List<com.zyt.entity.StoreHits>
     * @return
     */
    @Override
    public List<TopFiveStoreInfoTo> GetStoreHitsInfos() {
        //获得所有的店铺点击量新
        List <StoreHits> storeHitsList =  this.storeHitsMapper.getStoreHitsInfos() != null ? this.storeHitsMapper.getStoreHitsInfos() : null;
        int [] hitsInfo  = new int[storeHitsList.size()];
        for (int i = 0; i < storeHitsList.size(); i++) {
            hitsInfo[i] = storeHitsList.get(i).getSotreHits();
        }
        //获得店铺点击量数据
        logger.info("获得点击量数据:"+hitsInfo);
        //对评级进行排序
        Arrays.sort(hitsInfo);
        int [] hitsProdInfos = new int [5];
        logger.info("排序后获得点击量数据:"+hitsInfo);
        for (int i = hitsInfo.length-1; i >= 0 ; i--) {
            logger.info("结果为:"+hitsInfo[i]);
        }
        hitsProdInfos[0] = hitsInfo[hitsInfo.length-1];
        hitsProdInfos[1] = hitsInfo[hitsInfo.length-2];
        hitsProdInfos[2] = hitsInfo[hitsInfo.length-3];
        hitsProdInfos[3] = hitsInfo[hitsInfo.length-4];
        hitsProdInfos[4] = hitsInfo[hitsInfo.length-5];
        for (int i = 0; i < hitsProdInfos.length; i++) {
            logger.info("结果为:"+hitsProdInfos[i]);
        }
        //通过评分信息获得店铺的编号
        List<Integer> storeId = new ArrayList<>();
        for (int i = 0 ; i < hitsProdInfos.length ; i++){
            storeId.add(this.storeHitsMapper.getStoreIdByHits(hitsProdInfos[i]) != null ? this.storeHitsMapper.getStoreIdByHits(hitsProdInfos[i]):null);
        }

        logger.info("获得店铺信息为:"+storeId);
        List<Store> topFiveStoreInfo = this.storeMapper.getTopFiveStoreInfo(storeId);
        List<TopFiveStoreInfoTo> topFiveStoreInfoTos =topFiveStoreInfo.stream().map(topFiveStoreInfos->{
            TopFiveStoreInfoTo topFiveStoreInfoTo = new TopFiveStoreInfoTo();
            topFiveStoreInfoTo.setStoreId(topFiveStoreInfos.getStoreId());
            topFiveStoreInfoTo.setStoreName(topFiveStoreInfos.getStoreName());
            topFiveStoreInfoTo.setStoreImg(topFiveStoreInfos.getStoreImg());
            int storeHits = this.storeHitsMapper.getStoreHitsByStoreId(topFiveStoreInfos.getStoreId());
            topFiveStoreInfoTo.setStoreHtis(storeHits);
            return  topFiveStoreInfoTo;
        }).collect(Collectors.toList());
        logger.info("获得的 top_5店铺信息为:"+topFiveStoreInfoTos);
        return  topFiveStoreInfoTos != null ? topFiveStoreInfoTos : null;
    }
}
