package com.blog.files.repository;

import com.blog.files.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, String> {

}
