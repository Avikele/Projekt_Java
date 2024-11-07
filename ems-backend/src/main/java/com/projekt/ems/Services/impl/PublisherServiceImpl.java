package com.projekt.ems.Services.impl;


import com.projekt.ems.Dto.PublisherDto;
import com.projekt.ems.Models.Publisher;
import com.projekt.ems.Repositories.PublisherRepository;
import com.projekt.ems.Services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class PublisherServiceImpl implements PublisherService {

    private PublisherRepository publisherRepository;

    @Autowired
    public PublisherServiceImpl(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<PublisherDto> findAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers.stream()
                .map(this::mapToPublisherDto)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherDto findPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        return mapToPublisherDto(publisher);
    }

    @Override
    public PublisherDto savePublisher(PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherDto.getName());
        Publisher savedPublisher = publisherRepository.save(publisher);
        return mapToPublisherDto(savedPublisher);
    }

    @Override
    public PublisherDto updatePublisher(Long id, PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.findById(id).orElse(null);
        publisher.setName(publisherDto.getName());
        Publisher updatedPublisher = publisherRepository.save(publisher);
        return mapToPublisherDto(updatedPublisher);
    }


    @Override
    public void deletePublisher(Long id) {
        publisherRepository.deleteById(id);
    }



    private PublisherDto mapToPublisherDto(Publisher publisher) {
        if (publisher == null) {
            return null;
        }

        return PublisherDto.builder()
                .id(publisher.getId())
                .name(publisher.getName())
                .build();
    }


}
