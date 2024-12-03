package com.projekt.ems.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.projekt.ems.Dto.ExportDto;
import com.projekt.ems.Dto.UserStatisticsDto;
import com.projekt.ems.Services.UserStatisticsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

    @GetMapping("/json/{id}")
    public void exportUserStatistics(@PathVariable Long id, HttpServletResponse response) throws IOException {
        ExportDto export = userStatisticsService.exportUserStatistics(id);

        response.setContentType("application/json");
        response.setHeader("Content-Disposition", String.format("attachment; filename=%s.json", export.getTitle()));

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.writerWithDefaultPrettyPrinter().writeValue(response.getOutputStream(), export);
    }

    @PostMapping("/json")
    public String importUserStatistics(@RequestParam Long user_id, @RequestParam Long book_id, @RequestBody String jsonContent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            ExportDto exportDto = objectMapper.readValue(jsonContent, ExportDto.class);

            userStatisticsService.importUserStatistics(user_id, book_id, exportDto);

            return "Import";
        } catch (IOException e) {
            e.printStackTrace();
            return "Import failed: " + e.getMessage();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserStatisticsDto> updateUserStatistics(@PathVariable Long id, @RequestBody UserStatisticsDto userStatisticsDto) {
        UserStatisticsDto userStatistics = userStatisticsService.updateUserStatistics(id, userStatisticsDto);
        return ResponseEntity.ok(userStatistics);
    }
}
