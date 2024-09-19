package com.xian.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
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
@TableName("courses")
@ApiModel(value = "Courses对象", description = "")
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableId(value = "course_id", type = IdType.AUTO)
    private Integer courseId;

    @ApiModelProperty("课程图片")
    @TableField("image")
    private String image;

    @ApiModelProperty("课程标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("课程描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("课程分类id")
    @TableField("categoryId")
    private Integer categoryId;

    @ApiModelProperty("课程价格")
    @TableField("price")
    private BigDecimal price;

    @ApiModelProperty("教师ID")
    @TableField("teacher_id")
    private Integer teacherId;

    @ApiModelProperty("创建日期")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
