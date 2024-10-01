package com.blog.files.service;

import com.blog.files.payloads.CommentDto;

public interface CommentService {

    CommentDto addComment(CommentDto commentDto, String postId);

    void deleteComment(String postId);
}
