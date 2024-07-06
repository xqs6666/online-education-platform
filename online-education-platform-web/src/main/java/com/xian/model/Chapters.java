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
@TableName("chapters")
@ApiModel(value = "Chapters对象", description = "")
public class Chapters implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("章节ID")
    @TableId(value = "chapter_id", type = IdType.AUTO)
    private Integer chapterId;

    @ApiModelProperty("课程ID")
    @TableField("course_id")
    private Integer courseId;

    @ApiModelProperty("章节标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("章节描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("创建日期")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
