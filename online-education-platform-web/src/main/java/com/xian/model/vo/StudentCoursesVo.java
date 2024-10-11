package com.xian.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xian.model.Courses;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 鲜青松
 * @since 2024-10-09
 */
@Getter
@Setter
@TableName("student_courses")
@ApiModel(value = "StudentCoursesVo对象", description = "")
public class StudentCoursesVo {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("注册ID")
    private Integer registrationId;

    @ApiModelProperty("学生ID")
    private Integer studentId;

    @ApiModelProperty("课程ID")
    private Integer courseId;

    @ApiModelProperty("课程")
    private Courses courses;

    @ApiModelProperty("注册日期")
    private LocalDateTime registeredAt;

    @ApiModelProperty("学习进度")
    private Integer progress;
}
