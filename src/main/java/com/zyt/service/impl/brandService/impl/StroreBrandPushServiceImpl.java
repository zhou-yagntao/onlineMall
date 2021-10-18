package com.zyt.service.impl.brandService.impl;

import com.zyt.entity.StoreBrand;
import com.zyt.mapper.BrandMapper;
import com.zyt.service.brandService.StroreBrandPushService;
import com.zyt.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: StroreBrandPushServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 商品品牌管理服务侧
 * @Date: 12:24 2021/1/24
 * @Version: 1.0
 */
@Service(value = "stroreBrandPushService")
public class StroreBrandPushServiceImpl  implements StroreBrandPushService {

    private Logger logger = LoggerFactory.getLogger(StroreBrandPushService.class);

    @Autowired
    private BrandMapper brandMapper;

    /**
     * @Method: PushStoreBrandInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理商品添加的实现过程
     * @Return: int
     * @Exception:
     * @Date: 2021/1/24 14:27
     * @Param: * @param brandsInfos
     * @Return: int
     */
    @Override
    @Transactional //添加事务属性，防止重复加入
    public int PushStoreBrandInfos(String[] brandsInfos) throws Exception {
        StoreBrand storeBrand = new StoreBrand();
        //通过循环遍历数组，
        for (int i = 0 ; i <  brandsInfos.length ; i++ ){
           logger.info("前台传递的参数为:"+brandsInfos[i]);
        }
        //将前台传递来的数据入驻到实体类中
        storeBrand.setBrandName(brandsInfos[0]);
        storeBrand.setBrandType(brandsInfos[1]);
        String storeCreateTime  = brandsInfos[2];
        logger.info("创建时间为:"+storeCreateTime.substring(0,10));
        Date brandCreateTime = DateUtils.stringDate(storeCreateTime.substring(0,10),"yyyy-MM-dd");
        storeBrand.setRandCreatTime(DateUtils.stringDate(brandsInfos[2].substring(0,10),"yyyy-MM-dd"));
        storeBrand.setIsForbidden(brandsInfos[3]);
        storeBrand.setBrandDescrible(brandsInfos[4]);
        //注入到持久层
        return this.brandMapper.pushStoreBrandInfos(storeBrand);


    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:返回商品详细信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 14:38
      * @Param: * @param null
      * @Return:
      */
    @Override
    public List<StoreBrand> GetStoreBrandDetailInfos(String currentPage, String pageLimit,String isForbiddenBrand) {
        int currPage  = Integer.parseInt(currentPage);
        int limit = Integer.parseInt(pageLimit);
        int start = (currPage - 1)*limit;
        int end = limit;
        logger.info("计算分页结果为:"+start+"\t\t"+end);
        return this.brandMapper.getStoreBrandDetailInfos(start,end,isForbiddenBrand);

    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:返回商品的数量
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 14:38
      * @Param: * @param null
      * @Return:
      */
    @Override
    public int GetStoreBrandCounts(String isForbiddenBrand) {
        return this.brandMapper.getStoreBrandCounts(isForbiddenBrand);
    }

    /**
     * @Method: UpdateStoreBrandInfosByBrandId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:修改商品状态
     * @Return: int
     * @Exception:
     * @Date: 2021/1/24 16:31
     * @Param: * @param brandId
     * @Return: int
     */
    @Override
    public int UpdateStoreBrandInfosByBrandId(String brandId, String isForbidden) {
        int brandID = Integer.parseInt(brandId);
        //接收传递的参数并返回修改结果
        return  this.brandMapper.updateStoreBrandInfosByBrandId(brandID,isForbidden);
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据编号删除商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 17:06
      * @Param: * @param null
      * @Return:
      */
    @Override
    public int DeleteStoreBrandInfosByBrandId(String brandId) {
        int brandID = Integer.parseInt(brandId);
        return this.brandMapper.deleteStoreBrandInfosByBrandId(brandID);
    }

     /**
      * @Method: GetAllProductBrandName
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得所有的商品品牌信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 17:20
      * @Param: * @param null
      * @Return:
      */
    @Override
    public List<StoreBrand> GetAllProductBrandName() {
        return this.brandMapper.getAllProductBrandName();
    }

    /**
     * @Method: GetAllProductBrandTypeMenu
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 获得前端菜单页面渲染数据
     * @Return: java.util.List<com.zyt.entity.StoreBrand>
     * @Exception:
     * @Date: 2021/2/8 16:23
     * @Param: * @param
     * @Return: java.util.List<com.zyt.entity.StoreBrand>
     */
    @Override
    public List<StoreBrand> GetAllProductBrandTypeMenu() {
        List<StoreBrand> brandsList = this.brandMapper.getAllProductBrandTypeMenu();
        List<StoreBrand> brandsInfo = null;
        //若查询品牌菜单的信息不为空
        if (brandsList != null){
            //通过stream流函数获得菜单的名称并重新组装层成对象集合
            brandsInfo = brandsList.stream().map(brandList ->{
                StoreBrand storeBrand = new StoreBrand();
                storeBrand.setBrandName(brandList.getBrandName());
                return  storeBrand;
            }).collect(Collectors.toList());
        }
        //若当前对象集合不为空则返回数据否则返回空
        return brandsInfo != null ? brandsInfo:null;
    }

    @Override
    public List<StoreBrand> GetStoreInfoOfBrand(String brandName,String currPostions) {
        String currPostion = "%"+currPostions+"%";
        logger.info("输入服务层的参数为:"+currPostion);
        List<StoreBrand>storeBradInfo =  this.brandMapper.getStoreInfoOfBrand(brandName,currPostion) != null ? this.brandMapper.getStoreInfoOfBrand(brandName,currPostion) : null;
//        List<Store> storeList = storeBradInfo.stream().map(storeBrand -> {
//            logger.info("数据长度为:"+storeBrand.getStoreInfo().size());
//            int i = 0;
//            Store store = new Store();
//            while(i < storeBrand.getStoreInfo().size()-1){
//                store.setStoreName(storeBrand.getStoreInfo().get(i).getStoreName());
//                i++;
//                logger.info("店铺信息为:"+store.toString());
//            }
//            return store;
//        }).collect(Collectors.toList());
        return  storeBradInfo;
    }

}
