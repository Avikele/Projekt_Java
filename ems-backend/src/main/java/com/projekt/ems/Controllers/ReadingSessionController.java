package com.projekt.ems.Controllers;

import com.projekt.ems.Dto.ReadingSessionDto;
import com.projekt.ems.Services.ReadingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reading_session")
public class ReadingSessionController {

    private ReadingSessionService readingSessionService;

    @Autowired
    public ReadingSessionController(ReadingSessionService readingSessionService) {
        this.readingSessionService = readingSessionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReadingSessionDto> getReadingSessionById(@PathVariable Long id) {
        ReadingSessionDto readingSessionDto = readingSessionService.getReadingSessionById(id);
        return ResponseEntity.ok(readingSessionDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<ReadingSessionDto>> getAllReadingSessions(@RequestParam Long user_statistics_id) {
        List<ReadingSessionDto> readingSessionDto = readingSessionService.getAllReadingSession(user_statistics_id);
        return ResponseEntity.ok(readingSessionDto);
    }

    @PostMapping("/")
    public ResponseEntity<ReadingSessionDto> createReadingSession(@RequestBody ReadingSessionDto readingSessionDto, @RequestParam Long book_id, @RequestParam Long user_id) {
        ReadingSessionDto readingSession = readingSessionService.crateReadingSession(readingSessionDto, book_id, user_id);
        return ResponseEntity.ok(readingSession);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReadingSessionDto> updateReadingSession(@PathVariable Long id, @RequestBody ReadingSessionDto readingSessionDto) {
        ReadingSessionDto readingSession = readingSessionService.updateReadingSession(id, readingSessionDto);
        return ResponseEntity.ok(readingSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReadingSessionDto> deleteReadingSession(@PathVariable Long id) {
        readingSessionService.deleteReadingSession(id);
        return ResponseEntity.ok(null);
    }
}
