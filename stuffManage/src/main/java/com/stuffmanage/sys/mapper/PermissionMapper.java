package com.stuffmanage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stuffmanage.sys.entity.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("select permission_code " +
            "from user natural join user_role natural join role_permission natural join permission " +
            "where username=#{username} and father_id!=0")
    Set<String> selectPermissionsByUsername(String username);

    @Select("SELECT permission_id, permission_code, permission_name, father_id, path, is_menu "+
            "from user natural join user_role natural join role natural join role_permission natural join permission "+
            "where username=#{username} and father_id=0 and is_menu=1")
    List<Permission> selectFatherPermissionsByUsername(String username);

    @Select("SELECT permission_id, permission_code, permission_name, father_id, path, is_menu "+
            "from permission where father_id=#{fatherId} and is_menu=1")
    List<Permission> selectSubPermissionsByFatherId(int fatherId);
}
