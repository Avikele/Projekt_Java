package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.UserStatisticsDto;
import com.projekt.ems.Services.UserStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user_statistics")
public class UserStatisticsController {

    private UserStatisticsService userStatisticsService;

    @Autowired
    public UserStatisticsController(UserStatisticsService userStatisticsService) {
        this.userStatisticsService = userStatisticsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserStatisticsDto> getUserStatistics(@PathVariable Long id) {
        UserStatisticsDto userStatisticsDto = userStatisticsService.getUserStatistics(id);
        return ResponseEntity.ok(userStatisticsDto);
    }

    @GetMapping("/")
    public ResponseEntity<UserStatisticsDto> getOrCrateUserStatistics(@RequestParam Long user_id, @RequestParam Long book_id) {
        UserStatisticsDto userStatisticsDto = userStatisticsService.getOrCrateUserStatistics(user_id, book_id);
        return ResponseEntity.ok(userStatisticsDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserStatisticsDto> updateUserStatistics(@PathVariable Long id, @RequestBody UserStatisticsDto userStatisticsDto) {
        UserStatisticsDto userStatistics = userStatisticsService.updateUserStatistics(id, userStatisticsDto);
        return ResponseEntity.ok(userStatistics);
    }
}
