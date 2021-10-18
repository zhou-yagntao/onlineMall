package com.zyt.algorithm;

import com.zyt.entity.Product;
import com.zyt.entity.ProductClick;
import com.zyt.entity.UserActive;
import com.zyt.entity.UserSimilarity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.algorithm
 * @ClassName: CollaborativeFilteringRecommendationAlgorithm
 * @Author: zhou_yangtao@yeah.net
 * @Description: 协调过滤算法的算法实现---用于进行商品推荐的实现
 * @Date: 16:14 2021/2/9
 * @Version: 1.0
 */
public class CollaborativeFilteringRecommendAlgorith {

     /**
      * @Method: updateBuyingBehavior
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:更新用户的购买行为，最终插入数据库中
      * @Return: 
      * @Exception: 
      * @Date: 2021/2/9 16:19
      * @Param: * @param null
      * @Return: 
      */
     public static  boolean updateBuyingBehavior(int userId,int itemsId){
         boolean flag = false;
         return  flag;
     }

    /**
     * @Method: assembleBehaviors
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:将用户的购买行为组装成一个map,key为userId，value也是一个map，这个map记录的是每个类目下的商品信息以及它对应的点击量
     * @Return: java.util.concurrent.ConcurrentHashMap<java.lang.Long,java.util.concurrent.ConcurrentHashMap<java.lang.Long,java.lang.Long>>
     * @Exception:
     * @Date: 2021/2/9 16:23
     * @Param: * @param userActiveList  用户的购买行为列表
     * @Return: java.util.concurrent.ConcurrentHashMap<java.lang.Long,java.util.concurrent.ConcurrentHashMap<java.lang.Long,java.lang.Long>>
     * @param userActiveList
     */
     public static ConcurrentHashMap<Long,ConcurrentHashMap<Long,Long>> assembleBehaviors(List<ProductClick> userActiveList){
         ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>> activeMap = new ConcurrentHashMap<Long, ConcurrentHashMap<Long, Long>>();
         //通过for循环查询用户点击行为数据
         for (ProductClick userActive:userActiveList){
              //获取用户id
              Long userId  = userActive.getUser_id().longValue();
              //获得商品id
              Long prodId = userActive.getProClickId().longValue();
              //获得该编号商品的点击量
              Long prodHits  = userActive.getProClickCounts().longValue();
              //判断当前集合中是否已经存在了该UserId的信息，若存在则更新 不存在则添加
             if (activeMap.containsKey(userId)){
                 ConcurrentHashMap<Long,Long> tempMap = activeMap.get(userId);
                 tempMap.put(prodId,prodHits);
                 activeMap.put(userId,tempMap);
             }else{
                 //不存在就直接添加
                  ConcurrentHashMap<Long,Long> concurrentHashMap = new ConcurrentHashMap<>();
                  concurrentHashMap.put(prodId,prodHits);
                  activeMap.put(userId,concurrentHashMap);
             }

         }
         return  activeMap;
     }

    /**
     * @Method: calcSimilarityBetweenUsers
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:计算用户与用户之间的相似性，返回计算出的用户与用户之间的相似度对象
     * @Return: java.util.List<com.zyt.entity.UserSimilarity>
     * @Exception:
     * @Date: 2021/2/9 16:56
     * @Param: * @param activeMap
     * @Return: java.util.List<com.zyt.entity.UserSimilarity>
     */
     public static List<UserSimilarity> calcSimilarityBetweenUsers(ConcurrentHashMap<Long,ConcurrentHashMap<Long,Long>> activeMap){
         //用户之间的相似度集合
         List<UserSimilarity> similarities = new ArrayList<>();
         //获取所有的键的集合
         Set<Long> userSet = activeMap.keySet();
         System.out.println(userSet);
         //将获得的键集合放到定义的list数组中
         List<Long> userIdList = new ArrayList<>(userSet);
         //若用户id数组的长度小于2则说明当前的map集合中只有一个map集合的购买行为，或者没有购买行为，则直接删除
         if (userIdList.size() < 2){
             return  similarities;
         }
         //否则的话就计算用户之间的相似度
         for (int i = 0; i < userIdList.size() - 1; i++) {
             for (int j = i+1; j < userIdList.size(); j++) {
                 //分别获取两个用户对每个商品的点击量
                 ConcurrentHashMap<Long,Long> userClickProdHits = activeMap.get(userIdList.get(i));
                 ConcurrentHashMap<Long,Long> userRefClickProdHits = activeMap.get(userIdList.get(j));
                 //获取两个map集合中的id集合
                 Set<Long> ket_1Set = userClickProdHits.keySet();
                 Set<Long> key_2Set = userRefClickProdHits.keySet();
                 Iterator<Long> iterator_1 = ket_1Set.iterator();
                 Iterator<Long> iterator_2 = key_2Set.iterator();
                 //两个用户之间的相似度
                 double similarity = 0.0;
                 //余弦相似度公式中的分子
                 double molecule = 0.0;
                 //余弦相似度公式中的分母
                 double denminator = 1.0;
                 // 余弦相似度公式中分母根号下的两个向量的模的值
                 double vector1 = 0.0;
                 double vector2 = 0.0;
                 while(iterator_1.hasNext() && iterator_2.hasNext()){
                     Long iterator_1Id = iterator_1.next();
                     Long iterator_2Id = iterator_2.next();
                     //获取对商品的点击量
                     Long hits_1  = userClickProdHits.get(iterator_1Id);
                     Long hits_2 = userRefClickProdHits.get(iterator_2Id);
                     System.out.println("===============>"+hits_1+"\t"+hits_2);
                     //计算分子
                     molecule += hits_1 * hits_2;
                     //累计分母中的两个向量的模
                     vector1 += Math.pow(hits_1,2);
                     vector2 += Math.pow(hits_2,2);
                 }
                 //计算分母
                 denminator = Math.sqrt(vector1)+Math.sqrt(vector2);
                 //计算相似度
                 similarity = molecule / denminator;
                 UserSimilarity userSimilarity = new UserSimilarity();
                 userSimilarity.setUserId(userIdList.get(i));
                 userSimilarity.setUserRefId(userIdList.get(j));
                 userSimilarity.setSimilarity(similarity);
                 similarities.add(userSimilarity);
             }
         }
         return  similarities;
     }

    /**
     * @Method: getSimilarityBetweenUsers
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:找出与userId购买行为最相似的topN个用户
     * @Return: java.util.List<java.lang.Long>
     * @Exception:
     * @Date: 2021/2/9 16:58
     * @Param: * @param userId 用户相似度列表
     * @param userSimilarityDTOList
     * @param topN 与userId相似用户的数量
     * @Return: java.util.List<java.lang.Long> 与usereId最相似的topN个用户
     */
    public static List<Long> getSimilarityBetweenUsers(Long userId, List<UserSimilarity> userSimilarityDTOList, Integer topN) {

        // 用来记录与userId相似度最高的前N个用户的id
        List<Long> similarityList = new ArrayList<Long>(topN);
        System.out.println("<=======================>"+userSimilarityDTOList);
        // 堆排序找出最高的前N个用户，建立小根堆，遍历的时候当前的这个相似度比堆顶元素大就剔掉堆顶的值，把这个数入堆(把小的都删除干净,所以要建立小根堆)
        PriorityQueue<UserSimilarity> minHeap = new PriorityQueue<UserSimilarity>(new Comparator<UserSimilarity>(){
            @Override
            public int compare(UserSimilarity o1, UserSimilarity o2) {
                if (o1.getSimilarity() - o2.getSimilarity() > 0){
                    return  1;
                }else if(o1.getSimilarity() - o2.getSimilarity() == 0){
                    return 0;
                }else{
                    return  -1;
                }
            }
        });

        for (UserSimilarity userSimilarityDTO : userSimilarityDTOList) {
            if (minHeap.size() < topN) {
                minHeap.offer(userSimilarityDTO);
                System.out.println(minHeap.peek().getSimilarity());
            } else if (minHeap.peek().getSimilarity() < userSimilarityDTO.getSimilarity()) {
                minHeap.poll();
                minHeap.offer(userSimilarityDTO);
            }
        }
        // 把得到的最大的相似度的用户的id取出来(不要取它自己)
        for (UserSimilarity userSimilarity : minHeap) {
            similarityList.add(userSimilarity.getUserId().equals(userId) ? userSimilarity.getUserRefId() : userSimilarity.getUserId());
        }
        System.out.println("========================>"+similarityList);
        return similarityList;
    }

    /**
     * @Method: getRecommendateCategory2
     * @Author: Justin
     * @Version  1.0
     * @Description:到similarUserList中的用户访问的商品类目中查找userId不经常点击的商品类目中获得被推荐的类目id
     * @Return: java.util.List<java.lang.Long>
     * @Exception:
     * @Date: 2021/2/9 17:01
     * @Param: * @param userId
     * @param similarUserList 用userId相似的用户集合
     * @param userActiveList  所有用户的浏览行为
     * @Return: java.util.List<java.lang.Long> 以推荐给userId的商品类目id列表
     */
    public static List<Long> getRecommendateCategory2(Long userId, List<Long> similarUserList, List<UserActive> userActiveList) {

        List<Long> recommeddateProductList = new ArrayList<Long>();

        // userId的浏览行为列表
        List<UserActive> userIdActiveList = findUsersBrowsBehavior(userId, userActiveList);

        // 对userId的浏览行为按照二级类目id排个序，方便后续与推荐的用户的浏览行为中的二级类目的点击次数直接相减，避免时间复杂度为O(n2)
        Collections.sort(userIdActiveList, new Comparator<UserActive>(){
            @Override
            public int compare(UserActive o1, UserActive o2) {
                return o1.getProductId().compareTo(o2.getProductId());
            }
        });

        // 1.从与useId浏览行为相似的每个用户中找出一个推荐的商品类目
        for (Long refId : similarUserList) {
            // 计算当前用户所点击的二级类目次数与被推荐的用户所点击的商品类目的次数的差值
            // 找到当前这个用户的浏览行为
            List<UserActive> currActiveList = findUsersBrowsBehavior(refId, userActiveList);

            // 排序，同上述理由
            Collections.sort(currActiveList, new Comparator<UserActive>(){
                @Override
                public int compare(UserActive o1, UserActive o2) {
                    return o1.getProductId().compareTo(o2.getProductId());
                }
            });

            // 记录差值最大的商品类目的id
            long maxCategory2 = 0L;

            // 记录最大的差值
            double maxDifference = 0.0;
            for (int i = 0; i < currActiveList.size(); i++) {
                // 求出点击量差值最大的商品类目，即为要推荐的类目
                double difference = Math.abs(currActiveList.get(i).getHits() - userIdActiveList.get(i).getHits());
                if (difference > maxDifference) {
                    maxDifference = difference;
                    maxCategory2 = currActiveList.get(i).getProductId();
                }
            }
            recommeddateProductList.add(maxCategory2);
        }
        return recommeddateProductList;
    }

    /**
     * 找到当前用户的浏览行为列表
     * @param userId 当前用户id
     * @param userActiveList 所有用户的浏览行为列表
     * @return 当前用户的浏览行为列表
     */
    public static List<UserActive> findUsersBrowsBehavior(Long userId, List<UserActive> userActiveList) {
        List<UserActive> currActiveList = new ArrayList<UserActive>();
        for (UserActive userActive : userActiveList) {
            if (userActive.getUserId().equals(userId)) {
                currActiveList.add(userActive);
            }
        }
        return currActiveList;
    }

    /**
     * 找到当前商品列表中点击量最高的商品
     * @param productList 商品列表
     * @return 点击量最高的商品
     */
    public static Product findMaxHitsProduct(List<? extends Product> productList) {
        if (productList == null || productList.size() == 0) {
            return null;
        }
        // 记录当前最大的点击量
        Long maxHits = 0L;

        // 记录当前点击量最大的商品
        Product product = null;
        for (Product temp : productList) {
//            if (temp.getHits() >= maxHits) {
//                maxHits = temp.getHits();
//                product = temp;
//            }
        }
        return product;
    }






















}
