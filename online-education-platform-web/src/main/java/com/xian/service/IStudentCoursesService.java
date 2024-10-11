package com.xian.service;

import com.xian.model.StudentCourses;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.dto.StudentCourseDTO;
import com.xian.model.vo.StudentCoursesVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface IStudentCoursesService {

    // 关联学生与课程
    void addStudentCourse(Integer courseId);

    List<StudentCoursesVo> getStudentCourses();

    void deleteStudentCourse(Integer courseId);
}
