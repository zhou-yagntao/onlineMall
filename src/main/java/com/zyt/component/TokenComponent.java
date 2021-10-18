package com.zyt.component;

//import com.alibaba.fastjson.JSON;
//import com.zyt.constant.UserLoginTokenConstant;
//import com.zyt.entity.security.SelfUserEntity;
//import com.zyt.utils.ChangeJsonTools;
//import io.jsonwebtoken.CompressionCodec;
//import io.jsonwebtoken.CompressionCodecs;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @ProjectName: online_drink_mall
// * @Package: com.zyt.component
// * @ClassName: TokenComponent
// * @Author: zhou_yangtao@yeah.net
// * @Description: token组件
// * @Date: 19:42 2021/3/15
// * @Version: 1.0
// */
//@Component
//public class TokenComponent {
//
//    /**
//     * @Method: createTokenOfCurrUser
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:创建token
//     * @Return: java.lang.String
//     * @Exception:
//     * @Date: 2021/3/15 20:12
//     * @Param: * @param userName
//     * @Return: java.lang.String
//     */
//    public  String createTokenOfCurrUser(SelfUserEntity selfUserEntity){
//        String token  = Jwts.builder()
//                            //用户名+id
//                            .setId(selfUserEntity.getUserId()+"")
//                            .setSubject(selfUserEntity.getUsername())
//                            //签发时间
//                            .setIssuedAt(new Date())
//                            .claim("authorities", JSON.toJSONString(selfUserEntity.getAuthorities()))
//                            //签发人
//                            .setIssuer(UserLoginTokenConstant.USER_LOGIN_TOKEN_ISSUSER)
//                            //失效时间
//                            .setExpiration(new Date(System.currentTimeMillis()+ UserLoginTokenConstant.USER_LOGIN_TOKEN_ECRPIRATION))
//                            //签名算法+秘钥
//                            .signWith(SignatureAlgorithm.HS512,UserLoginTokenConstant.USER_LOGIN_TOKEN_SECRET_KEY)
//                            .compressWith(CompressionCodecs.GZIP)
//                            .compact();
//        return token;
//    }
//
//
//    /**
//     * @Method: getUserInfoFromToken
//     * @Author: zhou_yangtao@yeah.net
//     * @Version  1.0
//     * @Description:获取用户信息
//     * @Return: java.lang.String
//     * @Exception:
//     * @Date: 2021/3/15 20:13
//     * @Param: * @param token
//     * @Return: java.lang.String
//     */
//    public String getUserInfoFromToken(String token){
//
//        return Jwts.parser().setSigningKey(UserLoginTokenConstant.USER_LOGIN_TOKEN_SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
//    }
//
//     /**
//      * @Method:
//      * @Author: zhou_yangtao@yeah.net
//      * @Version  1.0
//      * @Description:删除token
//      * @Return:
//      * @Exception:
//      * @Date: 2021/3/15 20:14
//      * @Param: * @param null
//      * @Return:
//      */
//    public void removeToken(String token){
//    }
//}
