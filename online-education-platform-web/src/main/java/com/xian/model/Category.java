package com.xian.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
* 课程分类表
* @TableName category
*/
@Getter
@Setter
@TableName("category")
@ApiModel(value = "Category对象", description = "")
public class Category implements Serializable {

    /**
    * 课程分类主键名称
    */
    @ApiModelProperty("课程分类主键名称")
    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;
    /**
    * 课程名称
    */
    @ApiModelProperty("课程名称")
    @TableId(value = "category_name")
    private String categoryName;

}
