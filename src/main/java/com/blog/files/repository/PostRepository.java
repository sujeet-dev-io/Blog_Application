package com.blog.files.repository;

import com.blog.files.entites.Category;
import com.blog.files.entites.Post;
import com.blog.files.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, String> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitle(String title);

}
