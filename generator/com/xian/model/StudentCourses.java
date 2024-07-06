package com.xian.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 鲜青松
 * @since 2024-07-04
 */
@Getter
@Setter
@TableName("student_courses")
@ApiModel(value = "StudentCourses对象", description = "")
public class StudentCourses implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("注册ID")
    @TableId(value = "registration_id", type = IdType.AUTO)
    private Integer registrationId;

    @ApiModelProperty("学生ID")
    @TableField("student_id")
    private Integer studentId;

    @ApiModelProperty("课程ID")
    @TableField("course_id")
    private Integer courseId;

    @ApiModelProperty("注册日期")
    @TableField("registered_at")
    private LocalDateTime registeredAt;

    @ApiModelProperty("学习进度")
    @TableField("progress")
    private Integer progress;
}
