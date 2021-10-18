package com.zyt.service.impl.SecKillSessionManagerService.impl;

import com.zyt.entity.SecKillSession;
import com.zyt.mapper.SecKillSessionMapper;
import com.zyt.service.SecKillProdRelationManagerService.SecKillProdRelationService;
import com.zyt.service.secKillSessionManagerService.SecKillSessionManagerService;
import com.zyt.service.storeService.NewStoreSettledService;

import com.zyt.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.SecKillSessionMoudleService.impl
 * @ClassName: SecKillSessionManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 秒杀服务管理服务层实现模块
 * @Date: 11:35 2021/3/6
 * @Version: 1.0
 */
@Service(value = "secKillSessionManagerService")
public class SecKillSessionManagerServiceImpl implements SecKillSessionManagerService {

    private Logger logger = LoggerFactory.getLogger(SecKillSessionManagerServiceImpl.class);

    @Autowired
    private NewStoreSettledService newStoreSettledService;

    @Autowired
    private SecKillSessionMapper secKillSessionMapper;

    @Autowired
    private SecKillProdRelationService secKillProdRelationService;

    /**
     * @Method: saveSecKillSessionInfos
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:处理秒杀场次信息
     * @Return: int
     * @Exception:
     * @Date: 2021/3/6 13:08
     * @Param: * @param seckillSessions
     * @param userName
     * @Return: int
     */

    @Override
    @Transactional
    public int saveSecKillSessionInfos(String[] seckillSessions, String userName) {
        //根据姓名去查询店铺编号
        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        SecKillSession secKillSession = new SecKillSession();
        //封装数据
        secKillSession.setSec_kill_name(seckillSessions[0].substring(2,seckillSessions[0].length()-1));
        try {
           String date = seckillSessions[1].replace("Z", " UTC");
           SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
           secKillSession.setStart_time(format.parse(date.substring(1,date.length()-1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String date = seckillSessions[2].replace("Z", " UTC");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            secKillSession.setEnd_time(format.parse(date.substring(1,date.length()-1)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("true".equals(seckillSessions[3].substring(0,seckillSessions[3].length()-1))){
            secKillSession.setEnable_status(true);
        }else{
            secKillSession.setEnable_status(false);
        }
        secKillSession.setCreate_time(new Date());
        secKillSession.setStore_id(storeId);
        logger.info("封装之后的结果为:"+secKillSession.toString());
        int result = this.secKillSessionMapper.saveSecKillSessionInfos(secKillSession);
        return result > 0 ? result : 0;
    }

    /**
     * @Method: getAllSecKillInfoOfCurrStore
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前店铺秒杀场次信息
     * @Return: java.util.List<com.zyt.entity.SecKillSession>
     * @Exception:
     * @Date: 2021/3/6 16:46
     * @Param: * @param pageSize
     * @param currentPage
     * @param userName
     * @Return: java.util.List<com.zyt.entity.SecKillSession>
     */
    @Override
    public List<SecKillSession> getAllSecKillInfoOfCurrStore(String pageSize, String currentPage, String userName) {
        //根据姓名去查询店铺编号

        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        int initPageSize = Integer.parseInt(pageSize);
        int initCurrentPage = Integer.parseInt(currentPage);
        int start = (initCurrentPage - 1) * initPageSize;
        int end  = initPageSize;
        Date date = new Date();
        String subTime = "%"+DateUtils.dateString(date,"yyyy-MM-dd").substring(5,10)+"%";
        logger.info("查询参数为:"+start+"\t"+end+"\t"+storeId+"\t"+subTime);
        List<SecKillSession> secKillSessions = this.secKillSessionMapper.getAllSecKillInfoOfCurrStore(start,end,storeId,subTime);
        return secKillSessions != null ? secKillSessions : null;
    }

    /**
     * @Method: getSecKillInfoCountsOfCurrStore
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得当前店铺秒杀数目信息
     * @Return: int
     * @Exception:
     * @Date: 2021/3/6 16:46
     * @Param: * @param userName
     * @Return: int
     */
    @Override
    public int getSecKillInfoCountsOfCurrStore(String userName) {
        //根据姓名去查询店铺编号
        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        Date date = new Date();
        String subTime = "%"+DateUtils.dateString(date,"yyyy-MM-dd").substring(5,10)+"%";
        int count = this.secKillSessionMapper.getSecKillInfoCountsOfCurrStore(storeId,subTime);
        return count > 0 ? count : 0;

    }

    /**
     * @Method: getKillInfoByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据条件获得秒杀
     * @Return: java.util.List<com.zyt.entity.SecKillSession>
     * @Exception:
     * @Date: 2021/3/8 13:54
     * @Param: * @param pageSize
     * @param currentPage
     * @param userName
     * @param killName
     * @param isEnable
     * @Return: java.util.List<com.zyt.entity.SecKillSession>
     */

    @Override
    public List<SecKillSession> getKillInfoByItems(String pageSize, String currentPage, String userName, String killName, String isEnable) {
        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        int initPageSize = Integer.parseInt(pageSize);
        int initCurrentPage = Integer.parseInt(currentPage);
        int start = (initCurrentPage - 1) * initPageSize;
        int end  = initPageSize;
        Boolean flag;
        if ("是".equals(isEnable)){
            flag = true;
        }else{
            flag = false;
        }
        return this.secKillSessionMapper.getKillInfoByItems(storeId,start,end,killName,flag) != null ?
               this.secKillSessionMapper.getKillInfoByItems(storeId,start,end,killName,flag) :
                null;

    }

    /**
     * @Method: getKillInfoCountsByItems
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询当前场次秒杀新的数量
     * @Return: int
     * @Exception:
     * @Date: 2021/3/8 13:59
     * @Param: * @param pageSize
     * @param currentPage
     * @param userName
     * @param killName
     * @param isEnable
     * @Return: int
     */
    @Override
    public int getKillInfoCountsByItems(String pageSize, String currentPage, String userName, String killName, String isEnable) {
        int storeId = this.newStoreSettledService.getStoreIdByStoreOwnerUserName(userName);
        Boolean flag;
        if ("是".equals(isEnable)){
            flag = true;
        }else{
            flag = false;
        }
        return this.secKillSessionMapper.getKillInfoCountsByItems(storeId,killName,flag) > 0 ?
                this.secKillSessionMapper.getKillInfoCountsByItems(storeId,killName,flag) :
                0;

    }

    /**
     * @Method: BatchDeleteKillInfo
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:批量删除秒杀信息
     * @Return: int
     * @Exception:
     * @Date: 2021/3/8 14:37
     * @Param: * @param killId
     * @Return: int
     */
    @Override
    @Transactional
    public int BatchDeleteKillInfo(String[] killId) {
        List<Integer> secKillIdinfo = new ArrayList<>();
        for (int i = 0; i < killId.length; i++) {
            secKillIdinfo.add(Integer.parseInt(killId[i]));
        }
        logger.info("转化后的删除id信息为:"+secKillIdinfo);
        this.secKillSessionMapper.batchDeleteKillInfo(secKillIdinfo);
        return  1;

    }

    /**
     * @Method: DeleteKillInfoByKillId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:根据id删除秒杀场次信息
     * @Return: int
     * @Exception:
     * @Date: 2021/3/8 14:47
     * @Param: * @param killId
     * @Return: int
     */
    @Override
    public int DeleteKillInfoByKillId(String killId) {
        int kill_id = Integer.parseInt(killId);
        return this.secKillSessionMapper.deleteKillInfoByKillId(kill_id) > 0 ? this.secKillSessionMapper.deleteKillInfoByKillId(kill_id) : 0;

    }

    /**
     * @Method: getSessionsInfoOfLatestThreeDay
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获取最近三天的秒杀场次信息
     * @Return: java.util.List<com.zyt.entity.SecKillSession>
     * @Exception:
     * @Date: 2021/3/12 14:18
     * @Param: * @param
     * @Return: java.util.List<com.zyt.entity.SecKillSession>
     */
    @Override
    public List<SecKillSession> getSessionsInfoOfLatestThreeDay() {
        String startTime = startTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String endTime = endTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<SecKillSession> secKillSessionList =  this.secKillSessionMapper.getSessionsInfoOfLatestThreeDay(startTime,endTime);
        //如若我们当前的秒杀优惠场次不为空
        if (secKillSessionList != null && secKillSessionList.size() > 0){
             List<SecKillSession> secKillSessions =secKillSessionList.stream().map(secKillSession -> {
               secKillSession.setSecKillProdRelationList(this.secKillProdRelationService.getRelativeProdInfoOfCurrSeesionId(secKillSession.getSec_kill_id()));
               return  secKillSession;
             }).collect(Collectors.toList());
             return  secKillSessions;
        }
        return  null;
    }

    //TODO 1计算当前时间三天后的时间
    //TODO 1.1计算开始时间
    private static LocalDateTime startTime(){
        LocalDate now = LocalDate.now();
        LocalTime low_time = LocalTime.MIN;
        LocalDateTime start  =  LocalDateTime.of(now,low_time);
        return  start;
    }

    //TODO 1.2计算结束时间
    private static  LocalDateTime endTime(){
        LocalDate now = LocalDate.now();
        //计算当前日期两天后的时间
        LocalDate localDate = now.plusDays(2);
        LocalTime high_time = LocalTime.MAX;
        LocalDateTime end = LocalDateTime.of(localDate,high_time);
        return  end;
    }
}
