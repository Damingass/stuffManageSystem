package com.stuffmanage.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.stuffmanage.sys.entity.Course;

import java.util.Map;

public interface ICourseService extends IService<Course> {
    Map<String,Object> getThisCourse(Integer userId, Integer pageNum, Integer pageSize);

    boolean isContradict(Integer userId, Integer courseId);

    int chooseCourse(Integer userId, Integer courseId);
}
