package com.vijay.ratingservice.service;

import com.vijay.ratingservice.entity.Rating;
import com.vijay.ratingservice.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {


    @Autowired
    private RatingRepository repository;
    @Override
    public Rating create(Rating rating) {

        return repository.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String userId) {

        return repository.findByUserId(userId);
    }

    @Override
    public List<Rating> getRatingByPostId(String postId) {

        return repository.findByPostId(postId);
    }
    @Override
    public List<Rating> getRatingByCommentId(String commentId) {
        List<Rating> byCommentId= repository.findByCommentId(commentId);
        return byCommentId;
    }
}

