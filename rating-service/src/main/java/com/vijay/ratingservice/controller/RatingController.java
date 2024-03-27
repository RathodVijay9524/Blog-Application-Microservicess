package com.vijay.ratingservice.controller;

import com.vijay.ratingservice.entity.Rating;
import com.vijay.ratingservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    //create rating
   // @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating rating) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    //get all
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    //get all of user
   // @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }


    //get all of hotels
    @GetMapping("/posts/{postId}")
    public ResponseEntity<List<Rating>> getRatingsByPostId(@PathVariable String postId) {
        return ResponseEntity.ok(ratingService.getRatingByPostId(postId));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<List<Rating>> getRatingsByCommentId(@PathVariable String commentId) {
        return ResponseEntity.ok(ratingService.getRatingByCommentId(commentId));
    }


}
