package com.zyt.service.userAddress;

import com.zyt.entity.Addreess;
import com.zyt.entity.CartItem;
import com.zyt.entity.Result;
import com.zyt.entity.vo.PersonAcceptAddressInfoTo;
import org.elasticsearch.cluster.metadata.AliasAction;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.userAddress
 * @ClassName: AddressManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户收获地址管理服务层
 * @Date: 12:17 2021/2/25
 * @Version: 1.0
 */
public interface AddressManagerService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:用户收获地址管理模块
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 12:18
      * @Param: * @param null
      * @Return:
      */
    public int AddPersonAcceptAddress(String []address);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户的收货地址信息
      * @Return:
      * @Exception:
      * @Date: 2021/2/25 12:55
      * @Param: * @param null
      * @Return:
      */
    public List<Addreess> GetCurrUserAcceptAddress(String loginUserName);
 
     /**
      * @Method: 
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:
      * @Return: 
      * @Exception: 
      * @Date: 2021/2/25 14:48
      * @Param: * @param null
      * @Return: 
      */
    public PersonAcceptAddressInfoTo getAddressInfoByAddressId(String addressId);


    public Addreess GetInitUserAcceptAddress(boolean isCheck, String userName);
}
