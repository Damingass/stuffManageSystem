package com.stuffmanage.sys.mapper;

import org.apache.ibatis.annotations.Insert;

public interface ChooseCourseMapper {
    @Insert("insert into choose_course(user_id,course_id) " +
            "values (#{userId},#{courseId})")
    int insertChooseCourse(Integer userId, Integer courseId);
}
