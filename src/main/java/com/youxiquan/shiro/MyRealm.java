package com.youxiquan.shiro;

import com.youxiquan.pojo.Admin;
import com.youxiquan.service.AdminService;
import com.youxiquan.service.Services;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户名
        String username = principalCollection.getPrimaryPrincipal().toString();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //获得授权角色
        authorizationInfo.setRoles(adminService.getRoles(username));
        //获得授权权限
//        authorizationInfo.setStringPermissions(adminService.getPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //获取用户名密码
        String username = authenticationToken.getPrincipal().toString();
        Admin admin = adminService.getAdminByUsername(username);
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);//使用账号作为盐值
        if (admin != null) {
            //得到用户账号和密码存放到authenticationInfo中用于Controller层的权限判断 第三个参数随意不能为null
            AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(),
                    credentialsSalt,getName() );
            return authenticationInfo;
        } else {
            return null;
        }
    }
}
