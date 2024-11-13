package com.stuffmanage.shiro;

import com.stuffmanage.sys.entity.User;
import com.stuffmanage.sys.service.IUserService;
import com.stuffmanage.token.JwtToken;
import com.stuffmanage.util.StringUtil;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyCredentialMatcher extends SimpleCredentialsMatcher {

    @Autowired
    private IUserService userService;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        JwtToken jwtToken = (JwtToken) token;
        if(jwtToken.getPassword()==null){
            return true;
        }
        System.out.println("MyCredentialMatcher");
        String inPassword = new String(jwtToken.getPassword());
        System.out.println(inPassword);

        User user = (User)info.getPrincipals().getPrimaryPrincipal();
        System.out.println(user);

        String salt=user.getSalt();
        System.out.println(salt);

        String dbPassword = (String)info.getCredentials();
        System.out.println(dbPassword);

        return this.equals(StringUtil.md5(inPassword+salt),dbPassword);
    }
}
