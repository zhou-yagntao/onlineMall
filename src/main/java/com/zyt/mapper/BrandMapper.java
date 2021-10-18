package com.zyt.mapper;

import com.zyt.entity.StoreBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: BrandMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 品牌管理持久层
 * @Date: 12:26 2021/1/24
 * @Version: 1.0
 */
@Mapper
@Repository(value = "brandMapper")
public interface BrandMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加商品品牌信息持久层
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 12:28
      * @Param: * @param null
      * @Return:
      */
    public int pushStoreBrandInfos(StoreBrand storeBrand);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得商品详细信息
     * @Return:
     * @Exception:
     * @Date: 2021/1/24 14:37
     * @Param: * @param null
     * @Return:
     */
    public List<StoreBrand> getStoreBrandDetailInfos(@Param("start") int start, @Param("end") int end, @Param("isForbidden")String isForbidden);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得商品的总数量
     * @Return:
     * @Exception:
     * @Date: 2021/1/24 14:37
     * @Param: * @param null
     * @Return:
     */
    public int getStoreBrandCounts(@Param("isForbidden")String isForbidden);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品的启用禁用状态
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 16:37
      * @Param: * @param null
      * @Return:
      */
    public  int updateStoreBrandInfosByBrandId(@Param("brandId") int brandId,@Param("isForbidden")String isForbidden);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据编号删除商品信息
      * @Return:
      * @Exception:
      * @Date: 2021/1/24 17:05
      * @Param: * @param null
      * @Return:
      */
    public int deleteStoreBrandInfosByBrandId(@Param("brandId") int brandId);

      /**
       * @Method:
       * @Author: zhou_yangtao@yeah.net
       * @Version  1.0
       * @Description:获得商品品牌信息
       * @Return:
       * @Exception:
       * @Date: 2021/1/24 17:19
       * @Param: * @param null
       * @Return:
       */
    public List<StoreBrand> getAllProductBrandName();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据品牌名称获得品牌编号
      * @Return:
      * @Exception:
      * @Date: 2021/1/26 14:17
      * @Param: * @param null
      * @Return:
      */
    public StoreBrand getBrandIdByBrandName(@Param("brandName") String brandName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据品牌编号获得品牌名称
      * @Return:
      * @Exception:
      * @Date: 2021/2/5 14:06
      * @Param: * @param null
      * @Return:
      */
    public String getBrandNameByBrandId(@Param("brandId") int brandId);

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得品牌信息进行前台渲染
     * @Return:
     * @Exception:
     * @Date: 2021/2/8 16:15
     * @Param: * @param null
     * @Return:
     */
    public  List<StoreBrand> getAllProductBrandTypeMenu();

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return:
      * @Exception:
      * @Date: 2021/2/8 17:58
      * @Param: * @param null
      * @Return:
      */
    public List<StoreBrand> getStoreInfoOfBrand(@Param("brandName") String brandName,@Param("currentPostion")String currentPostion);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:根据品牌名称获得品牌编号
      * @Return:
      * @Exception:
      * @Date: 2021/2/18 19:40
      * @Param: * @param null
      * @Return:
      */
    public  Integer getBrandIdOfCurrBrandName(String brandName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前id的品牌名称
      * @Return:
      * @Exception:
      * @Date: 2021/2/27 13:12
      * @Param: * @param null
      * @Return:
      */
    public String getCurrentBrandNameByBrandId(@Param("brand_Id") int brand_Id);

}
