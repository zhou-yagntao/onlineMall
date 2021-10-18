package com.zyt.service.impl.productService.impl;

import com.zyt.entity.Product;
import com.zyt.entity.StoreBrand;
import com.zyt.entity.to.es.ProUpEslasticsearch;
import com.zyt.mapper.BrandMapper;
import com.zyt.mapper.ProdPopularityMapper;
import com.zyt.mapper.ProductMapper;
import com.zyt.mapper.StoreMapper;
import com.zyt.service.productService.StoreProductInfosService;
import com.zyt.service.esSearch.ProductStatusUpService;
import com.zyt.utils.ChangeJsonTools;
import com.zyt.utils.DateUtils;
import com.zyt.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: StoreProductInfosServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 店铺商品信息服务实现层
 * @Date: 13:22 2021/1/26
 * @Version: 1.0
 */
@Service(value = "storeProductInfosService")
public class StoreProductInfosServiceImpl implements StoreProductInfosService {

     private Logger logger = LoggerFactory.getLogger(StoreProductInfosServiceImpl.class);

     //注入品牌持久层
     @Autowired
     private BrandMapper brandMapper;

     //注入店铺持久层
     @Autowired
     private StoreMapper storeMapper;

     //注入商品持久层
     @Autowired
     private ProductMapper productMapper;

     //注入商品热度表排行榜
    @Autowired
    private ProdPopularityMapper prodPopularityMapper;

    //注入商品上架服务
    @Autowired
    private ProductStatusUpService productStatusUpService;

    //注入redis缓存
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Method: AddNewProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:
     * @Return: int
     * @Exception:店铺信息实现管理层
     * @Date: 2021/1/26 13:23
     * @Param: * @param file
     * @param params
     * @Return: int
     */
    @Override
    public int AddNewProductInfos(MultipartFile file, String[] params) {
         String filePath = file.getOriginalFilename();
         //将上传的图片地址转化为地址url
         StringBuilder stringBuilder = new StringBuilder(filePath);
         stringBuilder.insert(5,"://");
         stringBuilder.insert(24,'/');
         stringBuilder.insert(52,'/');
         stringBuilder.insert(55,'/');
         String newFilePath = stringBuilder.toString().substring(0,stringBuilder.toString().length()-4);
         logger.info("图片地址为:"+newFilePath);
        for (int i = 0; i < params.length ; i++) {
            logger.info("参数列表有:"+params[i]);
        }
        Product product = new Product();
        product.setProdImage(newFilePath);
        //获得商品的名称
        product.setProdName(params[0]);
        //根据品牌名称获得品牌的id
        String brandName = params[1];
        StoreBrand brand = this.brandMapper.getBrandIdByBrandName(brandName);
        logger.info("获得商品品牌编号为:"+brand.getBrandId());
        product.setStoreBrandId(brand.getBrandId());
        String createTime = params[2].substring(0,10);
        try {
            product.setProdReleaseTime(DateUtils.stringDate(createTime,"yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        product.setProdStatus(params[3]);
        String nvipAllPrice = params[4]+"元"+"|"+params[5]+"元"+"|"+params[6]+"元";
        String vipAllPrice = params[7]+"元"+"|"+params[8]+"元"+"|"+params[9]+"元";
        product.setProdNvipAllPrice(nvipAllPrice);
        product.setProdVipAllPrice(vipAllPrice);
        product.setProdMoral(params[10]);
        product.setProdIntegral(Integer.parseInt(params[11]));
        product.setProdGrowthvalue(Integer.parseInt(params[12]));
        product.setOnSheleves(Boolean.getBoolean(params[13]));
        product.setUseCouple(Boolean.getBoolean(params[14]));
        List list = new ArrayList();
        product.setProStoreName(params[15]);
        product.setProdEveryMaxStorage(Integer.parseInt(params[16]));
        product.setOnSheleves(false);
        logger.info("商品实体类信息为"+product.toString());
        //返回添加数据的结果
        return this.productMapper.addNewStoreProduct(product);
    }

    /**
     * @Method: GetCurrentStoreAllProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description: 查询所有商品信息
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/1/28 10:26
     * @Param: * @param currentPage
     * @param pageLimit
     * @param userName
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> GetCurrentStoreAllProductInfos(String currentPage, String pageLimit, String userName) {
        //1.根据用户名查询该用户所拥有的的店铺名称
        String storeName = this.storeMapper.getStoreNameByOwnerName(userName);
        logger.info("传入服务层的参数信息为:"+storeName);
        Integer currentPages = Integer.parseInt(currentPage) > 0 ? Integer.parseInt(currentPage) : 0;
        Integer pageLimits = Integer.parseInt(pageLimit);
        //计算分页起始，末了位置
        int start = (currentPages - 1) * pageLimits;
        int end = pageLimits;
        logger.info("传入服务层的参数信息为:"+storeName+"\t"+"\t"+start+"\t"+"\t"+end);
        //logger.info("查询商品信息"+this.productMapper.getAllProductRelationInfos(start,end,storeName));
        //return this.productMapper.getAllProductRelationInfos(start,end,storeName) != null ? this.productMapper.getAllProductRelationInfos(start,end,storeName) : null;
        return this.productMapper.getAllProductRelationInfos(start,end) != null ? this.productMapper.getAllProductRelationInfos(start,end) : null;

    }

     /**
      * @Method: GetCurrrentStoreAllProductsCounts
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前店铺下所有商品的数目
      * @Return:
      * @Exception:
      * @Date: 2021/2/5 18:44
      * @Param: * @param null
      * @Return:
      */
    @Override
    public int GetCurrrentStoreAllProductsCounts(String userName) {
        //1.根据用户名查询该用户所拥有的的店铺名称
        String storeName = this.storeMapper.getStoreNameByOwnerName(userName);
        logger.info("传入服务层的参数信息为:"+storeName);
        //2.根据店铺编号去查询该店铺下的所有商品的数量
        //return  this.productMapper.allProductCountsCurrentStore(storeName) > 0 ? this.productMapper.allProductCountsCurrentStore(storeName) : 0;
        return  this.productMapper.allProductCountsCurrentStore() > 0 ? this.productMapper.allProductCountsCurrentStore() : 0;
    }

    /**
     * @Method: GetProductInfosByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询商品信息
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/2/1 14:48
     * @Param: * @param currentPage
     * @param pageLimit
     * @param productName
     * @param storeName
     * @param prodBrandId
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> GetProductInfosByItems(String currentPage, String pageLimit, String productName, String storeName, String prodBrandId) {
        Integer currentPages = Integer.parseInt(currentPage) > 0 ? Integer.parseInt(currentPage) : 0;
        Integer pageLimits = Integer.parseInt(pageLimit);
        //计算分页起始，末了位置
        int start = (currentPages - 1) * pageLimits;
        int end = pageLimits;
        return this.productMapper.getProductInfosByItems(start,end,productName,storeName,Integer.parseInt(prodBrandId)) != null ? this.productMapper.getProductInfosByItems(start,end,productName,storeName,Integer.parseInt(prodBrandId)):null;

    }
    /**
     * @Method: GetProductInfoCountsBYItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询商品数量
     * @Return: int
     * @Exception:
     * @Date: 2021/2/1 14:49
     * @Param: * @param productName
     * @param storeName
     * @param prodBrandId
     * @Return: int
     */
    @Override
    public int GetProductInfoCountsBYItems(String productName, String storeName, String prodBrandId) {
        return this.productMapper.getProductInfoCountsBYItems(productName,storeName,Integer.parseInt(prodBrandId)) > 0 ? this.productMapper.getProductInfoCountsBYItems(productName,storeName,Integer.parseInt(prodBrandId)):0;
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 根据编号删除数据
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:37
      * @Param: * @param null
      * @Return:
      */
    @Override
    public int DeleteProductByProdId(String productId) {
        Integer prodId = Integer.parseInt(productId);
        //删除数据，删除成功则返回对应的结果，否则返回结果为0
        return this.productMapper.deleteProductByProdId(prodId) > 0 ? this.productMapper.deleteProductByProdId(prodId) : 0;
    }

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:修改商品是否启用禁用优惠券服务层
      * @Return:
      * @Exception:
      * @Date: 2021/2/1 17:54
      * @Param: * @param null
      * @Return:
      */
    @Override
    public int UpdateProductCoupleState(String productId,String coupleState) {
        Integer prodId = Integer.parseInt(productId);
        return this.productMapper.updateProductCoupleState(prodId,coupleState) > 0 ? this.productMapper.updateProductCoupleState(prodId,coupleState) : 0;
    }

    /**
     * @Method:
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:修改商品是否启用禁用积分服务层
     * @Return:
     * @Exception:
     * @Date: 2021/2/1 17:54
     * @Param: * @param null
     * @Return:
     */
    @Override
    public int UpdateProductIntegeral(String productId,String integralState) {
        Integer prodId  = Integer.parseInt(productId);
        return this.productMapper.updateProductIntegeral(prodId,integralState) > 0 ? this.productMapper.updateProductIntegeral(prodId,integralState) : 0;
    }

    /**
     * @Method: ProductUp
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:商品上架业务
     * @Return: void
     * @Exception:
     * @Date: 2021/2/4 11:39
     * @Param: * @param prodId
     * @Return: void
     */
    @Override
    public int ProductUp(String prodId,String brandId) {
        logger.info("传入的商品品牌编号为:"+brandId);
        Integer productId = Integer.parseInt(prodId);
        //Integer brandID = Integer.parseInt(brandId);
        //根据商品编号查询商品信息
        List<Product> plist = this.productMapper.getProductInfoByProdId(productId);
        logger.info("商品信息为:"+plist);
        //通过stream流来遍历我们查询的数据保存到搜素服务实体类中
        List<ProUpEslasticsearch>  upProductInfos =  plist.stream().map(prodList ->{
            System.out.println(prodList);
            //1.组装保存到es的数据
            ProUpEslasticsearch proUpEslasticsearch = new ProUpEslasticsearch();
            BeanUtils.copyProperties(prodList,proUpEslasticsearch);
            proUpEslasticsearch.setProdId(new Long((long)(prodList.getProdId())));
            proUpEslasticsearch.setProdBrandId(new Long((long)prodList.getStoreBrandId()));
            proUpEslasticsearch.setProdImg(prodList.getProdImage());
            //保存到es搜索服务中的商品价格以最低价为准
            String normalProductPrice = prodList.getProdNvipAllPrice().substring(0,2);
            System.out.println(normalProductPrice);
            proUpEslasticsearch.setProdPrice(new BigDecimal(Integer.parseInt(normalProductPrice)));
            //查询当天商品是否已经售卖
            //根据编号信息获得品牌名称
            String brandName = this.brandMapper.getBrandNameByBrandId(prodList.getStoreBrandId());
            logger.info("品牌名称:"+brandName);
            proUpEslasticsearch.setBrandName(brandName);
            proUpEslasticsearch.setStoreName(prodList.getProStoreName());
            //查询商品的热度
//            ProdPopularity prodPopularity = this.prodPopularityMapper.getProdPopularityInfoByProdId(prodList.getProdId());
//            proUpEslasticsearch.setHotScore(new Long((long)prodPopularity.getPopolarity()));
            proUpEslasticsearch.setHotScore(new Long((long) 0));
            return  proUpEslasticsearch;
        }).collect(Collectors.toList());
        logger.info("转化为json格式数据为:"+ChangeJsonTools.objToStringPretty(upProductInfos));
        logger.info("搜索服务保存的商品信息为:");
        //调用es服务进行商品上架
        boolean result = this.productStatusUpService.UpProductInfos(upProductInfos);
        if(result = true){
            //修改数据库中商品是否上架属性
            Boolean flag = true;
            this.productMapper.updateProductIsOnSheleves(productId,flag);
            logger.info("商品上架成功");
            //上架成功 返回结果1
            return  1;
        }
        return 0;
    }

    /**
     * @Method: SearchProInfoByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询商品信息
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/2/10 16:56
     * @Param: * @param currentPage
     * @param pageLimit
     * @param params
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> SearchProInfoByItems(String currentPage, String pageLimit, String params) {

        logger.info("传入参数为:"+currentPage+"\t"+pageLimit+"\t"+params);
        Integer currentPages = Integer.parseInt(currentPage);
        Integer pageLimits = Integer.parseInt(pageLimit);
        int start = (currentPages - 1) * pageLimits;
        int end = pageLimits;
        return this.productMapper.searchProInfoByItems(start,end,params) != null ? this.productMapper.searchProInfoByItems(start,end,params) : null;
    }

    /**
     * @Method: SearchProInfoCountsByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件查询商品数量
     * @Return: int
     * @Exception:
     * @Date: 2021/2/10 16:57
     * @Param: * @param params
     * @Return: int
     */
    @Override
    public int SearchProInfoCountsByItems(String params) {

        logger.info("传入参数为:"+params);
        return this.productMapper.searchProInfoCountsByItems(params);
    }

    /**
     * @Method: GetAllProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询商品信息进行页面渲染
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/2/10 16:57
     * @Param: * @param
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> GetAllProductInfos() {
        //缓存中放的是json字符串，获取json字符串后进行逆转为能用的对象类型。
        //1.使用缓存考虑一下情况： 缓存穿透  缓存击穿
        //通过key在缓存中查询数据
        List<Product> productList = null;
        //logger.info("即将从缓存中获取数据");
        productList = this.redisUtil.getList("productInfo");
       if (0 == productList.size()){
           //如果从缓存中获取数据为null,则从数据库获取
          // logger.info("从数据库获取数据");
           List<Product> productInfoList =  this.productMapper.getAllProductInfos();
//           List<Product> productInfoList = productInfo.stream().filter(productInfos ->
//                   productInfos.getProdId() <=40
//           ).collect(Collectors.toList());
           //将查询的数据添加到缓存中
           this.redisUtil.setList("productInfo",productInfoList);
           return  productInfoList;
       }else{
           //说明缓存中已经有数据了则直接返回
           return  productList;
       }

    }

    /**
     * @Method: GetAllProductInfoOfCurrBrand
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前品牌下商品信息
     * @Return: java.util.List<com.zyt.entity.Product>
     * @Exception:
     * @Date: 2021/2/18 19:32
     * @Param: * @param brandName
     * @Return: java.util.List<com.zyt.entity.Product>
     */
    @Override
    public List<Product> GetAllProductInfoOfCurrBrand(String brandName) {
        List<Product> productListOfCurrBrand  = null;
        //第一次进来从缓存中查询数据
        productListOfCurrBrand = this.redisUtil.getList("prodInfoOfCurrBrand");
        if (0 == productListOfCurrBrand.size()){
            //从数据库查询
            logger.info("从数据库查询");
            //通过品牌名称获得品牌编号
            Integer brandId = this.brandMapper.getBrandIdOfCurrBrandName(brandName);
            //通过品牌的id获取对应的商品名称
            List<Product> productInfoList = this.productMapper.getAllProductInfoOfCurrBrand(brandId);
            this.redisUtil.setList("prodInfoOfCurrBrand",productInfoList);
            return  productInfoList;
        }else{
            //从缓存中或而去
            logger.info("从缓存中获取");
            return productListOfCurrBrand;
        }
    }

//    public  List<Product>  GetAllProductInfoWithRedisLock(){
//        //占分布式锁,通俗理解去redis缓存中占坑
//        //为了防止删除锁的时候删除的是别人的锁  所以我们为每一个线程创建一个编号
//        String uuid = UUID.randomUUID().toString();
//        //Boolean lock = redisTemplate.opsForValue().setIfAbsent("productInfo","zhou_yangtao");
//        Boolean lock = redisTemplate.opsForValue().setIfAbsent("productInfo",uuid,30,TimeUnit.SECONDS);
//        if (lock){
//              //加锁成功，执行相关业务
//              //设置过期时间  要与加锁同步
//              redisTemplate.expire("productInfo",30, TimeUnit.SECONDS);
//              redisTemplate.opsForValue().set("productInfo",);
//              //执行完业务 删除锁
//              //redisTemplate.delete("productInfo");
//              String lockValue = (String) redisTemplate.opsForValue().get("productInfo");
//              if (uuid.equals(lockValue)){
//                  redisTemplate.delete("productInfo");
//              }
//        }else{
//            //加锁失败。。重试
//            return  GetAllProductInfoWithRedisLock();
//        }
//    }
}
