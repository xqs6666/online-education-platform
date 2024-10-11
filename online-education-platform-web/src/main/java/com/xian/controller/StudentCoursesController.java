package com.xian.controller;

import com.xian.model.StudentCourses;
import com.xian.model.dto.StudentCourseDTO;
import com.xian.model.vo.StudentCoursesVo;
import com.xian.service.IStudentCoursesService;
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
@Api(tags = "学生与课程")
@RestController
@RequestMapping("/online/studentCourses")
public class StudentCoursesController {
    @Autowired
    private IStudentCoursesService studentCoursesService;

    @ApiOperation(value = "关联学生与课程")
    @PostMapping("/addStudentCourses/{courseId}")
    public Result addStudentCourse(@PathVariable(name = "courseId") Integer courseId){
        studentCoursesService.addStudentCourse(courseId);
        return Result.success();
    }

    @ApiOperation(value = "获取学生已选课程")
    @GetMapping("/getStudentCourses")
    public Result<List<StudentCoursesVo>> getStudentCourses(){
        return Result.success(studentCoursesService.getStudentCourses());
    }

    @ApiOperation(value = "取消关联学生与课程")
    @PostMapping("/deleteStudentCourses/{courseId}")
    public Result deleteStudentCourse(@PathVariable(name = "courseId") Integer courseId){
        studentCoursesService.deleteStudentCourse(courseId);
        return Result.success();
    }


}
