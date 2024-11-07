package com.projekt.ems.Dto;

import lombok.*;

import java.util.Set;

@Data
@Builder
public class BookDto {
    private long id;
    private String title;
    private Integer pages;
    private String cover;
    private boolean status;
    private Double avgRating;
    private Set<String> authors;
    private String publisher;
    private Set<String> categories;
}
