package com.zyt.service.impl.storeService.impl;

import com.zyt.entity.Store;
import com.zyt.mapper.StoreMapper;
import com.zyt.service.storeService.NewStoreSettledService;
import com.zyt.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: NewStoreSettledService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺入驻服务实现层
 * @Date: 17:38 2021/1/18
 * @Version: 1.0
 */
@Service(value = "newStoreSettledService")
public class NewStoreSettledServiceImpl  implements NewStoreSettledService {
    Logger logger = LoggerFactory.getLogger(NewStoreSettledServiceImpl.class);

    @Value("${prop.upload-folder}")
    private String filePath;

    @Autowired
    private StoreMapper storeMapper;


    /**
     * @Method: NewStoreSettled
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: int
     * @Exception:添加店铺信息到数据库
     * @Date: 2021/1/19 13:57
     * @Param: * @param file
     * @param storeName
     * @param storeOwner
     * @param addressDetails
     * @param longitude
     * @param latitude
     * @param settledDate
     * @param storeDesc
     * @Return: int
     */
    @Override
    public int NewStoreSettled(MultipartFile file, String storeName, String storeOwner, String addressDetails, String longitude, String latitude, String settledDate, String storeDesc) {

        //设置图片保存本地的存储位置
        logger.info(filePath);
        //获得上传文件的源文件后缀名
        String filesuffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        //随机生成UUID
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //重新拼接获得文件路径
        String newFileNameAndPath = uuid+"."+filesuffix;
        logger.info("新的文件名称为:"+newFileNameAndPath);
        Double newLongitude = Double.parseDouble(longitude);
        Double newLatitude = Double.parseDouble(latitude);
        String newSettledDate = settledDate.substring(0,10);
        System.out.println("新的结果为"+newSettledDate);
        Date newStoreSettledDate = null;
        try {
            newStoreSettledDate = DateUtils.stringDate(newSettledDate,"yyyy-MM-dd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //封装数据为Store对象
        Store store = new Store();
        store.setStoreName(storeName);
        store.setStoreOwner(storeOwner);
        store.setStoreImg(newFileNameAndPath);
        store.setStoreAddress(addressDetails);
        store.setLongitude(newLongitude);
        store.setAtitude(newLatitude);
        store.setSettledDate(newStoreSettledDate);
        store.setStoreDesc(storeDesc);
        return  this.storeMapper.storeSettled(store);

    }

    /**
     * @Method: GetStoreDetailInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获取详细的店铺信息
     * @Return: java.util.List<com.zyt.entity.Store>
     * @Exception:
     * @Date: 2021/1/19 13:57
     * @Param: * @param currPage
     * @param limit
     * @Return: java.util.List<com.zyt.entity.Store>
     */
    @Override
    public List<Store> GetStoreDetailInfos(String currPage, String limit) {
        Integer currentPage = Integer.parseInt(currPage);
        Integer pageLimit  = Integer.parseInt(limit);
        //计算数据开始，结束为止
        int start = (currentPage-1)*pageLimit;
        int end = pageLimit;
        return this.storeMapper.getStoreDetailInfos(start,end);
    }

    /**
     * @Method: GetAllStoreCounts
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: int
     * @Exception:获取店铺的总数目
     * @Date: 2021/1/19 13:58
     * @Param: * @param
     * @Return: int
     */
    @Override
    public int GetAllStoreCounts() {
        return this.storeMapper.getStoreTotalCounts();
    }

    /**
     * @Method: UpdateStoreInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:修改商品的信息
     * @Return: int
     * @Exception:
     * @Date: 2021/1/19 16:17
     * @Param: * @param storeId
     * @param storeName
     * @param storeAddress
     * @param longitude
     * @param lattitude
     * @param storeDescrible
     * @Return: int
     */
    @Override
    public int UpdateStoreInfos(String storeId, String storeName, String storeAddress, String longitude, String lattitude, String storeDescrible) {
        Integer newStoreId  = Integer.parseInt(storeId);
        Double newLongitude = Double.parseDouble(longitude);
        Double newLattitude = Double.parseDouble(lattitude);
       Store store = new Store();
       store.setStoreId(newStoreId);
       store.setStoreName(storeName);
       store.setStoreAddress(storeAddress);
       store.setLongitude(newLongitude);
       store.setAtitude(newLattitude);
       store.setStoreDesc(storeDescrible);
       return this.storeMapper.updateStoreInfos(store);

    }

    /**
     * @Method: DeleteStoreInfosByStoreId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:删除商品信息
     * @Return: int
     * @Exception:
     * @Date: 2021/1/19 16:37
     * @Param: * @param storeId
     * @Return: int
     */
    @Override
    public int DeleteStoreInfosByStoreId(String storeId) {
        Integer newStoreId  = Integer.parseInt(storeId);
        return this.storeMapper.deleteStoreInfosByStoreId(newStoreId);
    }

    /**
     * @Method: GetStoreInfosByStoreName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据店铺名称查询店铺信息
     * @Return: com.zyt.entity.Store
     * @Exception:
     * @Date: 2021/2/6 16:53
     * @Param: * @param storeName
     * @Return: com.zyt.entity.Store
     */
    @Override
    public Store GetStoreInfosByStoreName(String storeName) {
        return this.storeMapper.getStoreInfosByStoreName(storeName);
    }

    /**
     * @Method: GetStoreIdByStoreOwnerName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: int
     * @Exception:
     * @Date: 2021/2/6 20:11
     * @Param: * @param storeOwner
     * @Return: int
     */
    @Override
    public int GetStoreIdByStoreOwnerName(String storeOwner) {
        return  this.storeMapper.getStoreIdByStoreOwnerName(storeOwner) > 0 ? this.storeMapper.getStoreIdByStoreOwnerName(storeOwner) : 0;
        //return this.storeMapper.getStoreIdByStoreName(storeOwner) > 0 ? this.storeMapper.getStoreIdByStoreName(storeOwner) : 0;
    }

    /**
     * @Method: GetStoreInfoOfCurrentPostion
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: java.util.List<com.zyt.entity.Store>
     * @Exception:
     * @Date: 2021/2/18 18:53
     * @Param: * @param postion
     * @Return: java.util.List<com.zyt.entity.Store>
     */
    @Override
    public List<Store> GetStoreInfoOfCurrentPostion(String postion) {
        String postions  = "%"+postion+"%";
        logger.info("获得店铺信息结果为:"+this.storeMapper.getStoreInfoOfCurrentPostion(postions));
        return this.storeMapper.getStoreInfoOfCurrentPostion(postions) != null ? this.storeMapper.getStoreInfoOfCurrentPostion(postions) : null;
    }

    /**
     * @Method: getStoreIdByStoreName
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前店铺名称下的店铺id
     * @Return: int
     * @Exception:
     * @Date: 2021/3/4 16:15
     * @Param: * @param prod_store
     * @Return: int
     */
    @Override
    public int getStoreIdByStoreName(String prod_store) {
        return this.storeMapper.getStoreIdByOfCurrStoreName(prod_store);
    }

    /**
     * @Method: saveSecKillSessionInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据店主名称去获得店铺id
     * @Return: int
     * @Exception:
     * @Date: 2021/3/6 13:24
     * @Param: * @param userName
     * @Return: int
     */
    @Override
    public int getStoreIdByStoreOwnerUserName(String userName) {
        return this.storeMapper.getStoreIdByStoreOwnerUserName(userName) != 0 ? this.storeMapper.getStoreIdByStoreOwnerUserName(userName) : 0;

    }

    /**
     * @Method: getStoreInfoByStoreOwner
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据店铺拥有者查询店铺信息
     * @Return: java.util.List<com.zyt.entity.Store>
     * @Exception:
     * @Date: 2021/3/8 21:16
     * @Param: * @param userName
     * @Return: java.util.List<com.zyt.entity.Store>
     */
    @Override
    public Store getStoreInfoByStoreOwner(String userName) {
        return this.storeMapper.getStoreInfoByStoreOwner(userName) != null ?
               this.storeMapper.getStoreInfoByStoreOwner(userName) :
                null;

    }

    /**
     * @Method: getStoreNameByStoreId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据id查询店铺名称
     * @Return: java.lang.String
     * @Exception:
     * @Date: 2021/3/9 16:24
     * @Param: * @param storeId
     * @Return: java.lang.String
     */
    @Override
    public String getStoreNameByStoreId(int storeId) {
        return this.storeMapper.getStoreNameByStoreId(storeId) != null ?
               this.storeMapper.getStoreNameByStoreId(storeId):
                null;

    }
}
