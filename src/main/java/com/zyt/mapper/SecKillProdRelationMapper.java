package com.zyt.mapper;

import com.zyt.entity.SecKillProdRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: SecKillProdRelationMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀场次关联的商品信息
 * @Date: 15:16 2021/3/8
 * @Version: 1.0
 */
@Mapper
@Repository(value = "secKillProdRelationMapper")
public interface SecKillProdRelationMapper {

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version 1.0
     * @Description:根据场次id获得当前关联的商品信息
     * @Return:
     * @Exception:
     * @Date: 2021/3/8 15:55
     * @Param: * @param null
     * @Return:
     */
     public List<SecKillProdRelation> getSeckillProRelationInfoBySessionId(@Param("seckill_id") int seckill_id, @Param("start") int start,@Param("end") int end);

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
     public int getSeckillProRelationCountsBySessionId(@Param("seckill_id") int seckill_id);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据条件获得所有的关联商品信息
       * @Return:
       * @Exception:
       * @Date: 2021/3/8 20:45
       * @Param: * @param null
       * @Return:
       */
     public List<SecKillProdRelation> getRelativeProductInfoOfCurrKillSessionByItems(@Param("storeId") int storeId, @Param("start") int start,@Param("end") int end,@Param("int_prodId") int int_prodId,@Param("int_session") int int_session);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据店铺获得当前所有商品的数量
       * @Return:
       * @Exception:
       * @Date: 2021/3/8 20:45
       * @Param: * @param null
       * @Return:
       */
     public int getRelativeProductCountsOfCurrKillSessionByItems(@Param("storeId") int storeId,@Param("int_prodId") int int_prodId,@Param("int_session") int int_session);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:保存相关联的商品信息
       * @Return:
       * @Exception:
       * @Date: 2021/3/9 16:00
       * @Param: * @param null
       * @Return:
       */
     public int saveRelativeProduct(@Param("secKillProdRelation") SecKillProdRelation secKillProdRelation);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据id删除关联商品信
       * @Return:
       * @Exception:
       * @Date: 2021/3/9 17:06
       * @Param: * @param null
       * @Return:
       */
     public int deleteKillRelativeProdInfo(@Param("relationId") int relationId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:根据场次idH获得当前场次的商品信息
       * @Return:
       * @Exception:
       * @Date: 2021/3/12 15:21
       * @Param: * @param null
       * @Return:
       */
     public List<SecKillProdRelation> getRelativeProdInfoOfCurrSeesionId(@Param("sec_kill_id") Integer sec_kill_id);
}
