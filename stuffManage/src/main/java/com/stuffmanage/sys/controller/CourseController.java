package com.stuffmanage.sys.controller;

import com.alibaba.fastjson.JSON;
import com.stuffmanage.common.vo.Result;
import com.stuffmanage.sys.entity.ChooseCourse;
import com.stuffmanage.sys.service.impl.CourseServiceImpl;
import com.stuffmanage.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping("/showThis")
    @ApiOperation("获取可选课程列表")
    @RequiresPermissions("course:choose")
    public String showThis(Integer userId,String pageNum,String pageSize){
        Integer first= StringUtil.changeString(pageNum);
        Integer second= StringUtil.changeString(pageSize);
        Map<String,Object> map=courseService.getThisCourse(userId,first,second);
        System.out.println(JSON.toJSONString(map));
        if((long)map.get("total")==0){
            return JSON.toJSONString(Result.success(map,"无可选课程","200"));
        }else{
            return JSON.toJSONString(Result.success(map,"查询成功","200"));
        }
    }

    @PostMapping("/choose")
    @ApiOperation("选课")
    public String chooseCourse(@RequestBody ChooseCourse chooseCourse){
        if(courseService.isContradict(chooseCourse.getUserId(),chooseCourse.getCourseId())){
            return JSON.toJSONString(Result.error("500","时间冲突"));
        }
        int result = courseService.chooseCourse(chooseCourse.getUserId(), chooseCourse.getCourseId());
        if(result==1){
            return JSON.toJSONString(Result.success(null,"选课成功","200"));
        }
        else{
            return JSON.toJSONString(Result.error("500","服务器异常"));
        }
    }
}
