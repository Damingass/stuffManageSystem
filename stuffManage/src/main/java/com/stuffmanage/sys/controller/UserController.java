package com.stuffmanage.sys.controller;

import com.alibaba.fastjson.JSON;
import com.stuffmanage.common.vo.Result;
import com.stuffmanage.sys.entity.User;
import com.stuffmanage.sys.service.IUserService;
import com.stuffmanage.token.JwtToken;
import com.stuffmanage.util.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/all")
    public Result<List<User>> getAllUser() {
        List<User> list = userService.getAllUser();
        //System.out.println(list);
        return Result.success(list);
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestParam String loginName, @RequestParam String password) {
        Subject subject= SecurityUtils.getSubject();
        String jwt= JwtUtil.createJWT(loginName,"back","user",1000*60*30);
        JwtToken jwtToken = new JwtToken(jwt,password);
        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            System.out.println("账号不存在");
            return Result.error("401","账号不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码不存在");
            return Result.error("402","密码错误");
        }
        User backUser=userService.findByUsername(loginName);
        backUser.setPassword(null);
        backUser.setSalt(null);
        Map<String,Object> map=new HashMap<>();
        map.put("user",backUser);
        map.put("token",jwtToken);
        return Result.success(map,"登录成功","200");
    }

    @GetMapping("/getMenuList")
    @ApiOperation("获取主界面侧边栏")
    public String getMenulist(String username) {
        System.out.println(username);
        if(StringUtils.isEmpty(username)){
            return JSON.toJSONString(Result.error("401","未登录"));
        }
        List<Map<String, Object>> menuList = userService.getMenuList((username));
        System.out.println(JSON.toJSONString(menuList));
        return JSON.toJSONString(Result.success(menuList,"","200 "));
    }

    @RequiresPermissions("course:choose")
    @GetMapping("/test")
    public Result<String> test() {
        return Result.success("test");
    }

    //@CrossOrigin
    @GetMapping("/test2")
    public Result<String> test2() {
        return Result.success("test2");
    }

}
