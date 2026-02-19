package com.theunsentdaak.api.service;

import com.theunsentdaak.api.dto.Dtos;
import com.theunsentdaak.api.model.Book;
import com.theunsentdaak.api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAll(String type) {
        if (type != null && !type.isBlank()) {
            return bookRepository.findByBookType(type);
        }
        return bookRepository.findAll();
    }

    public List<Book> getFeatured() {
        return bookRepository.findByIsFeaturedTrue();
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book create(Dtos.BookRequest dto) {
        Book book = new Book();
        mapDtoToBook(dto, book);
        return bookRepository.save(book);
    }

    public Book update(Long id, Dtos.BookRequest dto) {
        Book book = getById(id);
        mapDtoToBook(dto, book);
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    private void mapDtoToBook(Dtos.BookRequest dto, Book book) {
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setDescription(dto.getDescription());
        book.setCoverImage(dto.getCoverImage());
        book.setPrice(dto.getPrice());
        book.setBuyLink(dto.getBuyLink());
        book.setBookType(dto.getBookType());
        book.setIsFeatured(dto.getIsFeatured() != null ? dto.getIsFeatured() : false);
    }
}
