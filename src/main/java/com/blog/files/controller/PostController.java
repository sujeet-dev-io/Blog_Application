package com.blog.files.controller;

import com.blog.files.payloads.PostDto;
import com.blog.files.repository.PostRepository;
import com.blog.files.response.BaseResponse;
import com.blog.files.service.FileService;
import com.blog.files.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    @Operation(summary = "Create a new post", description = "API to create a new post for a user in a specific category")
    public ResponseEntity<BaseResponse<Object>> createPost(@RequestBody PostDto postDto,
                                                           @PathVariable String userId,
                                                           @PathVariable String categoryId) {
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);

        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Post Created")
                .data(createdPost)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    @Operation(summary = "Get posts by user", description = "API to retrieve all posts created by a specific user")
    public ResponseEntity<BaseResponse<Object>> getPostsByUser(@PathVariable String userId) {

        List<PostDto> posts = postService.getPostsByUser(userId);
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Posts retrieved successfully")
                .data(posts)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    @Operation(summary = "Get posts by user", description = "API to retrieve all posts created by a specific category")
    public ResponseEntity<BaseResponse<Object>> getPostsByCategory(@PathVariable String categoryId) {

        List<PostDto> posts = postService.getPostsByCategory(categoryId);
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("category retrieved successfully")
                .data(posts)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    @Operation(summary = "Get post by ID", description = "API to retrieve a specific post by its ID")
    public ResponseEntity<BaseResponse<Object>> getPostById(@PathVariable String postId) {
        PostDto postDto = postService.getPostById(postId);

        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Post retrieved successfully")
                .data(postDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/getAll")
    @Operation(summary = "Retrieve all posts", description = "API to fetch all posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPost();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable String postId, @RequestBody PostDto postDto) {
        PostDto postDos = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(postDos, HttpStatus.OK);
    }

    @DeleteMapping("/posts/delete/{postId}")
    @Operation(summary = "Delete a post", description = "API to delete a post by its ID")
    public ResponseEntity<BaseResponse<Object>> deletePost(@PathVariable String postId) {
        postService.deletePost(postId);

        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("Post deleted successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keyword") String keyword) {
        List<PostDto> posts = this.postService.searchPosts(keyword);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }


    @PostMapping("/posts/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image, @PathVariable String postId) throws IOException {

        PostDto postDto = this.postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);
        postDto.setImageName(fileName);
        PostDto updatePostDto = this.postService.updatePost(postId, postDto);

        return new ResponseEntity<>(updatePostDto, HttpStatus.OK);

    }

    @GetMapping("/posts/image/{imageName}")
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
