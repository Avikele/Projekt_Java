package com.projekt.ems.Repositories;

import com.projekt.ems.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT r.* FROM user_has_book uhb " +
                    "JOIN review r on r.user_book_id = uhb.id " +
                    "WHERE uhb.book_id = :bookId", nativeQuery = true)
    List<Review> getBookReviews(Long bookId);
}
