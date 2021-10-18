package com.zyt.mapper;

import com.zyt.entity.Addreess;
import com.zyt.entity.vo.PersonAcceptAddressInfoTo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: AddressMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 地址管理持久层模块
 * @Date: 11:36 2021/2/25
 * @Version: 1.0
 */
@Mapper
@Repository(value = "addressMapper")
public interface AddressMapper {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:添加用户地址管理信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 12:26
      * @Param: * @param null
      * @Return:
      */
    public int addPersonAcceptAddress(@Param("addreess") Addreess addreess);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description: 查询个人收获地址信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 12:58
      * @Param: * @param null
      * @Return:
      */
    public List<Addreess> getCurrUserAcceptAddress(@Param("loginUserName") String loginUserName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询跟人当前选中收货地址信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 13:36
      * @Param: * @param null
      * @Return:
      */
    public Addreess getAddressInfoByAddressId(@Param("address_id") int address_id);

    public Addreess getInitUserAcceptAddress(@Param("isCheck") boolean isCheck, @Param("userName") String userName);
}
