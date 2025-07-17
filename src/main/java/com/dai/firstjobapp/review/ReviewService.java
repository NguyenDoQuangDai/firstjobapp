package com.dai.firstjobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review getReviewById(Long companyId, Long reviewId);
    Review createReview(Long companyId, Review review);
    Review updateReviewById(Long companyId, Long reviewId, Review updatedReview);
    boolean deleteReviewById(Long companyId, Long reviewId);
}
