package com.stuffmanage.sys.service.impl;

import com.stuffmanage.sys.entity.User;
import com.stuffmanage.sys.entity.UserRole;
import com.stuffmanage.sys.mapper.UserMapper;
import com.stuffmanage.sys.mapper.UserRoleMapper;
import com.stuffmanage.sys.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
    @Autowired
    private UserMapper userMapper;

}
