package com.vijay.ratingservice.service;


import com.vijay.ratingservice.entity.Rating;

import java.util.List;


public interface RatingService {

    //create
    Rating create(Rating rating);


    //get all ratings
    List<Rating> getRatings();

    //get all by UserId
    List<Rating> getRatingByUserId(String userId);

    //get all by hotel
    List<Rating> getRatingByPostId(String postId);

    List<Rating> getRatingByCommentId(String commentId);
}
