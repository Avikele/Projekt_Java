package com.projekt.ems.Services;

import com.projekt.ems.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAllCategories();
    CategoryDto findCategoryById(Long id);
    CategoryDto saveCategory(CategoryDto category);
    CategoryDto updateCategory(Long id, CategoryDto category);
    void deleteCategory(Long id);
}
