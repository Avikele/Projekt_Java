package com.projekt.ems.Services;

import com.projekt.ems.Dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getBookReviews(Long book_id);
    ReviewDto getReviewById(Long id);
    ReviewDto saveReview(ReviewDto review, Long userId, Long bookId);
    ReviewDto updateReview(Long id, ReviewDto review);
    void deleteReview(Long id);
}
