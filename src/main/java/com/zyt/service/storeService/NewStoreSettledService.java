package com.zyt.service.storeService;

import com.zyt.entity.Stock;
import com.zyt.entity.Store;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: NewStoreSettledService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺入驻服务
 * @Date: 17:38 2021/1/18
 * @Version: 1.0
 */
public interface NewStoreSettledService {

    //添加数据
    public  int NewStoreSettled(MultipartFile file, String storeName, String storeOwner, String addressDetails, String longitude, String latitude, String settledDate, String storeDesc);
    //获取所有店铺信息
    public List<Store> GetStoreDetailInfos(String currPage, String limit);
    //获得所有店铺的数量
    public int GetAllStoreCounts();
    //修改店铺数据
    public int UpdateStoreInfos(String storeId,String storeName,String storeAddress,String longitude,String lattitude,String storeDescrible);
    //删除商品信息
    public int DeleteStoreInfosByStoreId(String storeId);

    //根据店铺名称查询店铺信息
    public Store GetStoreInfosByStoreName(String storeName);

    //根据店铺拥有者名字查询店铺编号
    public int GetStoreIdByStoreOwnerName(String storeOwner);

    //根据位置店铺名称信息
    public List<Store> GetStoreInfoOfCurrentPostion(String postion);

     //根据店铺名称获得店铺id
    public int getStoreIdByStoreName(String prod_store);

    //根据店铺店主去获得店铺id
    public int getStoreIdByStoreOwnerUserName(String userName);

    //根据拥有者姓名获得店铺的信息
    public Store getStoreInfoByStoreOwner(String userName);

    //根据id查询店铺名称
    public String getStoreNameByStoreId(int storeId);
}
