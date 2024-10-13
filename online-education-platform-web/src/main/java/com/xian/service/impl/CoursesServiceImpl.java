package com.xian.service.impl;

import com.xian.cotext.UserContext;
import com.xian.mapper.CategoryMapper;
import com.xian.mapper.StudentCoursesMapper;
import com.xian.mapper.UsersMapper;
import com.xian.model.Category;
import com.xian.model.Courses;
import com.xian.mapper.CoursesMapper;
import com.xian.model.Users;
import com.xian.model.dto.StudentCourseDTO;
import com.xian.model.vo.CourseAdminVo;
import com.xian.model.vo.CourseVo;
import com.xian.service.ICoursesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@Service
@Slf4j
public class CoursesServiceImpl  implements ICoursesService{
   @Autowired
    private CoursesMapper coursesMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private StudentCoursesMapper studentCoursesMapper;

    public List<CourseVo> list() {
        ArrayList<CourseVo> list = new ArrayList<>();

        //先获取所有分类
        List<Category> categoryCourses = categoryMapper.list();

        if (categoryCourses != null){
            for (Category category : categoryCourses) {
                //获取分类名称
                String categoryName = category.getCategoryName();
                //
                Integer categoryId = category.getCategoryId();
                //获取分类id
                List<Courses> courses=coursesMapper.getByCategoryId(categoryId);

                CourseVo courseVo = new CourseVo();
                courseVo.setCategoryId(category.getCategoryId());
                courseVo.setCategoryName(categoryName);
                courseVo.setCoursesList(courses);
                list.add(courseVo);
            }
        }

        return list;
    }


    public Courses getById(Long courseId) {
        return coursesMapper.getById(courseId);
    }


    public void save(Courses course) {
        //获取用户id
        Map<String, Object> user = UserContext.getUser();
        Integer userId = (Integer) user.get("userId");

        //查询是老师还是学生
        Users users = usersMapper.getById(userId);
        if (!users.getUserType().equals("teacher")) {
            throw new RuntimeException("只有老师才能发布课程");
        }
        //TeacherId为空
        if (course.getTeacherId()==null){
            course.setTeacherId(userId);
        }
        //TeacherId不为空
        if (!course.getTeacherId().equals(userId)){
            throw new RuntimeException("只能发布自己发布的课程");
        }

        //CategoryId不为空
        if (course.getCategoryId()==null){
            throw new RuntimeException("请选择课程分类");
        }

        coursesMapper.save(course);
    }


    public void updateById(Courses course) {
        coursesMapper.updateById(course);
    }


    public void removeById(Long courseId) {
        coursesMapper.removeById(courseId);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.list();
    }

    //获取热门课程
    @Override
    public List<Courses> getHotCourses() {
        //获取热门课程
        List<Integer> hotCourseId=studentCoursesMapper.getHotCourses();
        List<Courses> coursesList = new ArrayList<>();
        for (Integer courseId : hotCourseId){
            Courses courses = coursesMapper.getById(Long.valueOf(courseId));
            coursesList.add(courses);
        }
        return coursesList;
    }

    @Override
    public List<CourseAdminVo> adminList() {
        ArrayList<CourseAdminVo> list = new ArrayList<>();

        //先获取所有分类
        List<Category> categoryCourses = categoryMapper.list();

        if (categoryCourses != null){
            for (Category category : categoryCourses) {
                //获取分类名称
                String categoryName = category.getCategoryName();
                //
                Integer categoryId = category.getCategoryId();
                //获取分类id
                List<Courses> courses=coursesMapper.getByCategoryId(categoryId);

                for (Courses course : courses){
                    CourseAdminVo courseVo = new CourseAdminVo();
                    BeanUtils.copyProperties(course,courseVo);
                    courseVo.setCategoryId(category.getCategoryId());
                    courseVo.setCategoryName(categoryName);
                    Users user = usersMapper.getById(course.getTeacherId());
                    courseVo.setUsername(user.getUsername());
                    courseVo.setTeacherAvatar(user.getAvatar());
                    list.add(courseVo);
                }

            }
        }

        return list;
    }
}
