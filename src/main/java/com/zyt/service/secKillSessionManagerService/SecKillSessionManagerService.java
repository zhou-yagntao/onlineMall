package com.zyt.service.secKillSessionManagerService;

import com.zyt.entity.SecKillSession;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.SecKillSessionService
 * @ClassName: SecKillSessionManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀管理服务层
 * @Date: 11:34 2021/3/6
 * @Version: 1.0
 */
public interface SecKillSessionManagerService {

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:保存秒杀信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/6 13:07
     * @Param: * @param null
     * @Return:
     */
    public int saveSecKillSessionInfos(String[] seckillSessions, String userName);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:获得当前店铺秒杀信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/6 16:04
     * @Param: * @param null
     * @Return:
     */
    public List<SecKillSession> getAllSecKillInfoOfCurrStore(String pageSize, String currentPage, String userName);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:获得当前店铺面试时间数据个数
     * @Return:
     * @Exception:
     * @Date: 2021/3/6 16:05
     * @Param: * @param null
     * @Return:
     */
    public int getSecKillInfoCountsOfCurrStore(String userName);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:获得当前条件下的秒杀信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/8 13:14
     * @Param: * @param null
     * @Return:
     */
    public List<SecKillSession> getKillInfoByItems(String pageSize, String currentPage, String userName, String killName, String isEnable);


    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:获得当前条件下秒杀的数目
     * @Return:
     * @Exception:
     * @Date: 2021/3/8 13:14
     * @Param: * @param null
     * @Return:
     */
    public int getKillInfoCountsByItems(String pageSize, String currentPage, String userName, String killName, String isEnable);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:批量删除秒杀信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/8 14:35
     * @Param: * @param null
     * @Return:
     */
    public int BatchDeleteKillInfo(String[] killId);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:根据id删除秒杀 信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/8 14:46
     * @Param: * @param null
     * @Return:
     */
    public int DeleteKillInfoByKillId(String killId);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得最新三天的优惠信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 14:17
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillSession> getSessionsInfoOfLatestThreeDay();
}
