package com.xian.controller;

import com.xian.model.Category;
import com.xian.model.Courses;
import com.xian.model.vo.CourseVo;
import com.xian.service.ICoursesService;
import com.xian.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@RestController
@RequestMapping("/online/courses")
@Api(tags = "CoursesController")
public class CoursesController {
    @Autowired
    private ICoursesService courseService;

    // 获取所有课程
    @GetMapping
    @ApiOperation(value = "获取所有课程")
    public Result<List<CourseVo>> getAllCourses() {
        return Result.success(courseService.list());
    }

    // 获取单个课程
    @GetMapping("/{courseId}")
    @ApiOperation(value = "获取单个课程")
    public Result<Courses> getCourse(@PathVariable Long courseId) {
        return Result.success(courseService.getById(courseId));
    }

    // 新增课程
    @PostMapping
    @ApiOperation(value = "新增课程")
    public Result addCourse(@RequestBody Courses course) {
        courseService.save(course);
        return Result.success();
    }

    // 更新课程
    @PutMapping("/{courseId}")
    @ApiOperation(value = "更新课程")
    public Result updateCourse(@PathVariable(name = "courseId") Integer courseId, @RequestBody Courses course) {
        course.setCourseId(courseId);
        courseService.updateById(course);
        return Result.success();
    }

    // 删除课程
    @DeleteMapping("/{courseId}")
    @ApiOperation(value = "删除课程")
    public Result deleteCourse(@PathVariable Long courseId) {
        courseService.removeById(courseId);
        return Result.success();
    }

    //获取所有课程分类
    @GetMapping("/getAllCategory")
    @ApiOperation(value = "获取所有课程分类")
    public Result getAllCategory(){
        List<Category> list =courseService.getAllCategory();
        return Result.success(list);
    }
}
