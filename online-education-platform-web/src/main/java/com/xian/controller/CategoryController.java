package com.xian.controller;

import com.xian.model.Category;
import com.xian.model.vo.CourseVo;
import com.xian.service.CategoryService;
import com.xian.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/online/category")
@Api(tags = "CategoryController")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    //新增课程分类
    @PostMapping
    @ApiOperation(value = "新增课程分类")
    public Result addCategory(String categoryName) {
        categoryService.addCategory(categoryName);
        return Result.success();
    }

}
