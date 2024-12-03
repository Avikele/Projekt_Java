package com.projekt.ems.Dto;

import com.projekt.ems.Models.Friends;
import com.projekt.ems.Models.User;
import lombok.Data;

@Data
public class FriendsDto {
    private Long user_id;
    private String username;
    private Integer status;

    public FriendsDto(Friends friends, User user) {
        User friend = friends.getUser1() == user ? friends.getUser2() : friends.getUser1();
        this.user_id = friend.getId();
        this.username = friend.getUsername();
        this.status = friends.getStatus();
    }

    public FriendsDto(User user) {
        this.user_id = user.getId();
        this.username = user.getUsername();
        this.status = null;
    }

    public FriendsDto() {

    }
}
