package com.stuffmanage.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.stuffmanage.sys.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Select("SELECT * from course where course_status=1 and not exists "+
            "(select * from choose_course where user_id=#{userId} and course_id=course.course_id)")
    List<Course> selectThisCourses(int userId);

    @Select("SELECT course.course_id, course_code, course_name, course_type, school_id, " +
            "teacher_id, course_status, course_term_id, course_term, course_time, " +
            "course_place, course_class, course_credit, usual_weight, end_weight " +
            "from course,choose_course,term " +
            "where course.course_id=choose_course.course_id " +
            "and course.course_term_id=term.term_id " +
            "and term_status=1 " +
            "and choose_status=0 " +
            "and course_status=1 and user_id=#{userId}")
    List<Course> selectChooseResult(Integer userId);
}
