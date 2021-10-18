package com.zyt.service.impl.userAddress.impl;

import com.zyt.entity.Addreess;
import com.zyt.entity.vo.PersonAcceptAddressInfoTo;
import com.zyt.mapper.AddressMapper;
import com.zyt.mapper.VipMemberMapper;
import com.zyt.service.userAddress.AddressManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.impl.userAddress.impl
 * @ClassName: AddressManagerServiceImpl
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户地址管理服务层实现模块
 * @Date: 12:19 2021/2/25
 * @Version: 1.0
 */
@Service(value = "addressManagerServiceImpl")
public class AddressManagerServiceImpl implements AddressManagerService {

    @Autowired
    private VipMemberMapper vipMemberMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * @Method: AddPersonAcceptAddress
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:获得个人收货地址信息
     * @Return: int
     * @Exception:
     * @Date: 2021/2/25 12:56
     * @Param: * @param address
     * @Return: int
     */
    @Override
    @Transactional
    public int AddPersonAcceptAddress(String[] address) {
        Integer userId = this.vipMemberMapper.getUserIdByUserName(address[0]);
        int user_Id = 0;
        if (userId == null){
            user_Id = 0;
        }else{
            user_Id = userId.intValue();
        }
        Addreess addreess = new Addreess();
        addreess.setUser_id(user_Id);
        addreess.setUser_name(address[0]);
        addreess.setUser_area(address[2]);
        addreess.setAdd_detail(address[1]);
        addreess.setContact_num(address[3]);
        int result = this.addressMapper.addPersonAcceptAddress(addreess);
        return 0;
    }

    /**
     * @Method: GetCurrUserAcceptAddress
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询个人收获地址信息
     * @Return: java.util.List<com.zyt.entity.Addreess>
     * @Exception:
     * @Date: 2021/2/25 12:56
     * @Param: * @param loginUserName
     * @Return: java.util.List<com.zyt.entity.Addreess>
     */
    @Override
    public List<Addreess> GetCurrUserAcceptAddress(String loginUserName) {
        return this.addressMapper.getCurrUserAcceptAddress(loginUserName) != null ? this.addressMapper.getCurrUserAcceptAddress(loginUserName) : null;
    }

    /**
     * @Method: getAddressInfoByAddressId
     * @Author: zhou_yangtao@yeah.net
     * @Version  1.0
     * @Description:查询个人的当前选择中的收货地址信息
     * @Return: com.zyt.entity.vo.PersonAcceptAddressInfoTo
     * @Exception:
     * @Date: 2021/2/25 13:33
     * @Param: * @param addressId
     * @Return: com.zyt.entity.vo.PersonAcceptAddressInfoTo
     */
    @Override
    public PersonAcceptAddressInfoTo getAddressInfoByAddressId(String addressId) {
        int address_id  = Integer.parseInt(addressId);
        PersonAcceptAddressInfoTo personAcceptAddressInfoTo = new PersonAcceptAddressInfoTo();
        Addreess addreess = this.addressMapper.getAddressInfoByAddressId(address_id);
        if (addreess == null){
            personAcceptAddressInfoTo = new PersonAcceptAddressInfoTo();
        }else{
            personAcceptAddressInfoTo.setAddressDetails(addreess.getAdd_detail());
            personAcceptAddressInfoTo.setAccpter(addreess.getUser_name());
            personAcceptAddressInfoTo.setContactNum(addreess.getContact_num());
        }
        return  personAcceptAddressInfoTo != null ? personAcceptAddressInfoTo : null;
    }

    @Override
    public Addreess GetInitUserAcceptAddress(boolean isCheck, String userName) {
        return this.addressMapper.getInitUserAcceptAddress(isCheck,userName)!= null ? this.addressMapper.getInitUserAcceptAddress(isCheck,userName) : null;
    }
}
