package com.xian.mapper;

import com.xian.model.StudentCourses;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xian.model.dto.StudentCourseDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface StudentCoursesMapper {

    void addStudentCourse(@Param("courseId") Integer courseId,@Param("studentId") Integer studentId);

    List<StudentCourses> getStudentCourses(Integer studentId);

    void deleteStudentCourse(@Param("courseId") Integer courseId,@Param("studentId") Integer userId);


    List<Integer> getHotCourses();
}
