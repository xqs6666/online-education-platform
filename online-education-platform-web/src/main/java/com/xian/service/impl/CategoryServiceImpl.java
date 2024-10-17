package com.xian.service.impl;

import com.xian.mapper.CategoryMapper;
import com.xian.model.Category;
import com.xian.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void addCategory(String categoryName) {
        categoryMapper.save(categoryName);
    }
}
