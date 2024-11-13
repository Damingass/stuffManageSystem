package com.stuffmanage.sys.service.impl;

import com.stuffmanage.sys.entity.Role;
import com.stuffmanage.sys.mapper.RoleMapper;
import com.stuffmanage.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Set<String> getRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }
}
