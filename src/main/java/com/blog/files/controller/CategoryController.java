package com.blog.files.controller;

import com.blog.files.payloads.CategoryDto;
import com.blog.files.response.BaseResponse;
import com.blog.files.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary = "Create a new category", description = "Endpoint to create a new category")
    public ResponseEntity<BaseResponse<Object>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Category Added")
                .data(categoryService.saveCategory(categoryDto))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{categoryId}")
    @Operation(summary = "Get category by ID", description = "Endpoint to fetch a category by ID")
    public ResponseEntity<BaseResponse<Object>> getCategoryById(@PathVariable String categoryId) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Category Fetched")
                .data(categoryService.getCategoryById(categoryId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    @Operation(summary = "Get all categories", description = "Endpoint to fetch all categories")
    public ResponseEntity<BaseResponse<Object>> getAllCategories() {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("All Categories Fetched")
                .data(categoryService.getAllCategories())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getPagination")
    @Operation(summary = "Get all categories", description = "Endpoint to fetch all categories")
    public ResponseEntity<BaseResponse<Object>> getCategoriesByPaginationAndSorting(@RequestParam int offset, @RequestParam int pageSize, @RequestParam String field, @RequestParam String order) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("All Categories Fetched")
                .data(categoryService.getCategoriesByPaginationAndSorting(offset, pageSize, field, order))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update")
    @Operation(summary = "Update category", description = "Endpoint to update a category by ID")
    public ResponseEntity<BaseResponse<Object>> updateCategoryById(@RequestParam String categoryId, @RequestBody CategoryDto categoryDto) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Category Updated")
                .data(categoryService.updateCategoryById(categoryId, categoryDto))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryId}")
    @Operation(summary = "Delete category by ID", description = "Endpoint to delete a category by ID")
    public ResponseEntity<BaseResponse<Object>> deleteCategoryById(@PathVariable String categoryId) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Category Deleted")
                .data(categoryService.SoftDeleteCategoryById(categoryId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/hard-delete/{categoryId}")
    @Operation(summary = "Hard delete category by ID", description = "Endpoint to hard delete a category by ID")
    public ResponseEntity<BaseResponse<Object>> hardDeleteCategoryById(@PathVariable String categoryId) {
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Category Hard Deleted")
                .data(categoryService.HardDeleteCategoryById(categoryId))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
