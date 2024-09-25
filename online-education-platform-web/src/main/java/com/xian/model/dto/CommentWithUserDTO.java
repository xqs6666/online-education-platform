package com.xian.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "CommentDTO对象", description = "")
public class CommentWithUserDTO {
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

    @ApiModelProperty("父评论ID")
    private Integer parentId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("创建日期")
    private LocalDateTime createdAt;

    private List<CommentWithUserDTO> children;
}
