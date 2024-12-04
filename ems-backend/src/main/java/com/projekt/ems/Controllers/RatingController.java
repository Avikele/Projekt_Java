package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.RatingDto;
import com.projekt.ems.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/")
    public ResponseEntity<RatingDto> crateRating(@RequestBody RatingDto ratingDto, @RequestParam Long book_id, @RequestParam Long user_id) {
        RatingDto rating =  ratingService.crateRating(user_id, book_id, ratingDto);
        return ResponseEntity.ok(rating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingDto> updateRating(@PathVariable Long id, @RequestBody RatingDto ratingDto) {
        RatingDto rating = ratingService.updateRating(id, ratingDto);
        return ResponseEntity.ok(rating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRating(@PathVariable Long id){
        return ResponseEntity.accepted().build();
    }
}
