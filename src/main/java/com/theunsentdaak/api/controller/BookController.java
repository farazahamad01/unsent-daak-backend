package com.theunsentdaak.api.controller;

import com.theunsentdaak.api.model.Book;
import com.theunsentdaak.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks(@RequestParam(required = false) String type) {
        return bookService.getAll(type);
    }

    @GetMapping("/featured")
    public List<Book> getFeatured() {
        return bookService.getFeatured();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return bookService.getById(id);
    }
}
