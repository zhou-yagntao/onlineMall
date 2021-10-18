package com.zyt.service.coupleService;

import com.zyt.entity.Couple;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service
 * @ClassName: StoreCoupleManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 优惠券管理服务层
 * @Date: 12:59 2021/2/6
 * @Version: 1.0
 */
public interface StoreCoupleManagerService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加优惠券信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/6 16:33
      * @Param: * @param null
      * @Return:
      */
    public int AddCoupleInfo(String [] coupleInfo, MultipartFile file);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询所有优惠券信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/6 19:52
      * @Param: * @param null
      * @Return:
      */
    public List<Couple> GetAllCoupleInfos(String storeOwner, String currenrPage, String pageLimit);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得所有优惠券条数
      * @Return:
      * @Exception:
      * @Date: 2021/2/6 19:52
      * @Param: * @param null
      * @Return:
      */
    public  int GetAllCoupleInfoCounts(String storeOwner);
}
