package com.projekt.ems.Services;

import com.projekt.ems.Dto.PublisherDto;

import java.util.List;

public interface PublisherService {
    List<PublisherDto> findAllPublishers();
    PublisherDto findPublisherById(Long id);
    PublisherDto savePublisher(PublisherDto publisher);
    PublisherDto updatePublisher(Long id, PublisherDto publisher);
    void deletePublisher(Long id);
}
