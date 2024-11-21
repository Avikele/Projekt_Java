package com.projekt.ems.Services;


import com.projekt.ems.Dto.RatingDto;


public interface RatingService {
    RatingDto crateRating(Long userId, Long bookId, RatingDto rating);
    RatingDto updateRating(Long id, RatingDto ratingDto);
    void deleteRating(Long id);
}
