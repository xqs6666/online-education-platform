package com.xian.model.vo;

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
@ApiModel(value = "ChapterVo对象", description = "")
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("章节ID")
    private Integer chapterId;

    @ApiModelProperty("章节标题")
    private String title;

    @ApiModelProperty("视频ID")
    private String videoId;

    @ApiModelProperty("章节描述")
    private String description;

    @ApiModelProperty("播放地址")
    private String playUrl;


    @ApiModelProperty("创建日期")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
}
