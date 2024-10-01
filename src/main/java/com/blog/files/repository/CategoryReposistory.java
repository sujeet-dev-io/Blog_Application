package com.blog.files.repository;

import com.blog.files.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryReposistory extends JpaRepository<Category, String> {

    Optional<Category> findByCategoryIdAndDeletedFalse(String categoryId);

}
