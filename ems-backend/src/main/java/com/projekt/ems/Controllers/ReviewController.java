package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.ReviewDto;
import com.projekt.ems.Services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ReviewDto>> getBookReviews(@RequestParam Long book_id) {
        List<ReviewDto> reviews = reviewService.getBookReviews(book_id);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> findReviewById(@PathVariable Long id) {
        ReviewDto review = reviewService.getReviewById(id);
        if(review == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(review);
    }

    @PostMapping("/")
    public  ResponseEntity<ReviewDto> crateReview(@RequestBody ReviewDto reviewDto, @RequestParam Long user_id, Long book_id) {
        ReviewDto review = reviewService.saveReview(reviewDto, user_id, book_id);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto, @PathVariable Long id) {
        ReviewDto review = reviewService.updateReview(id, reviewDto);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.accepted().build();
    }
}
