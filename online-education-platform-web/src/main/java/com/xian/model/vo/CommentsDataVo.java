package com.xian.model.vo;




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
@ApiModel(value = "CommentsDataVo对象", description = "")
public class CommentsDataVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论ID")
    private Integer commentId;

    @ApiModelProperty("课程ID")
    private Integer courseId;

    @ApiModelProperty("用户ID")
    private Integer userId;
    @ApiModelProperty("用户昵称")
    private String username;
    @ApiModelProperty("用户头像")
    private String avatar;
    @ApiModelProperty("评论内容")
    private String content;
    @ApiModelProperty("课程名字")
    private String courseName;

    @ApiModelProperty("创建日期")
    private LocalDateTime createdAt;
}

