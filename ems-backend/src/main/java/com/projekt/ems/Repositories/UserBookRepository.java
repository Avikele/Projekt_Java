package com.projekt.ems.Repositories;

import com.projekt.ems.Dto.UserBookDto;
import com.projekt.ems.Models.UserBook;
import jakarta.persistence.SqlResultSetMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBookRepository extends JpaRepository<UserBook, Long> {
    @Query(value = "SELECT * " +
            "FROM user_has_book " +
            "WHERE user_id = :userId AND book_id = :bookId LIMIT 1",
            nativeQuery = true)
    UserBook getUserBook(@Param("userId") Long userId, @Param("bookId") Long bookId);
}
