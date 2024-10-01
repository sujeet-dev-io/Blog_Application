package com.blog.files.service.Impl;

import com.blog.files.entites.Comment;
import com.blog.files.entites.Post;
import com.blog.files.exception.NotFoundException;
import com.blog.files.payloads.CommentDto;
import com.blog.files.repository.CommentRepository;
import com.blog.files.repository.PostRepository;
import com.blog.files.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentImpl implements CommentService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto addComment(CommentDto commentDto, String postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new NotFoundException("post id not found"));
        String commentId = UUID.randomUUID().toString();
        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setCommentId(commentId);
        Comment saveComment = commentRepository.save(comment);
       // comment.setContent(saveComment.getContent());
      return modelMapper.map(saveComment, CommentDto.class);
    }

    @Override
    public void deleteComment(String postId) {
//    commentRepository.deleteById(postId);
        Comment comment = commentRepository.findById(postId).orElseThrow(()-> new NotFoundException("comment id not found"));
        commentRepository.delete(comment);
    }
}
