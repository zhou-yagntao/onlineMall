package com.zyt.constant;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.constant
 * @ClassName: SecondKillConstant
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀变量信息
 * @Date: 16:36 2021/3/12
 * @Version: 1.0
 */
public class SecondKillConstant {

    //场次缓存前缀
    public static final String SECOND_KILL_SESSION_CHACHE_PREFIX = "seckill:sessions";

    //商品信息缓存前缀
    public static final String SECOND_KILL_PRODUCT_CHACHE_PREFIX = "seckill:product";

    //库存相关信号量
    public static final String SECOND_KILL_PRODUCT_STOCK_SEMAPHORE = "seckill:stock:"; //商品随机码

    public static  final String SECOND_KILL_UPLOAD_LOCK = "seckill:upload:lock";
}
