package com.hys.login.manager;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyCasRealm extends CasRealm {

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

}
