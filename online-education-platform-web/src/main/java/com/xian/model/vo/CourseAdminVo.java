package com.xian.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("courses")
@ApiModel(value = "Courses对象", description = "")
public class CourseAdminVo {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    @ApiModelProperty("课程分类id")
    @TableField("categoryId")
    private Integer categoryId;

    @ApiModelProperty("教师ID")
    @TableField("teacher_id")
    private Integer teacherId;

    @ApiModelProperty("课程图片")
    private String image;

    @ApiModelProperty("老师图片")
    private String teacherAvatar;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程描述")
    private String description;

    @ApiModelProperty("课程老师")
    private String username;

    @ApiModelProperty("课程价格")
    private BigDecimal price;

    @ApiModelProperty("分类名")
    private String categoryName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
}
