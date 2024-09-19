package com.xian.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "VideosVo对象", description = "")
public class VideosVo {
    private String videoId;
    private String playUrl;
}
