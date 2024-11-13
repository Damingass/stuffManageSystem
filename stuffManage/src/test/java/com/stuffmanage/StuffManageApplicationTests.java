package com.stuffmanage;

import com.stuffmanage.sys.entity.User;
import com.stuffmanage.sys.mapper.CourseMapper;
import com.stuffmanage.sys.mapper.UserMapper;
import com.stuffmanage.sys.service.ICourseService;
import com.stuffmanage.sys.service.IPermissionService;
import com.stuffmanage.sys.service.IRoleService;
import com.stuffmanage.sys.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
class StuffManageApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ICourseService courseService;


    @Test
    void testCourseMapper() {
        //System.out.println(courseMapper.selectThisCourses((1)));
        //System.out.println(courseService.getThisCourse(1,1,1));
        System.out.println(courseMapper.selectChooseResult(1));
    }

    @Test
    void testRoleService(){
        System.out.println(roleService.getRolesByUsername("20201001"));
    }
    @Test
    void testPermissionService(){
        System.out.println(permissionService.getPermissionsByUsername("20201001"));
    }
    @Test
    void testGetPermission(){
        List<Map<String,Object>> list=userService.getMenuList("20201001");
        System.out.println(list.get(1).get("subMenu"));
    }
    @Test
    void testMapper() {
        List<User> users=userMapper.selectList(null);
        users.forEach(System.out::println);
    }

}
