package com.projekt.ems.Repositories;

import com.projekt.ems.Models.ReadingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReadingSessionRepository extends JpaRepository<ReadingSession, Long> {
    @Query(value = "SELECT * FROM reading_session " +
            "WHERE user_statistics_id = :userStatisticsId", nativeQuery = true)
    List<ReadingSession> getAllReadingSession(long userStatisticsId);

    @Modifying
    @Transactional
    @Query(value = "Delete FROM reading_session WHERE user_statistics_id = :userStatisticsId", nativeQuery = true)
    void deleteAllReadingSessions(long userStatisticsId);
}
