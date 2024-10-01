package com.blog.files.payloads;

import com.blog.files.entites.Category;
import com.blog.files.entites.Comment;
import com.blog.files.entites.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private String postId;

    private String title;
    private String content;
    private String imageName;

    private Category category; // Use Category instead of CategoryDto
    private User user;

    private Set<Comment> comments = new HashSet<>();


}
