package com.zyt.service.SecKillProdRelationManagerService;

import com.zyt.entity.Product;
import com.zyt.entity.SecKillProdRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.SecKillProdRelationManagerService
 * @ClassName: SecKillProdRelationService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀场次关联的商品信息
 * @Date: 15:50 2021/3/8
 * @Version: 1.0
 */
public interface SecKillProdRelationService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前场次关联的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 15:53
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillProdRelation> GetSeckillProRelationInfoBySessionId(String sec_kill_id, String currentPage, String pageSize);


    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前场次关联商品的数目
     * @Return:
     * @Exception:
     * @Date: 2021/3/8 16:00
     * @Param: * @param null
     * @Return:
     */
    public int GetSeckillProRelationCountsBySessionId(String seckill_id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件查询秒杀商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 20:32
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillProdRelation> GetRelativeProductInfoOfCurrKillSessionByItems(String currentPage, String pageSize, String prodId, String sessionId, String sec_kill_id,String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询当前场次安排的商品数量
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 20:33
      * @Param: * @param null
      * @Return:
      */
    public int GetRelativeProductCountsOfCurrKillSessionByItems(String prodId, String sessionId, String sec_kill_id,String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前店家下的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/8 21:13
      * @Param: * @param null
      * @Return:
      */
    public  List<Product> GetAllProdInfoOfCurrStore(String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存秒杀商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/9 15:47
      * @Param: * @param null
      * @Return:
      */
    public int SaveRelativeProduct(String userName, String kill_session_id, String prod_id, String kill_price, String kill_count, String sort, String limit_buy_count);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据条件删除秒杀商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/9 17:04
      * @Param: * @param null
      * @Return:
      */
    public int DeleteKillRelativeProdInfo(String relation_id);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据场次id获得当前场次的商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/12 15:19
      * @Param: * @param null
      * @Return:
      */
    public List<SecKillProdRelation> getRelativeProdInfoOfCurrSeesionId(Integer sec_kill_id);
}
