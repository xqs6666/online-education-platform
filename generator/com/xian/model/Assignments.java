package com.xian.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("assignments")
@ApiModel(value = "Assignments对象", description = "")
public class Assignments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("作业ID")
    @TableId(value = "assignment_id", type = IdType.AUTO)
    private Integer assignmentId;

    @ApiModelProperty("课程ID")
    @TableField("course_id")
    private Integer courseId;

    @ApiModelProperty("作业描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("截止日期")
    @TableField("due_date")
    private LocalDate dueDate;
}
