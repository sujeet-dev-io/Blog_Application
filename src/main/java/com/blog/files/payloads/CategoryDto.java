package com.blog.files.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String categoryId;

    @NotEmpty(message = "category description not must be null")
    private String categoryDescription;
    @NotBlank(message = "category title must not be null")
    private String categoryTitle;
    private LocalDateTime createDate;
    private Boolean deleted = Boolean.FALSE;}
