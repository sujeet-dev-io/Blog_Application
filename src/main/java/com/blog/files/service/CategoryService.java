package com.blog.files.service;

import com.blog.files.payloads.CategoryDto;
import org.springframework.data.domain.Page;


import java.util.List;

public interface CategoryService {

    CategoryDto saveCategory(CategoryDto categoryDto);

    CategoryDto getCategoryById(String categoryId);

    List<CategoryDto> getAllCategories();

    Page<CategoryDto> getCategoriesByPaginationAndSorting(int offset, int pageSize, String field, String order);

    CategoryDto updateCategoryById(String categoryId, CategoryDto categoryDto);

    String SoftDeleteCategoryById(String categoryId);

    String HardDeleteCategoryById(String categoryId);

}
