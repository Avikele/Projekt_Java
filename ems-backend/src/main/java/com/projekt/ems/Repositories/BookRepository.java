package com.projekt.ems.Repositories;

import com.projekt.ems.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long>{
}
