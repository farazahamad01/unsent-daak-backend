package com.theunsentdaak.api.controller;

import com.theunsentdaak.api.model.Post;
import com.theunsentdaak.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public Page<Post> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(required = false) String category) {
        return postService.getPublished(page, category);
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getPost(@PathVariable String slug) {
        try {
            return ResponseEntity.ok(postService.getBySlug(slug));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(Map.of("error", "Post not found"));
        }
    }

    @GetMapping("/search")
    public Page<Post> search(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int page) {
        return postService.search(q, page);
    }

    @GetMapping("/{slug}/related")
    public List<Post> getRelated(@PathVariable String slug) {
        Post post = postService.getBySlug(slug);
        return postService.getRelated(post.getCategory(), post.getId());
    }
}
