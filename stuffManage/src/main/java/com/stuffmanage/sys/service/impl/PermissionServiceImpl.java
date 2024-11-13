package com.stuffmanage.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.stuffmanage.sys.entity.Permission;
import com.stuffmanage.sys.mapper.PermissionMapper;
import com.stuffmanage.sys.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Set;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> getPermissionsByUsername(String username){
        return permissionMapper.selectPermissionsByUsername(username);
    }
}
