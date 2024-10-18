package com.blog.files.service.Impl;

import com.blog.files.entites.Category;
import com.blog.files.exception.NotFoundException;
import com.blog.files.payloads.CategoryDto;
import com.blog.files.repository.CategoryReposistory;
import com.blog.files.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CategoryImpl implements CategoryService {
    @Autowired
    private CategoryReposistory categoryReposistory;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto saveCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        String categoryId = UUID.randomUUID().toString();
        category.setCategoryId(categoryId);
        category.setCreateDate(LocalDateTime.now());
        Category savedCategory = categoryReposistory.save(category);
        log.info("Category details saved successfully... {}", categoryDto);
        return modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategoryById(String categoryId) {
        Category category = categoryReposistory.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category ID not found"));

        if (Boolean.TRUE.equals(category.getDeleted())) {
            throw new NotFoundException("This category Id is already soft deleted");
        }

        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        log.info("Category details retrieved successfully... {}", categoryDto);
        return categoryDto;
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        List<Category> categories = categoryReposistory.findAll();

        if (!CollectionUtils.isEmpty(categories)) {
            categories.forEach(category -> {
                CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
                categoryDtoList.add(categoryDto);
            });
        }
        log.info("All categories fetched from the database: {}", categoryDtoList);
        return categoryDtoList;
    }

    @Override
    public Page<CategoryDto> getCategoriesByPaginationAndSorting(int offset, int pageSize, String field, String order) {
        PageRequest pageRequest = PageRequest.of(offset, pageSize)
                .withSort(Sort.by(Sort.Direction.valueOf(order), field));
        Page<Category> categories = categoryReposistory.findAll(pageRequest);
        List<CategoryDto> categoryDtoList = categories.stream()
                .map(e -> modelMapper.map(e, CategoryDto.class))
                .toList();

        log.info("Fetched {} categories from page {} with page size {} sorted by {} in {} order",
                categories.getTotalElements(), offset, pageSize, field, order);
        return new PageImpl<>(categoryDtoList, pageRequest, categories.getTotalElements());
    }

    @Override
    public CategoryDto updateCategoryById(String categoryId, CategoryDto categoryDto) {
        Category category = categoryReposistory.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category ID not found"));
        categoryDto.setCategoryId(categoryId);
        modelMapper.map(categoryDto, category);
        Category updatedCategory = categoryReposistory.save(category);
        log.info("Category details updated successfully... {}", categoryDto);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public String SoftDeleteCategoryById(String categoryId) {
        Category category = categoryReposistory.findByCategoryIdAndDeletedFalse(categoryId)
                .orElseThrow(() -> new NotFoundException("Category ID not found"));
        category.setDeleted(true);
        categoryReposistory.save(category);
        log.info("Category details deleted successfully... {}", category);
        return "Category details deleted successfully...";
    }

    @Override
    public String HardDeleteCategoryById(String categoryId) {
        Category category = categoryReposistory.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("Category ID not found"));
        categoryReposistory.delete(category);
        log.info("Category details hard deleted successfully... {}", category);
        return "Category details hard deleted successfully...";
    }



}
