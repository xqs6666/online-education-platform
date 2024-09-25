package com.xian.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xian.model.Courses;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@TableName("chapters")
@ApiModel(value = "CourseChapterVo对象", description = "")
public class CourseChapterVo {
    private Courses courses;
    private List<ChapterVo> ChapterVoList;
}
