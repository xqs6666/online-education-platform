package com.xian.mapper;

import com.xian.model.Courses;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
public interface CoursesMapper  {

    List<Courses> list();

    Courses getById(Long courseId);

    void save(Courses course);


    void updateById(Courses course);

    void removeById(Long courseId);

    List<Courses> getByCategoryId(Integer categoryId);
}
