package com.xian.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "StudentCourseDTO对象", description = "")
public class StudentCourseDTO {
    @ApiModelProperty("学生ID")
    private Integer studentId;
    @ApiModelProperty("课程ID")
    private Integer courseId;
}
