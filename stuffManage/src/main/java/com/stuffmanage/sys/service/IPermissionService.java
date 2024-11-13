package com.stuffmanage.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stuffmanage.sys.entity.Permission;

import java.util.Set;

public interface IPermissionService extends IService<Permission> {
    Set<String> getPermissionsByUsername(String username);
}
