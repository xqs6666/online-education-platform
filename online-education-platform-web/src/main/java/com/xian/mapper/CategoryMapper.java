package com.xian.mapper;

import com.xian.model.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> list();

    Category getByCategoryId(Integer categoryId);

    void save(String categoryName);
}
