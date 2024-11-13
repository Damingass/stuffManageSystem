package com.stuffmanage.sys.service.impl;

import com.stuffmanage.sys.entity.Permission;
import com.stuffmanage.sys.entity.User;
import com.stuffmanage.sys.mapper.PermissionMapper;
import com.stuffmanage.sys.mapper.UserMapper;
import com.stuffmanage.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userMapper.getAllUsers();
    }

    @Override
    public List<Map<String,Object>> getMenuList(String username){
        List<Map<String,Object>> list = new ArrayList<>();
        List<Permission> permissionList=permissionMapper.selectFatherPermissionsByUsername(username);
        for(Permission permission:permissionList){
            Map<String,Object> map = new HashMap<>();
            map.put("menu",permission);
            List<Permission> permissionList1=permissionMapper.selectSubPermissionsByFatherId(permission.getPermissionId());
            map.put("subMenu",permissionList1);
            list.add(map);
        }
        return list;
    }
}
