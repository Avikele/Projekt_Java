package com.projekt.ems.Services;

import com.projekt.ems.Dto.FriendsDto;
import com.projekt.ems.Dto.UserDto;

import java.util.List;

public interface FriendsService {
    UserDto getFriendInfo(Long user_id, Long friend_id);
    List<FriendsDto> getFriendsList(Long user_id);
    List<FriendsDto> getFriendsRequests(Long user_id);
    FriendsDto sendRequest(Long user_id, Long friend_id);
    FriendsDto acceptRequest(Long user_id, Long friend_id);
    void removeFriend(Long user_id, Long friend_id);
}
