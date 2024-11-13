package com.stuffmanage.sys.mapper;

import com.stuffmanage.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;
/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author damingass
 * @since 2024-08-20
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user")
    List<User> getAllUsers();

    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUsername(String username);
}
