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
@TableName("forums")
@ApiModel(value = "Forums对象", description = "")
public class Forums implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("讨论主题ID")
    @TableId(value = "forum_id", type = IdType.AUTO)
    private Integer forumId;

    @ApiModelProperty("课程ID")
    @TableField("course_id")
    private Integer courseId;

    @ApiModelProperty("用户ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("讨论标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("讨论内容")
    @TableField("content")
    private String content;

    @ApiModelProperty("创建日期")
    @TableField("created_at")
    private LocalDateTime createdAt;
}
