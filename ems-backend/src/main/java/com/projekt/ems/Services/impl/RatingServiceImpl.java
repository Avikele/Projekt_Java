package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.RatingDto;
import com.projekt.ems.Dto.UserBookDto;
import com.projekt.ems.Models.Rating;
import com.projekt.ems.Models.UserBook;
import com.projekt.ems.Repositories.RatingRepository;
import com.projekt.ems.Repositories.UserBookRepository;
import com.projekt.ems.Services.BookService;
import com.projekt.ems.Services.RatingService;
import com.projekt.ems.Services.UserBookService;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private RatingRepository ratingRepository;
    private UserBookRepository userBookRepository;
    private UserBookService userBookService;
    private BookService bookService;

    public RatingServiceImpl(RatingRepository ratingRepository, UserBookRepository userBookRepository, UserBookService userBookService, BookService bookService) {
        this.ratingRepository = ratingRepository;
        this.userBookRepository = userBookRepository;
        this.userBookService = userBookService;
        this.bookService = bookService;
    }

    @Override
    public RatingDto crateRating(Long userId, Long bookId, RatingDto ratingDto) {
        UserBookDto userBookDto = userBookService.getOrCreateUserBook(userId, bookId);
        UserBook userBook = userBookRepository.findById(userBookDto.getId()).orElseThrow(() -> new RuntimeException("Rating not found"));
        Rating rating = new Rating();
        rating.setUserBook(userBook);
        rating.setScore(ratingDto.getScore());
        Rating saveRating = ratingRepository.save(rating);
        bookService.addRating(rating.getScore(), rating.getUserBook().getBook());
        return new RatingDto(saveRating.getId(), saveRating.getScore());
    }

    @Override
    public RatingDto updateRating(Long id, RatingDto ratingDto) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        bookService.removeRating(rating.getScore(), rating.getUserBook().getBook());
        rating.setScore(ratingDto.getScore());
        Rating saveRating = ratingRepository.save(rating);
        bookService.addRating(rating.getScore(), rating.getUserBook().getBook());
        return new RatingDto(saveRating.getId(), saveRating.getScore());
    }

    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        bookService.removeRating(rating.getScore(), rating.getUserBook().getBook());
        ratingRepository.deleteById(id);
    }
}
