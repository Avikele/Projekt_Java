package com.projekt.ems.Repositories;

import com.projekt.ems.Models.ReadingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingSessionRepository extends JpaRepository<ReadingSession, Long> {
    @Query(value = "SELECT * FROM reading_session " +
            "WHERE user_statistics_id = :userStatisticsId", nativeQuery = true)
    List<ReadingSession> getAllReadingSession(Long userStatisticsId);
}
