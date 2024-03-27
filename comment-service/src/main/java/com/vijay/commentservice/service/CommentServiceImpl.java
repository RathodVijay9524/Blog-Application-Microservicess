package com.vijay.commentservice.service;

import com.vijay.commentservice.client.UserClient;
import com.vijay.commentservice.entity.Comment;
import com.vijay.commentservice.model.CommentRequest;
import com.vijay.commentservice.model.CommentResponse;
import com.vijay.commentservice.model.UserDto;
import com.vijay.commentservice.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {



    private final CommentRepository commentRepository;
    private final ModelMapper mapper;
    @Autowired
    private UserClient userClient;



    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
    }

    @Override
    public List<CommentResponse> findCommentByPostId(String postId) {
        List<CommentResponse> comments = commentRepository.findByPostId(postId);
        return comments;
    }

    @Override
    public List<CommentResponse> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> mapper.map(comment, CommentResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CommentResponse> getCommentById(String id) {
        return commentRepository.findById(id)
                .map(comment -> mapper.map(comment, CommentResponse.class));
    }

    @Override
    public CommentResponse createComment(CommentRequest commentRequest) {
        Comment comment = mapper.map(commentRequest, Comment.class);

//        UserDto userDto= userClient.curruntUser();
//        comment.setName(userDto.getName());
//        comment.setEmail(userDto.getEmail());
        Comment savedComment = commentRepository.save(comment);
        return mapper.map(savedComment, CommentResponse.class);
    }

    @Override
    public CommentResponse updateComment(String id, CommentRequest updatedCommentRequest) {
        Comment existingComment = commentRepository.findById(id).orElse(null);
        if (existingComment == null) {
            // Handle the case where the comment does not exist
            return null;
        }
        Comment updatedComment = mapper.map(updatedCommentRequest, Comment.class);
        updatedComment.setId(id); // Ensure ID consistency
        Comment savedComment = commentRepository.save(updatedComment);
        return mapper.map(savedComment, CommentResponse.class);
    }

    @Override
    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }
}
