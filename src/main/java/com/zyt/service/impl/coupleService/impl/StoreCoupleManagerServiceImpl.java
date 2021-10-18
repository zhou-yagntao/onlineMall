package com.zyt.service.impl.coupleService.impl;

import com.zyt.entity.Couple;
import com.zyt.entity.Store;
import com.zyt.mapper.CoupleMapper;
import com.zyt.service.impl.productService.impl.StoreProductInfosServiceImpl;
import com.zyt.service.storeService.NewStoreSettledService;
import com.zyt.service.coupleService.StoreCoupleManagerService;
import com.zyt.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: StoreCoupleManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺优惠券服务层实现层
 * @Date: 13:00 2021/2/6
 * @Version: 1.0
 */
@Service(value = "storeCoupleManagerService")
public class StoreCoupleManagerServiceImpl  implements StoreCoupleManagerService {

     private Logger logger = LoggerFactory.getLogger(StoreProductInfosServiceImpl.class);

     //注入店铺服务层
     @Autowired
     private NewStoreSettledService newStoreSettledService;

     //注入优惠券持久层
     @Autowired
     private CoupleMapper coupleMapper;


    /**
     * @Method: AddCoupleInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:添加优惠券信息
     * @Return: int
     * @Exception:
     * @Date: 2021/2/6 16:32
     * @Param: * @param coupleInfo
     * @param file
     * @Return: int
     */
    @Transactional
    @Override
    public int AddCoupleInfo(String[] coupleInfo, MultipartFile file) {
        logger.info("优惠券信息为:"+coupleInfo+"\t\t"+file);
        String filePath = file.getOriginalFilename();
        //将上传的图片地址转化为地址url
        StringBuilder stringBuilder = new StringBuilder(filePath);
        stringBuilder.insert(5,"://");
        stringBuilder.insert(24,'/');
        stringBuilder.insert(52,'/');
        stringBuilder.insert(55,'/');
        String newFilePath = stringBuilder.toString().substring(0,stringBuilder.toString().length()-4);
        logger.info("优惠券图片信息为:"+newFilePath);
        for (int i = 0; i < coupleInfo.length; i++) {
            logger.info("传入的参数信息为:"+coupleInfo[i]);
        }
        Couple couple = new Couple();
        //将获得的优惠券信息保存到优惠券对象中
        couple.setCname(coupleInfo[0]);
        //根据店铺名称查询店铺信息
        Store store = this.newStoreSettledService.GetStoreInfosByStoreName(coupleInfo[1]);
        couple.setCoupleStoreId(store.getStoreId());
        couple.setCoupleType(coupleInfo[2]);
        try {
            couple.setCoupleObtainStart(DateUtils.stringDate(coupleInfo[3].substring(0,10),"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            couple.setCoupleObtainEnd(DateUtils.stringDate(coupleInfo[4].substring(0,10),"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        couple.setCoupleRelMaxCounts(Integer.parseInt(coupleInfo[5]));
        couple.setCoupleChangeMoney(Integer.parseInt(coupleInfo[6]));
        couple.setPerLimitedCouple(Integer.parseInt(coupleInfo[7]));
        try {
            couple.setCoupleUseStart(DateUtils.stringDate(coupleInfo[8].substring(0,10),"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            couple.setCoupleUseEnd(DateUtils.stringDate(coupleInfo[9].substring(0,10),"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        couple.setCoupleUseScenarios(coupleInfo[10]);
        couple.setCustomerType(coupleInfo[11]);
        couple.setCrossStoreUse(Boolean.getBoolean(coupleInfo[12]));
        couple.setCoupleUseCondition(coupleInfo[13]);
        couple.setOrderAmount(Integer.parseInt(coupleInfo[14]));
        couple.setCoupleImg(newFilePath);
        logger.info("优惠券信息为:"+couple);
        //返回添加优惠券信息是否成功结果
        return  this.coupleMapper.addCoupleInfo(couple);
    }

    /**
     * @Method: GetAllCoupleInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前条件下优惠券的信息
     * @Return: java.util.List<com.zyt.entity.Couple>
     * @Exception:
     * @Date: 2021/2/6 20:22
     * @Param: * @param storeOwner
     * @param currenrPage
     * @param pageLimit
     * @Return: java.util.List<com.zyt.entity.Couple>
     */
    @Override
    public List<Couple> GetAllCoupleInfos(String storeOwner, String currenrPage, String pageLimit) {
        Integer currentPages = Integer.parseInt(currenrPage);
        Integer pageLimits = Integer.parseInt(pageLimit);
        int start = (currentPages - 1)*pageLimits;
        int end = pageLimits;
        //根据拥有者的名字 查询店铺编号
        int storeId = this.newStoreSettledService.GetStoreIdByStoreOwnerName(storeOwner);
        logger.info("店铺编号为:"+storeId);
        //根据条件查询优惠券信息并返回查询数据信息
        return this.coupleMapper.getAllCoupleInfos(storeId,start,end) != null ? this.coupleMapper.getAllCoupleInfos(storeId,start,end) : null;

    }

    /**
     * @Method: GetAllCoupleInfoCounts
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前条件下优惠券的数量
     * @Return: int
     * @Exception:
     * @Date: 2021/2/6 20:22
     * @Param: * @param storeOwner
     * @Return: int
     */
    @Override
    public int GetAllCoupleInfoCounts(String storeOwner) {
        //根据拥有者的名字 查询店铺编号
        int storeId = this.newStoreSettledService.GetStoreIdByStoreOwnerName(storeOwner);
        //根据条件查询当前条件下优惠券数目并返回
        return this.coupleMapper.getAllCoupleInfoCounts(storeId) > 0 ? this.coupleMapper.getAllCoupleInfoCounts(storeId) : 0;

    }


}
