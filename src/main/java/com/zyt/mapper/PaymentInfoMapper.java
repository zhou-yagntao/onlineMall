package com.zyt.mapper;

import com.zyt.entity.PaymentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: PaymentInfoMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 支付宝支付流水信息
 * @Date: 14:22 2021/3/4
 * @Version: 1.0
 */
@Mapper
@Repository(value = "paymentInfoMapper")
public interface PaymentInfoMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:保存交易流水信息
      * @Return:
      * @Exception:
      * @Date: 2021/3/4 14:45
      * @Param: * @param null
      * @Return:
      */
    public void savePayMentInfo(@Param("paymentInfo") PaymentInfo paymentInfo);
}
