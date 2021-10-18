package com.zyt.service.impl.batchOperaionService.impl;

import com.zyt.config.SpringUtils;
import com.zyt.entity.Product;
import com.zyt.mapper.BatchSuperviseProductMapper;
import com.zyt.service.batchOperaionService.BatchSuperviseProductService;
import com.zyt.utils.DateUtils;
import com.zyt.utils.ExcelUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: BatchSuperviseProductServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 批量管理商品信息服务层实现层
 * @Date: 16:24 2021/1/27
 * @Version: 1.0
 */
@Service(value = "batchSuperviseProductService")
public class BatchSuperviseProductServiceImpl implements BatchSuperviseProductService {

    private Logger logger = LoggerFactory.getLogger(BatchSuperviseProductServiceImpl.class);

    @Autowired
    private BatchSuperviseProductMapper batchSuperviseProductMapper;

    /**
     * @Method: BatchAddProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:批量添加商品信息处理
     * @Return: int
     * @Exception:
     * @Date: 2021/1/27 17:45
     * @Param: * @param file
     * @Return: int
     */
    @Override
    public int BatchAddProduct(MultipartFile file) {
        //定义返回结果
        int result = 0;
        //判断我们上传的商品信息excel表格是否为空,则抛出异常
        if(file.isEmpty()){
            try {
                throw  new Exception("对不起,当前您上传的文件信息为空");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //定义文件输入流
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //定义一个文件输出接收链表
        List<List<Object>> list = null;
        try {
            list = new ExcelUploadUtils().getBankListByExcel(inputStream,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("首次读取文件信息为:"+list);
        //定义一个链表保存循环遍历链表的结果
        List<Object> productInfoList = null;
        //批量导入的数据
        List<Product> plist = new LinkedList<>();
        //循环遍历输出结果
        for (int i = 0; i < list.size() ; i++) {
            //遍历商品信息后保存到商品信息对象中
            Product product  = new Product();
            //logger.info("循环遍历输出的每条结果为:"+list.get(i));
            productInfoList = list.get(i);
            //保存到我们定义的list集合中的结果为
            logger.info("保存到list集合中的结果为:"+productInfoList);
            product.setProdName(String.valueOf(productInfoList.get(0)));
            product.setProdImage(String.valueOf(productInfoList.get(1)));
            try {
                product.setProdReleaseTime(DateUtils.stringDate(String.valueOf(productInfoList.get(2)),"yyyy-MM-dd"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            product.setProdStatus(String.valueOf(productInfoList.get(3)));
            product.setProdNvipAllPrice(String.valueOf(productInfoList.get(4)));
            product.setProdVipAllPrice(String.valueOf(productInfoList.get(5)));
            product.setProdIntegral(Integer.parseInt(String.valueOf(productInfoList.get(6))));
            product.setProdGrowthvalue(Integer.parseInt(String.valueOf(productInfoList.get(7))));
            product.setProStoreName(String.valueOf(productInfoList.get(8)));
            product.setStoreBrandId(Integer.parseInt(String.valueOf(productInfoList.get(9))));
            product.setOnSheleves(Boolean.getBoolean(String.valueOf(productInfoList.get(10))));
            product.setProdEveryMaxStorage(Integer.parseInt(String.valueOf(productInfoList.get(11))));
            product.setProdMoral(String.valueOf(productInfoList.get(12)));
            product.setUseIntegral(Boolean.getBoolean(String.valueOf(productInfoList.get(13))));
            product.setUseCouple(Boolean.getBoolean(String.valueOf(productInfoList.get(14))));
            logger.info("转化为商品信息对象后的结果为:"+product.toString());
            plist.add(product);
            //result = this.batchSuperviseProductMapper.batchAddProductInfos(product);
        }
        System.out.println(plist.size());
        logger.info("转化为商品信息对象后的结果为:");
        for (int i = 0; i < plist.size(); i++) {
            System.out.println(plist.get(i));
        }
        result = pretreatmentSaveProductInfos(plist);
        return result;
    }

    /**
     * @Method: pretreatmentSaveProductInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:创建线程池，以及处理相关的参数
     * @Return: int
     * @Exception:
     * @Date: 2021/2/4 21:37
     * @Param: * @param list
     * @Return: int
     */
    public  int pretreatmentSaveProductInfos(List<Product> list){
        int successCounts = 0;
        int count = 50; //一个线程所处理的数据条数
        int listSize = list.size(); //计算传入数据的条数
        int runThreadSize = ( listSize / count ) + 1; //计算我们需要创建的线程个数
        List<Product> plist = null;
        //创建一个定容的 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(runThreadSize);
        //设置一个信号量为5的信号量，限制同时运行的线程数量最大为5
        Semaphore semaphore = new Semaphore(5);
        //创建计数器
        CountDownLatch begin  = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(runThreadSize);
        List<Future<Integer>> futures = new ArrayList<>();
        //循环创建线程
        for (int i = 0; i < runThreadSize; i++) {
            int startIndex;
            int endIndex;
            //分割数据
            if((i+1) == runThreadSize){
                startIndex = i *count;
                endIndex = listSize;
                plist =  list.subList(startIndex, endIndex);
            }else{
                startIndex = i * count;
                endIndex = (i+1)*count;
                plist =  list.subList(startIndex, endIndex);
            }
            System.out.println("第"+i+"个线程执行数目"+plist);
            //提交任务进行处理，并返回处理结果
            Future<Integer> future = executorService.submit(new AsynBatchAddProducThreadService(begin,end,plist));
            futures.add(future);
        }
        for (Future<Integer> future:futures){
            try {
                successCounts +=future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            //关闭线程池
            executorService.shutdown();
        }
        //返回插入数据的成功个数
        return  successCounts;
    }


    /**
     * @Method: BatchDeleteProduct
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:批量删除商品信
     * @Return: int
     * @Exception:
     * @Date: 2021/2/1 17:10
     * @Param: * @param params
     * @Return: int
     */
    @Override
    @Transactional
    public int BatchDeleteProduct(String[] params) {
        List<Integer> newParamsList = new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            newParamsList.add(Integer.parseInt(params[i]));
        }
        logger.info("转化后参数为:"+newParamsList);
        //批量删除 若成功则返回结果 否则返回结果0
        return this.batchSuperviseProductMapper.batchDeleteProductInfos(newParamsList) > 0 ? this.batchSuperviseProductMapper.batchDeleteProductInfos(newParamsList) : 0;
    }
}

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl
 * @ClassName: BatchAddProducThread
 * @Author: zhou_yangtao@yeah.net
 * @Description: 批量添加商品信息线程类
 * @Date: 22:10 2021/2/4
 * @Version: 1.0
 */

class AsynBatchAddProducThreadService implements Callable<Integer> {

    private  Logger logger = LoggerFactory.getLogger(AsynBatchAddProducThreadService.class);

    private List<Product> productsList;
    private CountDownLatch start;
    private CountDownLatch end;

    private final Lock lock  = new ReentrantLock();

    /*
    *解决普通类调用持久层获得服务层的方法
    * 第一种:
    *   1.在配置包下定义一个springUtils解析工具类
    *   2.定义一个变量用于存储我们从spring IO容器获得的bean对象
    *   3.通过springUtils工具类，通过类的字节码对象获取对应的bean对象
    * */
    private static   BatchSuperviseProductMapper batchSuperviseProductMapper;

    static {
        batchSuperviseProductMapper = SpringUtils.getBean(BatchSuperviseProductMapper.class);
    }

    /*
    *第二种:
    *   1.保留通过@Authorid获取bean对象
    *   2.修改run()方法中的代码,此处不进行调用持久层函数，改为调用外部方法，由外部方法调用持久层进行处理
    *   3.外部定义一个函数用来处理插入数据
    *
    * */


    public AsynBatchAddProducThreadService(CountDownLatch start, CountDownLatch end, List<Product> list){
        this.start = start;
        this.end = end;
        this.productsList = list;
    }

    /**
     * @Method: call
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理批量添加业务
     * @Return: java.lang.Integer
     * @Exception:
     * @Date: 2021/2/4 21:40
     * @Param: * @param
     * @Return: java.lang.Integer
     */
    @Transactional
    @Override
    public Integer call() throws Exception {

        int result = 0;
        logger.info("线程ID：<{}>开始运行",Thread.currentThread().getId());
        lock.lock();
        try{
            if (null != productsList){
                logger.info("即将向数据库插入数据");
//                for (Product pList:productsList){
//                    logger.info("当前线程处理的信息为:"+pList);
//                    Product product = new Product();
//                    product.setProdName(pList.getProdName());
//                    product.setProdImage(pList.getProdImage());
//                    product.setProdReleaseTime(pList.getProdReleaseTime());
//                    product.setProdStatus(pList.getProdStatus());
//                    product.setProdNvipAllPrice(pList.getProdNvipAllPrice());
//                    product.setProdVipAllPrice(pList.getProdVipAllPrice());
//                    product.setProdIntegral(pList.getProdIntegral());
//                    product.setProdGrowthvalue(pList.getProdGrowthvalue());
//                    product.setProStoreName(pList.getProStoreName());
//                    product.setStoreBrandId(pList.getStoreBrandId());
//                    product.setIsOnSheleves(pList.getIsOnSheleves());
//                    product.setProdEveryMaxStorage(pList.getProdEveryMaxStorage());
//                    product.setProdMoral(pList.getProdMoral());
//                    product.setIsUseIntegral(pList.getIsUseIntegral());
//                    product.setIsUseCouple(pList.getIsUseCouple());
//                    logger.info("转化为product对象结果为:"+product);
//                    //持久层处理函数处理批量添加数据
//                    try{
//                        result = batchSuperviseProductMapper.batchAddProductInfos(product);
//                        //主线程等待子线程全部跑完才继续运行。设置60秒等待时间，超时后继续执行。
//                        start.await(60,TimeUnit.SECONDS);
//                    }catch (Exception e){
//                        logger.info("向数据库添加数据报错:"+e.getMessage());
//                    }finally {
//                        //计数器减1
//                        end.countDown();
//                    }
//                }
                //批量向数据库插入数据处理模块
                try{
                    result = batchSuperviseProductMapper.batchAddProductInfos(productsList);
                    //主线程等待子线程全部跑完才继续运行。设置60秒等待时间，超时后继续执行。
                    start.await(60,TimeUnit.SECONDS);
                }catch (Exception e){
                    logger.info("向数据库添加数据报错:"+e.getMessage());
                }finally {
                    //计数器减1
                    end.countDown();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("批量添加失败信息:"+e.getMessage());
        }finally {
            lock.unlock();
        }
        return result;
    }

}


