package com.projekt.ems.Repositories;

import com.projekt.ems.Models.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Long> {
    @Query(value = "SELECT * " +
            "FROM friends " +
            "WHERE user_id1 IN (:user_id, :friend_id) AND user_id2 IN (:user_id, :friend_id) LIMIT 1",
            nativeQuery = true)
    Friends getFriend(@Param("user_id") Long user_id, @Param("friend_id") Long friend_id);

    @Query(value = "SELECT * " +
            "FROM friends " +
            "WHERE user_id1 = :user_id OR user_id2 = :user_id AND status = 1",
            nativeQuery = true)
    List<Friends> getFriendsList(@Param("user_id") Long user_id);

    @Query(value = "SELECT * " +
            "FROM friends " +
            "WHERE user_id2 = :user_id AND status = 0",
            nativeQuery = true)
    List<Friends> getFriendsRequests(@Param("user_id") Long user_id);
}
