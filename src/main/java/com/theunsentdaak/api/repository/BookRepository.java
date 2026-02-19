package com.theunsentdaak.api.repository;

import com.theunsentdaak.api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByBookType(String bookType);
    List<Book> findByIsFeaturedTrue();
}
