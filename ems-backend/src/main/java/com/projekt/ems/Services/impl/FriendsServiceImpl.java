package com.projekt.ems.Services.impl;

import com.projekt.ems.Dto.FriendsDto;
import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Models.Friends;
import com.projekt.ems.Models.User;
import com.projekt.ems.Repositories.FriendsRepository;
import com.projekt.ems.Repositories.UserRepository;
import com.projekt.ems.Services.FriendsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {

    final private FriendsRepository friendsRepository;
    final private UserRepository userRepository;

    public FriendsServiceImpl(FriendsRepository friendsRepository, UserRepository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getFriendInfo(Long user_id, Long friend_id) {
        Friends friend = friendsRepository.getFriend(user_id, friend_id);
        if(friend == null || friend.getStatus() != 1) {
            throw new RuntimeException("You are not friends with this user!");
        }
        User user = friend.getUser1().getId() != user_id ? friend.getUser1() : friend.getUser2();
        return new UserDto(user);
    }

    @Override
    public List<FriendsDto> getFriendsList(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        List<Friends> friends = friendsRepository.getFriendsList(user_id);
        List<FriendsDto> friendsDto = new ArrayList<>();
        friends.forEach((f) -> friendsDto.add(new FriendsDto(f, user)));
        return friendsDto;
    }

    @Override
    public List<FriendsDto> getFriendsRequests(Long user_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        List<Friends> friends = friendsRepository.getFriendsRequests(user_id);
        List<FriendsDto> friendsDto = new ArrayList<>();
        friends.forEach((f) -> friendsDto.add(new FriendsDto(f, user)));
        return friendsDto;
    }

    @Override
    public FriendsDto sendRequest(Long user_id, Long friend_id) {
        User user = userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found"));
        User user2 = userRepository.findById(friend_id).orElseThrow(() -> new RuntimeException("User not found"));
        Friends friend = friendsRepository.getFriend(user_id, friend_id);
        if(friend != null && friend.getStatus() == 0) {
            throw new RuntimeException("Request already sent");
        }
        if(friend != null && friend.getStatus() == 1) {
            throw new RuntimeException("You are friends already");
        }
        Friends newFriend = new Friends();
        newFriend.setUser1(user);
        newFriend.setUser2(user2);
        newFriend.setStatus(0);
        friendsRepository.save(newFriend);
        return new FriendsDto(user2);
    }

    @Override
    public FriendsDto acceptRequest(Long user_id, Long friend_id) {
        Friends friend = friendsRepository.getFriend(user_id, friend_id);
        if(friend == null) {
            throw new RuntimeException("User not found");
        }
        if(friend.getStatus() == 1) {
            throw new RuntimeException("You are friends already");
        }
        if(friend.getUser2().getId() != user_id) {
            throw new RuntimeException("You can not accept this request");
        }
        friend.setStatus(1);
        friendsRepository.save(friend);
        return new FriendsDto(friend.getUser1());
    }

    @Override
    public void removeFriend(Long user_id, Long friend_id) {
        Friends friend = friendsRepository.getFriend(user_id, friend_id);
        if(friend == null) {
            throw new RuntimeException("Friend not found");
        }
        friendsRepository.deleteById(friend.getId());
    }
}
