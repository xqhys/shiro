package com.hys.login.manager;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class SampleReaml extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set<String> setRoles = new HashSet<String>();
        setRoles.add("admin");

        Set<String> setPermissions = new HashSet<String>();
        setPermissions.add("user:create");
        setPermissions.add("user:delete");

        authorizationInfo.setRoles(setRoles);
        authorizationInfo.setStringPermissions(setPermissions);

        return authorizationInfo;

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /*
        * userPwd：实际开发存储在数据库中
        * salt2：  实际开发存储在数据库中
        **/
        String userName = "admin";
        String userPwd = "6b655c32d5c41fecbe0edeebfb50f46a";//加密后的密码
        String salt2 = "ae557c76a58ba43b5fed9dc8ef087eb7";
        SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(userName,userPwd,getName());
        sai.setCredentialsSalt(ByteSource.Util.bytes(userName+salt2));//设置盐

        return sai;
    }

    public static void main(String[] args) {
        //保存密码的方式
        String algorithmName = "md5";
        String username = "admin";
        String password = "1111";
        String salt1 = username;
        String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
        int hashIterations = 2;
        SimpleHash hash = new SimpleHash(algorithmName, password, salt1 + salt2, hashIterations);
        System.out.println("密码："+hash.toString()+"盐："+salt2);
    }


}
