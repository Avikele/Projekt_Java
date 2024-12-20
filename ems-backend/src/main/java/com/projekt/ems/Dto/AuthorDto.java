package com.projekt.ems.Dto;

import com.projekt.ems.Models.Author;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class AuthorDto {
    private long id;
    private String name;
    private String surname;

}