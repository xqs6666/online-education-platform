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
@TableName("chapter_videos")
@ApiModel(value = "ChapterVideos对象", description = "")
public class ChapterVideos implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("视频ID")
    @TableId(value = "video_id", type = IdType.AUTO)
    private Integer videoId;

    @ApiModelProperty("章节ID")
    @TableField("chapter_id")
    private Integer chapterId;

    @ApiModelProperty("视频链接")
    @TableField("video_url")
    private String videoUrl;

    @ApiModelProperty("上传日期")
    @TableField("uploaded_at")
    private LocalDateTime uploadedAt;
}
