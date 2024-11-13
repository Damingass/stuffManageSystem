package com.stuffmanage.realm;

import com.stuffmanage.sys.entity.Role;
import com.stuffmanage.sys.entity.User;
import com.stuffmanage.sys.service.IPermissionService;
import com.stuffmanage.sys.service.IRoleService;
import com.stuffmanage.sys.service.IUserRoleService;
import com.stuffmanage.sys.service.IUserService;
import com.stuffmanage.token.JwtToken;
import com.stuffmanage.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("Author");
//        System.out.println(principals.getPrimaryPrincipal());
//        System.out.println(principals.iterator().next());
        User user = (User) principals.getPrimaryPrincipal();
        String username = user.getUsername();
        //String username=(String)principals.iterator().next();
        Set<String> roles=roleService.getRolesByUsername(username);
        Set<String> permissions=permissionService.getPermissionsByUsername(username);
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken token = (JwtToken) authenticationToken;
        String jwt=(String)token.getPrincipal();
        Claims claims=JwtUtil.parseJWT(jwt);
        String username=claims.getId();

        User user=userService.findByUsername(username);//loginName字段
        System.out.println(user);
        System.out.println(token);
        if(user!=null){
            return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        }
        return null;
    }
}