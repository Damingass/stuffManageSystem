package com.stuffmanage.sys.mapper;

import com.stuffmanage.sys.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.Set;
/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT role_name from user natural join user_role natural join role where username=#{username}")
    Set<String> selectRolesByUsername(String username);
}