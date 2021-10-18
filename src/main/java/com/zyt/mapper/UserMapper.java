package com.zyt.mapper;

import com.zyt.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.mapper
 * @ClassName: UserMapper
 * @Author: zhou_yangtao@yeah.net
 * @Description: 用户信息管理持久层
 * @Date: 10:40 2021/2/9
 * @Version: 1.0
 */
@Mapper
@Repository(value = "userMapper")
public interface UserMapper {

    //查询当前手机号下是否已经有人进行了注册
    public List<Users> getUserIsFinishedRegsiter(@Param("phoneNum") String phoneNum);

    //用户注册信息添加到数据库
    public int addUserRegisterInfos(Users users);

    //根据用户名查询用户的密码
    public String getUserLoginPass(@Param("userName") String userName);

}
