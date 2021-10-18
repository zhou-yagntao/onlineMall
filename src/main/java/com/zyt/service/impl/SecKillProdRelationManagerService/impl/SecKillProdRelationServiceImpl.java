package com.zyt.service.impl.SecKillProdRelationManagerService.impl;

import com.zyt.entity.Product;
import com.zyt.entity.SecKillProdRelation;
import com.zyt.entity.Store;
import com.zyt.mapper.SecKillProdRelationMapper;
import com.zyt.service.SecKillProdRelationManagerService.SecKillProdRelationService;
import com.zyt.service.impl.SecKillSessionManagerService.impl.SecKillSessionManagerServiceImpl;
import com.zyt.service.productService.ProductDetailsInfoService;
import com.zyt.service.storeService.NewStoreSettledService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.SecKillProdRelationManagerService.impl
 * @ClassName: SecKillProdRelationServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀场次关联的商品信息服务层实现模块
 * @Date: 15:50 2021/3/8
 * @Version: 1.0
 */
@Service(value = "secKillProdRelationService")
public class SecKillProdRelationServiceImpl implements SecKillProdRelationService {

    private Logger logger = LoggerFactory.getLogger(SecKillSessionManagerServiceImpl.class);

     @Autowired
     private SecKillProdRelationMapper secKillProdRelationMapper;

     @Autowired
     private NewStoreSettledService newStoreSettledService;


     @Autowired
     private ProductDetailsInfoService productDetailsInfoService;

    /**
     * @Method: GetSeckillProRelationInfoBySessionId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前场次关联的商品信息
     * @Return: java.util.List<com.zyt.entity.SecKillProdRelation>
     * @Exception:
     * @Date: 2021/3/8 15:54
     * @Param: * @param sec_kill_id
     * @param currentPage
     * @param pageSize
     * @Return: java.util.List<com.zyt.entity.SecKillProdRelation>
     */
    @Override
    public List<SecKillProdRelation> GetSeckillProRelationInfoBySessionId(String sec_kill_id, String currentPage, String pageSize) {
        int initPageSize = Integer.parseInt(pageSize);
        int initCurrentPage = Integer.parseInt(currentPage);
        int start = (initCurrentPage - 1) * initPageSize;
        int end  = initPageSize;
        int secKillId = Integer.parseInt(sec_kill_id);
        logger.info("参数信息为:"+start+"\t"+end+"\t"+secKillId);
        List<SecKillProdRelation> secKillProdRelationList = this.secKillProdRelationMapper.getSeckillProRelationInfoBySessionId(secKillId,start,end);
        logger.info("秒杀商品信息为:"+secKillProdRelationList);
        return secKillProdRelationList != null ? secKillProdRelationList : null;


    }

    /**
     * @Method: GetSeckillProRelationCountsBySessionId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前场次关联的商品信息的数目
     * @Return: int
     * @Exception:
     * @Date: 2021/3/8 16:01
     * @Param: * @param seckill_id
     * @Return: int
     */
    @Override
    public int GetSeckillProRelationCountsBySessionId(String seckill_id) {
        int killId = Integer.parseInt(seckill_id);
        return this.secKillProdRelationMapper.getSeckillProRelationCountsBySessionId(killId) > 0 ?
               this.secKillProdRelationMapper.getSeckillProRelationCountsBySessionId(killId):
               0;

    }

    /**
     * @Method: GetRelativeProductInfoOfCurrKillSessionByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询秒杀商品信息
     * @Return: java.util.List<com.zyt.entity.SecKillProdRelation>
     * @Exception:
     * @Date: 2021/3/8 21:52
     * @Param: * @param currentPage
     * @param pageSize
     * @param prodId
     * @param sessionId
     * @param sec_kill_id
     * @param userName
     * @Return: java.util.List<com.zyt.entity.SecKillProdRelation>
     */
    @Override
    public List<SecKillProdRelation> GetRelativeProductInfoOfCurrKillSessionByItems(String currentPage, String pageSize, String prodId, String sessionId, String sec_kill_id,String userName) {
        //根据姓名去查询店铺编号
        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        int initPageSize = Integer.parseInt(pageSize);
        int initCurrentPage = Integer.parseInt(currentPage);
        int start = (initCurrentPage - 1) * initPageSize;
        int end  = initPageSize;
        int int_prodId = Integer.parseInt(prodId);
        int int_session = Integer.parseInt(sessionId);
        return this.secKillProdRelationMapper.getRelativeProductInfoOfCurrKillSessionByItems(storeId,start,end,int_prodId,int_session) != null ?
               this.secKillProdRelationMapper.getRelativeProductInfoOfCurrKillSessionByItems(storeId,start,end,int_prodId,int_session) :
                null;


    }

    /**
     * @Method: GetRelativeProductCountsOfCurrKillSessionByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 查询关联商品的数量
     * @Return: int
     * @Exception:
     * @Date: 2021/3/8 21:53
     * @Param: * @param prodId
     * @param sessionId
     * @param sec_kill_id
     * @param userName
     * @Return: int
     */
    @Override
    public int GetRelativeProductCountsOfCurrKillSessionByItems(String prodId, String sessionId,String sec_kill_id, String userName) {
        //根据姓名去查询店铺编号
        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        int int_prodId = Integer.parseInt(prodId);
        int int_session = Integer.parseInt(sessionId);
        return this.secKillProdRelationMapper.getRelativeProductCountsOfCurrKillSessionByItems(storeId,int_prodId,int_session) > 0 ?
               this.secKillProdRelationMapper.getRelativeProductCountsOfCurrKillSessionByItems(storeId,int_prodId,int_session) :
                0;
    }

    /**
     * @Method: GetAllProdInfoOfCurrStore
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询商品信息
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/3/8 21:29
     * @Param: * @param userName
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> GetAllProdInfoOfCurrStore(String userName) {
        Store store  = this.newStoreSettledService.getStoreInfoByStoreOwner(userName);
        if (store != null){
            List<Product> productList =  this.productDetailsInfoService.getProductInfoOfCurrStore(store.getStoreName());
            return productList != null && productList.size() > 0 ? productList : null;
        }else{
            return null;
        }
    }

    /**
     * @Method: SaveRelativeProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:添加秒杀商品信息
     * @Return: int
     * @Exception:
     * @Date: 2021/3/9 15:48
     * @Param: * @param userName
     * @param kill_session_id
     * @param prod_id
     * @param kill_price
     * @param kill_count
     * @param sort
     * @param limit_buy_count
     * @Return: int
     */
    @Override
    @Transactional
    public int SaveRelativeProduct(String userName, String kill_session_id, String prod_id, String kill_price, String kill_count, String sort, String limit_buy_count) {
        //根据姓名去查询店铺编号
        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        int kill_sessionId = Integer.parseInt(kill_session_id);
        int killPrice = Integer.parseInt(kill_price);
        int killCounts = Integer.parseInt(kill_count);
        int int_sort = Integer.parseInt(sort);
        int limit_buyCounts = Integer.parseInt(limit_buy_count);
        //TODO 封装秒杀商品信息
        SecKillProdRelation secKillProdRelation  = new SecKillProdRelation();
        secKillProdRelation.setStore_id(storeId);
        String storeName = this.newStoreSettledService.getStoreNameByStoreId(storeId);
        int prodId = this.productDetailsInfoService.getProdIdByProdName(prod_id,storeName);
        secKillProdRelation.setProd_id(prodId);
        secKillProdRelation.setPromotion_session_id(kill_sessionId);
        secKillProdRelation.setSeckill_count(killCounts);
        secKillProdRelation.setSeckill_limit(limit_buyCounts);
        secKillProdRelation.setSeckill_price(killPrice);
        secKillProdRelation.setSeckill_sort(int_sort);
        logger.info("封装的秒杀关联商品信息为:"+secKillProdRelation.toString());
        //若封装的关联的商品信息不为空则保存
        if (secKillProdRelation != null){
            return  this.secKillProdRelationMapper.saveRelativeProduct(secKillProdRelation) > 0 ?
                    this.secKillProdRelationMapper.saveRelativeProduct(secKillProdRelation) :
                    0;
        }else{
            return 0;
        }
    }

    /**
     * @Method: DeleteKillRelativeProdInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据id删除关联商品信息
     * @Return: int
     * @Exception:
     * @Date: 2021/3/9 17:04
     * @Param: * @param relation_id
     * @Return: int
     */
    @Override
    @Transactional
    public int DeleteKillRelativeProdInfo(String relation_id) {
        int relationId = Integer.parseInt(relation_id);
        return this.secKillProdRelationMapper.deleteKillRelativeProdInfo(relationId) > 0 ?
               this.secKillProdRelationMapper.deleteKillRelativeProdInfo(relationId) :
                0;
    }

    /**
     * @Method: getRelativeProdInfoOfCurrSeesionId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据场次id获得当前场次所有关联的商品信息
     * @Return: java.util.List<com.zyt.entity.SecKillProdRelation>
     * @Exception:
     * @Date: 2021/3/12 15:20
     * @Param: * @param sec_kill_id
     * @Return: java.util.List<com.zyt.entity.SecKillProdRelation>
     */
    @Override
    public List<SecKillProdRelation> getRelativeProdInfoOfCurrSeesionId(Integer sec_kill_id) {
        return  this.secKillProdRelationMapper.getRelativeProdInfoOfCurrSeesionId(sec_kill_id) != null ?
                this.secKillProdRelationMapper.getRelativeProdInfoOfCurrSeesionId(sec_kill_id) :
                null;

    }


}
