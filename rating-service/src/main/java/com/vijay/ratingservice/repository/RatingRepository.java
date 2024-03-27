package com.vijay.ratingservice.repository;

import com.vijay.ratingservice.entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating,String> {

    //custom finder methods
    List<Rating> findByUserId(String userId);


    List<Rating> findByPostId(String postId);

    List<Rating> findByCommentId(String commentId);
}
