package com.blog.files.service.Impl;

import com.blog.files.entites.Category;
import com.blog.files.entites.Post;
import com.blog.files.entites.User;
import com.blog.files.exception.NotFoundException;
import com.blog.files.payloads.PostDto;
import com.blog.files.repository.CategoryReposistory;
import com.blog.files.repository.PostRepository;
import com.blog.files.repository.UserRepository;
import com.blog.files.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PostImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryReposistory categoryRepository;

    @Override
    public PostDto createPost(PostDto postDto, String userId, String categoryId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("user id not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException("category id not found"));

        Post post = modelMapper.map(postDto, Post.class);
        String postId = UUID.randomUUID().toString();
        post.setPostId(postId);
        post.setImageName("default.png");
        post.setDate(new Date());
        post.setUser(user);
        post.setCategory(category);

        Post savedPost = postRepository.save(post);
        PostDto savedPostDto = modelMapper.map(savedPost, PostDto.class);

        // Directly set User and Category
        savedPostDto.setUser(user);
        savedPostDto.setCategory(category);

        return savedPostDto;
    }

    @Override
    public PostDto updatePost(String postId, PostDto postDto) {
        Post existingPost = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post ID not found"));

        // Map only the fields that need to be updated
        modelMapper.map(postDto, existingPost);

        // Directly set fields, if not included in mapping
        existingPost.setTitle(postDto.getTitle());
        existingPost.setContent(postDto.getContent());
        existingPost.setImageName(postDto.getImageName());

        // Save the existingPost
        Post updatedPost = postRepository.save(existingPost);
        return modelMapper.map(updatedPost, PostDto.class);
    }


    @Override
    public void deletePost(String postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post ID not found"));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();
    }

    @Override
    public PostDto getPostById(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new NotFoundException("post id not found"));
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new NotFoundException("category id not found"));
        List<Post> posts = postRepository.findByCategory(category);
        return posts.stream().map((post -> modelMapper.map(post, PostDto.class))).toList();
    }

    @Override
    public List<PostDto> getPostsByUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user id not found"));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream().map((post -> modelMapper.map(post, PostDto.class))).toList();
    }


    @Override
    public List<PostDto> searchPosts(String keyword) {
        List<Post> posts = postRepository.findByTitle(keyword);
        return posts.stream().map((post -> modelMapper.map(post, PostDto.class))).toList();
    }
}
