package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.FriendsDto;
import com.projekt.ems.Dto.UserDto;
import com.projekt.ems.Services.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    final private FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getFriendInfo(@PathVariable Long id, @RequestParam Long user_id) {
        UserDto user = friendsService.getFriendInfo(user_id, id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/")
    public ResponseEntity<List<FriendsDto>> getFriendsList(@RequestParam Long user_id) {
        List<FriendsDto> friends = friendsService.getFriendsList(user_id);
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<FriendsDto>> getFriendsRequests(@RequestParam Long user_id) {
        List<FriendsDto> friends = friendsService.getFriendsRequests(user_id);
        return ResponseEntity.ok(friends);
    }

    @PostMapping("/{id}")
    public ResponseEntity<FriendsDto> sendFriendRequest(@PathVariable Long id, @RequestParam Long user_id) {
        FriendsDto friend = friendsService.sendRequest(user_id, id);
        return ResponseEntity.ok(friend);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FriendsDto> acceptFriendRequest(@PathVariable Long id, @RequestParam Long user_id) {
        FriendsDto friend = friendsService.acceptRequest(user_id, id);
        return ResponseEntity.ok(friend);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeFriend(@PathVariable Long id, @RequestParam Long user_id) {
        friendsService.removeFriend(user_id, id);
        return ResponseEntity.accepted().build();
    }
}
