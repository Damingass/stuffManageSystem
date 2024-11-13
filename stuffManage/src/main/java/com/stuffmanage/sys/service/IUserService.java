package com.stuffmanage.sys.service;

import com.stuffmanage.sys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
public interface IUserService extends IService<User> {
    User findByUsername(String username);

    List<User> getAllUser();

    List<Map<String,Object>> getMenuList(String username);
}
