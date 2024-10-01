package com.blog.files.controller;

import com.blog.files.payloads.CommentDto;
import com.blog.files.response.BaseResponse;
import com.blog.files.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    // @Operation(summary = "Create a new post", description = "API to create a new post for a user in a specific category")
    public ResponseEntity<BaseResponse<Object>> createPost(@RequestBody CommentDto comment, @PathVariable String postId) {
        CommentDto commentDto = this.commentService.addComment(comment, postId);
        BaseResponse<Object> response = BaseResponse.builder()
                .successMsg("comment created")
                .data(commentDto)
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("comments/{commentId}")
    //@Operation(summary = "Create a new post", description = "API to create a new post for a user in a specific category")
    public ResponseEntity<BaseResponse<Object>> deleteComment(@PathVariable String commentId) {
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
