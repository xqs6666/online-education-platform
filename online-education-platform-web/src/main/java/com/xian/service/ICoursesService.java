package com.xian.service;

import com.xian.model.Category;
import com.xian.model.Courses;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.vo.CourseAdminVo;
import com.xian.model.vo.CourseVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface ICoursesService  {

    List<CourseVo> list();

    Courses getById(Long courseId);

    void save(Courses course);

    void updateById(Courses course);

    void removeById(Long courseId);

    List<Category> getAllCategory();

    List<Courses> getHotCourses();

    List<CourseAdminVo> adminList();

}
