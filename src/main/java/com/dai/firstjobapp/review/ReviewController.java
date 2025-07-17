package com.dai.firstjobapp.review;

import java.util.List;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("companies/{companyId}/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        Review createdReview = reviewService.createReview(companyId, review);
        if(createdReview != null) {
            return new ResponseEntity<>("Review created successfully!", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create review!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review review = reviewService.getReviewById(companyId, reviewId);
        if(review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review or Company not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReviewById(@PathVariable Long companyId, 
                                    @PathVariable Long reviewId, @RequestBody Review updatedReview) {
        Review review = reviewService.updateReviewById(companyId, reviewId, updatedReview);
        if(review != null) {
            return new ResponseEntity<>("Review updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update review!", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        if(reviewService.deleteReviewById(companyId, reviewId)) {
            return new ResponseEntity<>("Review deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review or Company not found!", HttpStatus.NOT_FOUND);
        }
    }
}
