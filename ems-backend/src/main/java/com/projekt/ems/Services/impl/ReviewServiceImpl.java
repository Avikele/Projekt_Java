package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.ReviewDto;
import com.projekt.ems.Dto.UserBookDto;
import com.projekt.ems.Models.Review;
import com.projekt.ems.Models.UserBook;
import com.projekt.ems.Repositories.ReviewRepository;
import com.projekt.ems.Repositories.UserBookRepository;
import com.projekt.ems.Services.ReviewService;
import com.projekt.ems.Services.UserBookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private UserBookService userBookService;
    private UserBookRepository userBookRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserBookRepository userBookRepository, UserBookService userBookService) {
        this.reviewRepository = reviewRepository;
        this.userBookRepository = userBookRepository;
        this.userBookService = userBookService;
    }

    @Override
    public List<ReviewDto> getBookReviews(Long book_id) {
        List<Review> reviews = reviewRepository.getBookReviews(book_id);
        List<ReviewDto> result = new ArrayList<ReviewDto>();
        reviews.forEach((r) -> {
            ReviewDto newReview = new ReviewDto(r.getId(), r.getStatus(), r.getText(), r.getUserBook().getUser().getUsername());
            result.add(newReview);
        });
        return result;
    }

    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        return mapToReviewDto(review);
    }

    @Override
    public ReviewDto saveReview(ReviewDto reviewDto, Long userId, Long bookId) {
        UserBookDto userBookDto = userBookService.getOrCreateUserBook(userId, bookId);
        UserBook userBook = userBookRepository.findById(userBookDto.getId()).orElseThrow(() -> new RuntimeException("Review not found"));
        Review review = new Review();
        review.setUserBook(userBook);
        review.setText(reviewDto.getText());
        review.setStatus(0);
        Review saveReview = reviewRepository.save(review);
        return mapToReviewDto(saveReview);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
        review.setText(reviewDto.getText());
        review.setStatus(reviewDto.getStatus());
        Review saveReview = reviewRepository.save(review);
        return mapToReviewDto(saveReview);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private ReviewDto mapToReviewDto(Review review) {
        if(review == null) {
            return null;
        }

        return ReviewDto.builder()
                .text(review.getText())
                .user_book_id(review.getId())
                .status(review.getStatus())
                .build();
    }
}
