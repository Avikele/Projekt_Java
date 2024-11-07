package com.projekt.ems.Controllers;


import com.projekt.ems.Dto.PublisherDto;
import com.projekt.ems.Services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    private PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PublisherDto>> findAllPublishers() {
        List<PublisherDto> publishers = publisherService.findAllPublishers();
        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherDto> findPublisherById(@PathVariable Long id) {
        PublisherDto publisher = publisherService.findPublisherById(id);
        return ResponseEntity.ok(publisher);
    }

    @PostMapping("/")
    public ResponseEntity<PublisherDto> savePublisher(@RequestBody PublisherDto publisherDto) {
        PublisherDto publisher = publisherService.savePublisher(publisherDto);
        return ResponseEntity.ok(publisher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublisherDto> updatePublisher(@PathVariable Long id, @RequestBody PublisherDto publisherDto) {
        PublisherDto publisher = publisherService.updatePublisher(id, publisherDto);
        return ResponseEntity.ok(publisher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublisher(@PathVariable Long id) {
        publisherService.deletePublisher(id);
        return ResponseEntity.noContent().build();
    }
}
