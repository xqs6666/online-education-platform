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
@TableName("student_assignments")
@ApiModel(value = "StudentAssignments对象", description = "")
public class StudentAssignments implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("提交ID")
    @TableId(value = "submission_id", type = IdType.AUTO)
    private Integer submissionId;

    @ApiModelProperty("学生ID")
    @TableField("student_id")
    private Integer studentId;

    @ApiModelProperty("作业ID")
    @TableField("assignment_id")
    private Integer assignmentId;

    @ApiModelProperty("提交链接")
    @TableField("submission_url")
    private String submissionUrl;

    @ApiModelProperty("提交日期")
    @TableField("submitted_at")
    private LocalDateTime submittedAt;

    @ApiModelProperty("评分")
    @TableField("grade")
    private Double grade;

    @ApiModelProperty("评语")
    @TableField("feedback")
    private String feedback;
}
