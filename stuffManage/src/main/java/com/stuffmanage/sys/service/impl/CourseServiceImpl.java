package com.stuffmanage.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stuffmanage.sys.entity.Course;
import com.stuffmanage.sys.entity.School;
import com.stuffmanage.sys.entity.User;
import com.stuffmanage.sys.mapper.ChooseCourseMapper;
import com.stuffmanage.sys.mapper.CourseMapper;
import com.stuffmanage.sys.mapper.SchoolMapper;
import com.stuffmanage.sys.mapper.UserMapper;
import com.stuffmanage.sys.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService{
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private User user;
    @Autowired
    private ChooseCourseMapper chooseCourseMapper;


    @Override
    public Map<String, Object> getThisCourse(Integer userId, Integer pageNum, Integer pageSize) {
        Integer first= pageNum == null?1:pageNum;
        Integer second= pageSize == null?5:pageSize;
        PageHelper.startPage(first,second);
        List<Course> courses=courseMapper.selectThisCourses(userId);
        PageInfo pageInfo=new PageInfo(courses);
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        List<Map<String,Object>> mapList=new ArrayList<>();
        for(Course course:courses){
            Map<String,Object> map1=new HashMap<>();
            School school=schoolMapper.selectById(course.getSchoolId());
            User teacher = userMapper.selectById(course.getTeacherId());
            map1.put("course",course);
            map1.put("school",school);
            map1.put("teacher",teacher);
            mapList.add(map1);
        }
        map.put("list",mapList);
        return map;
    }

    @Override
    public boolean isContradict(Integer userId, Integer courseId) {
        List<Course> courses=courseMapper.selectChooseResult(userId);
        Course course=courseMapper.selectById(courseId);
        for(Course course1:courses){
            if(course.getCourseTime().equals(course1.getCourseTime())){
                System.out.println(course.getCourseId());
                System.out.println(course.getCourseTime());
                System.out.println(course1.getCourseId());
                System.out.println(course1.getCourseTime());
                return true;
            }
        }
        return false;
    }

    @Override
    public int chooseCourse(Integer userId, Integer courseId) {
        return chooseCourseMapper.insertChooseCourse(userId,courseId);
    }


}
