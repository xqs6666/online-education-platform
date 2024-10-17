package com.xian.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "UpdateChaptersDTO", description = "")
public class UpdateChaptersDTO {

    @ApiModelProperty("章节ID")
    private Integer chapterId;

    @ApiModelProperty("章节标题")
    private String title;

    @ApiModelProperty("章节描述")
    private String description;

    @ApiModelProperty("视频链接")
    private String videoUrl;

    @ApiModelProperty("排序")
    @TableField("sort_order")
    private Integer sortOrder;
}
