package com.stuffmanage.sys.service;

import com.stuffmanage.sys.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
public interface IRoleService extends IService<Role> {
    Set<String> getRolesByUsername(String username);
}
