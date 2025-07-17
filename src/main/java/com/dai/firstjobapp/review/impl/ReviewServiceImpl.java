package com.dai.firstjobapp.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dai.firstjobapp.company.Company;
import com.dai.firstjobapp.company.CompanyService;
import com.dai.firstjobapp.review.Review;
import com.dai.firstjobapp.review.ReviewRepository;
import com.dai.firstjobapp.review.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findAllByCompanyId(companyId);
        if(reviews == null) {
            return null; // Found no reviews for the company
        } else {
            return reviews.stream()
                          .filter(review -> review.getId().equals(reviewId))
                          .findFirst()
                          .orElse(null); // Return the review if found, otherwise null
        }
    }

    @Override
    public Review createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null) {
            review.setCompany(company);
            Review createdReview = reviewRepository.save(review);
            return createdReview;
        } else {
            return null;
        }
    }

    @Override
    public Review updateReviewById(Long companyId, Long reviewId, Review updatedReview) {
        Review existingReview = getReviewById(companyId, reviewId);
        if(existingReview != null) {
            existingReview.setTitle(updatedReview.getTitle());
            existingReview.setContent(updatedReview.getContent());
            existingReview.setRating(updatedReview.getRating());
            return reviewRepository.save(existingReview);
        } else {
            return null; // Review not found
        }
    }

    @Override
    public boolean deleteReviewById(Long companyId, Long reviewId) {
        Review existingReview = getReviewById(companyId, reviewId);
        if(existingReview != null) {
            reviewRepository.delete(existingReview);
            return true; // Return the deleted review
        } else {
            return false; // Review not found
        }
    }
    
}
