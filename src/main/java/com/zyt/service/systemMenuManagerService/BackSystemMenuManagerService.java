package com.zyt.service.systemMenuManagerService;

import com.zyt.entity.SystemUser;
import com.zyt.entity.vo.LoginSuccessVo;
import com.zyt.entity.vo.SystemMenuVo;

import java.util.List;

/**
 * @ProjectName: online_drink_mall
 * @Package: com.zyt.service.systemMenuManagerService
 * @ClassName: BackSystemMenuManagerService
 * @Author: zhou_yangtao@yeah.net
 * @Description: 后台系统菜单栏模块管理层
 * @Date: 21:28 2021/4/2
 * @Version: 1.0
 */
public interface BackSystemMenuManagerService {

     /**
      * @Method:
      * @Author: zhou_yangtao@yeah.net
      * @Version  1.0
      * @Description:获得当前用户当前角色下的菜单栏信息
      * @Return:
      * @Exception:
      * @Date: 2021/4/2 21:30
      * @Param: * @param null
      * @Return:
      */
    public List<SystemMenuVo> getSystemMeunInfoOfCurrUserRole(SystemUser systemUser);
}
