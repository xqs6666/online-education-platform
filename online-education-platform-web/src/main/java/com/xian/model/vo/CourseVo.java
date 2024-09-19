package com.xian.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xian.model.Courses;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class CourseVo implements Serializable {
    /**
     * 课程分类主键名称
     */
    @ApiModelProperty("课程分类id")
    private Integer categoryId;
    /**
     * 课程名称
     */
    @ApiModelProperty("课程分类名称")
    private String categoryName;

    @ApiModelProperty("课程分类名称下课程")
    private List<Courses> coursesList;

}
