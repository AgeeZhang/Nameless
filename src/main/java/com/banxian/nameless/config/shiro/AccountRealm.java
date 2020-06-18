package com.banxian.nameless.config.shiro;

import com.banxian.nameless.common.utils.JwtUtils;
import com.banxian.nameless.entity.Menu;
import com.banxian.nameless.entity.Role;
import com.banxian.nameless.entity.User;
import com.banxian.nameless.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //这部分代码用来做接口权限控制(接口上配合 @RequiresPermissions("user:info") 权限控制 使用)
        /*AccountProfile accountProfile = (AccountProfile) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.getById(accountProfile.getId());
        for (Role role : user.getRoleList()) {
            authorizationInfo.addRole(role.getName());
            for (Menu p : role.getMenuList()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }*/
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwt = (JwtToken) authenticationToken;
        log.info("jwt----------------->{}", jwt);
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        User user = userService.getById(Long.parseLong(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定！");
        }
        AccountProfile accountProfile = new AccountProfile();
        BeanUtils.copyProperties(user, accountProfile);
        log.info("profile----------------->{}", accountProfile.toString());
        return new SimpleAuthenticationInfo(accountProfile, jwt.getCredentials(), getName());
    }
}
