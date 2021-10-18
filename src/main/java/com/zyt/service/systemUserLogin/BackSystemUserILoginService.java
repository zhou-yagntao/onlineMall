package com.zyt.service.systemUserLogin;

import com.zyt.entity.SystemUser;
import com.zyt.entity.vo.LoginSuccessVo;
import com.zyt.entity.vo.SystemMenuVo;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.systemUserLogin
 * @ClassName: BackSystemUserILoginService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 后台用户登录服务层
 * @Date: 20:56 2021/4/2
 * @Version: 1.0
 */
public interface BackSystemUserILoginService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description://查询当前用户是否存在
      * @Return:
      * @Exception:
      * @Date: 2021/4/2 21:46
      * @Param: * @param null
      * @Return:
      */
    public LoginSuccessVo checkIsExistsOfCurrUserByUserName(String userName);

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:查询当前用户当前角色的菜单信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/2 21:46
      * @Param: * @param null
      * @Return:
      */
    public List<SystemMenuVo> getSystemMeunInfoOfCurrUserRole(String role);
}
