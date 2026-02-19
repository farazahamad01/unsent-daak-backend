package com.theunsentdaak.api.controller;

import com.theunsentdaak.api.dto.Dtos;
import com.theunsentdaak.api.repository.BookRepository;
import com.theunsentdaak.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HealthController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/api/health")
    public Map<String, String> health() {
        return Map.of("status", "UP", "brand", "The Unsent Daak");
    }

    @GetMapping("/api/admin/stats")
    public ResponseEntity<Dtos.DashboardStats> getDashboardStats() {
        long published = postRepository.countByIsPublishedTrue();
        long drafts = postRepository.countByIsPublishedFalse();
        long total = published + drafts;
        long books = bookRepository.count();

        return ResponseEntity.ok(new Dtos.DashboardStats(total, published, drafts, books));
    }
}