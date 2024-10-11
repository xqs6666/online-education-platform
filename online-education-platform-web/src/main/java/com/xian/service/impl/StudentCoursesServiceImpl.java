package com.xian.service.impl;

import com.xian.cotext.UserContext;
import com.xian.mapper.CoursesMapper;
import com.xian.mapper.UsersMapper;
import com.xian.model.Courses;
import com.xian.model.StudentCourses;
import com.xian.mapper.StudentCoursesMapper;
import com.xian.model.dto.StudentCourseDTO;
import com.xian.model.vo.StudentCoursesVo;
import com.xian.service.IStudentCoursesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class StudentCoursesServiceImpl  implements IStudentCoursesService {
    @Autowired
    private StudentCoursesMapper studentCoursesMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private CoursesMapper coursesMapper;

    //关联学生与课程
    @Override
    public void addStudentCourse(Integer courseId) {
        //获取用户id判断是否为学生
        Map<String, Object> user = UserContext.getUser();
        Integer userId = (Integer) user.get("userId");
        String userType = (String) user.get("userType");
        if (!"student".equals(userType)){
            throw new RuntimeException("用户不是学生 不能添加课程");
        }
        //不能重复加入课程
        List<StudentCourses> studentCourses = studentCoursesMapper.getStudentCourses(userId);
        for (StudentCourses studentCourse : studentCourses) {
            if (studentCourse.getCourseId().equals(courseId)){
                throw new RuntimeException("该课程已加入");
            }
        }

        studentCoursesMapper.addStudentCourse(courseId, userId);
    }

    //获取学生课程
    @Override
    public List<StudentCoursesVo> getStudentCourses() {
        //获取用户id判断是否为学生
        Map<String, Object> user = UserContext.getUser();
        Integer userId = (Integer) user.get("userId");
        String userType = (String) user.get("userType");
        log.info("获取学生课程controller 用户id:{}",userId);
        if (!"student".equals(userType)){
            throw new RuntimeException("用户不是学生");
        }
        List<StudentCourses> studentCourses =studentCoursesMapper.getStudentCourses(userId);

        List<StudentCoursesVo> studentCoursesVoList = new ArrayList<>();

        for (StudentCourses studentCourse : studentCourses) {
            StudentCoursesVo studentCoursesVo = new StudentCoursesVo();
            BeanUtils.copyProperties(studentCourse,studentCoursesVo);
            Courses course = coursesMapper.getById(Long.valueOf(studentCourse.getCourseId()));
            studentCoursesVo.setCourses(course);
            studentCoursesVoList.add(studentCoursesVo);
        }
        return studentCoursesVoList;
    }

    //删除学生课程
    @Override
    public void deleteStudentCourse(Integer courseId) {
        //获取用户id判断是否为学生
        Map<String, Object> user = UserContext.getUser();
        Integer userId = (Integer) user.get("userId");
        String userType = (String) user.get("userType");
        if (!"student".equals(userType)){
            throw new RuntimeException("用户不是学生");
        }
        studentCoursesMapper.deleteStudentCourse(courseId,userId);
    }
}
