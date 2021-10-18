package com.zyt.mapper;

import com.zyt.entity.SecKillSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: SecKillSessionMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀管持久层
 * @Date: 11:23 2021/3/6
 * @Version: 1.0
 */
@Mapper
@Repository(value = "secKillSessionMapper")
public interface SecKillSessionMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存秒杀信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/6 15:34
      * @Param: * @param null
      * @Return:
      */
    public int saveSecKillSessionInfos(@Param("secKillSession") SecKillSession secKillSession);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得所有的秒杀场次信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/6 16:44
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillSession> getAllSecKillInfoOfCurrStore(@Param("start") int start, @Param("end") int end, @Param("storeId") int storeId, @Param("subTime") String subTime);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前店铺秒杀场次数量
      * @Return:
      * @Exception:
      * @Date: 2021/3/6 16:45
      * @Param: * @param null
      * @Return:
      */
    public int getSecKillInfoCountsOfCurrStore(@Param("storeId") int storeId,@Param("subTime") String subTime);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件获得秒杀场次信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 13:17
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillSession> getKillInfoByItems(@Param("storeId") int storeId,@Param("start") int start,@Param("end") int end,@Param("killName") String killName, @Param("flag")Boolean flag);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件获得秒杀场次信息数目
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 13:18
      * @Param: * @param null
      * @Return:
      */
     public int getKillInfoCountsByItems(@Param("storeId") int storeId, @Param("killName") String killName, @Param("flag") Boolean flag);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:处理批量删除
       * @Return:
       * @Exception:
       * @Date: 2021/3/8 14:42
       * @Param: * @param null
       * @Return:
       */
    public void batchDeleteKillInfo(@Param("secKillIdinfo") List<Integer> secKillIdinfo);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:处理根据id删除秒杀场次信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 14:48
      * @Param: * @param null
      * @Return:
      */
    public int deleteKillInfoByKillId(@Param("kill_id") int kill_id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:计算当前时间三天后的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 14:32
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillSession> getSessionsInfoOfLatestThreeDay(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
