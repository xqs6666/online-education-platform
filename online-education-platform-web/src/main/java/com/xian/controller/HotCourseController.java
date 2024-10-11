package com.xian.controller;

import com.xian.model.Courses;
import com.xian.service.ICoursesService;
import com.xian.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 鲜青松
 * @since 2024-010-09
 */
@RestController
@RequestMapping("/online/hotCourses")
@Api(tags = "HotCourseController")
public class HotCourseController {
    @Autowired
    private ICoursesService courseService;
    //获取热门课程
    @GetMapping("/hots")
    @ApiOperation(value = "获取所有热门课程")
    public Result<List<Courses>> getHotCourses(){
        List<Courses> courses=courseService.getHotCourses();
        return Result.success(courses);
    }
}
