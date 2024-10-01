package com.blog.files.service;

import com.blog.files.entites.Post;
import com.blog.files.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, String userId, String categoryId);

    PostDto updatePost(String postId, PostDto postDto);

    void deletePost(String postId);

    List<PostDto> getAllPost();

    PostDto getPostById(String postId);

    List<PostDto> getPostsByCategory(String categoryId);

    List<PostDto> getPostsByUser(String userId);

    List<PostDto> searchPosts(String keyword);


}
