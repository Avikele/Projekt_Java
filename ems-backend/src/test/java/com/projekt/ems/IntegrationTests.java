package com.projekt.ems;

import com.projekt.ems.Dto.AuthorDto;
import com.projekt.ems.Dto.CategoryDto;
import com.projekt.ems.Dto.PublisherDto;
import com.projekt.ems.Models.Author;
import com.projekt.ems.Models.Category;
import com.projekt.ems.Models.Publisher;
import com.projekt.ems.Repositories.AuthorRepository;
import com.projekt.ems.Repositories.CategoryRepository;
import com.projekt.ems.Repositories.PublisherRepository;
import com.projekt.ems.Services.AuthorService;
import com.projekt.ems.Services.impl.CategoryServiceImpl;
import com.projekt.ems.Services.impl.PublisherServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
public class IntegrationTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherServiceImpl publisherService;

    @Test
    public void testCreateCategory() {
        CategoryDto categoryDto = CategoryDto.builder()
                .name("Fiction")
                .build();

        CategoryDto saveCategoryDto = categoryService.saveCategory(categoryDto);

        Category category = categoryRepository.findById(saveCategoryDto.getId()).orElse(null);
        assertNotNull(category);
        assertEquals("Fiction", category.getName());
    }

    @Test
    public void getCreateCategory() {
        CategoryDto categoryDto = CategoryDto.builder()
                .name("Fiction")
                .build();

        CategoryDto saveCategoryDto = categoryService.saveCategory(categoryDto);

        CategoryDto findCategory = categoryService.findCategoryById(saveCategoryDto.getId());
        assertNotNull(findCategory);
        assertEquals("Fiction", findCategory.getName());
    }

    @Test
    public void testCreateAuthor() {
        Author author = new Author();
        author.setName("Adam");
        author.setSurname("Mickiewicz");

        AuthorDto authorDto = AuthorDto.builder()
                .name(author.getName())
                .surname(author.getSurname())
                .build();

        AuthorDto saveAuthorDto = authorService.saveAuthor(authorDto);

        Author findAuthor = authorRepository.findById(saveAuthorDto.getId()).orElse(null);
        assertNotNull(findAuthor);
        assertEquals("Adam", findAuthor.getName());
        assertEquals("Mickiewicz", findAuthor.getSurname());
    }

    @Test
    public void testGetAuthor() {
        Author author = new Author();
        author.setName("Adam");
        author.setSurname("Mickiewicz");

        AuthorDto authorDto = AuthorDto.builder()
                .name(author.getName())
                .surname(author.getSurname())
                .build();

        AuthorDto saveAuthorDto = authorService.saveAuthor(authorDto);

        AuthorDto findAuthor = authorService.findAuthorById(saveAuthorDto.getId());
        assertNotNull(findAuthor);
        assertEquals("Adam", findAuthor.getName());
        assertEquals("Mickiewicz", findAuthor.getSurname());
    }

    @Test
    public void testCreatePublisher() {
        PublisherDto publisherDto = PublisherDto.builder()
                .name("test")
                .build();

        PublisherDto savePublisherDto = publisherService.savePublisher(publisherDto);

        Publisher findPublisher = publisherRepository.findById(savePublisherDto.getId()).orElse(null);
        assertNotNull(findPublisher);
        assertEquals("test", findPublisher.getName());
    }

    @Test
    public void testGetPublisher() {
        PublisherDto publisherDto = PublisherDto.builder()
                .name("test")
                .build();

        PublisherDto savePublisherDto = publisherService.savePublisher(publisherDto);

        PublisherDto findPublisher = publisherService.findPublisherById(savePublisherDto.getId());
        assertNotNull(findPublisher);
        assertEquals("test", findPublisher.getName());
    }


}
