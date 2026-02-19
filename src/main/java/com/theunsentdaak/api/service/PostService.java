package com.theunsentdaak.api.service;

import com.theunsentdaak.api.dto.Dtos;
import com.theunsentdaak.api.model.Post;
import com.theunsentdaak.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // ---------- Public ----------

    public Page<Post> getPublished(int page, String category) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        if (category != null && !category.isBlank()) {
            return postRepository.findByCategoryAndIsPublishedTrue(category, pageable);
        }
        return postRepository.findByIsPublishedTrue(pageable);
    }

    public Post getBySlug(String slug) {
        return postRepository.findBySlugAndIsPublishedTrue(slug)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Page<Post> search(String q, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("createdAt").descending());
        return postRepository.searchPublished(q, pageable);
    }

    public List<Post> getRelated(String category, Long excludeId) {
        return postRepository.findTop3ByCategoryAndIsPublishedTrueAndIdNot(category, excludeId);
    }

    // ---------- Admin ----------

    public List<Post> getAll() {
        return postRepository.findAll(Sort.by("createdAt").descending());
    }

    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post create(Dtos.PostRequest dto) {
        Post post = new Post();
        mapDtoToPost(dto, post);
        if (post.getSlug() == null || post.getSlug().isBlank()) {
            post.setSlug(generateSlug(dto.getTitle()));
        }
        post.setReadTime(calculateReadTime(dto.getContent()));
        return postRepository.save(post);
    }

    public Post update(Long id, Dtos.PostRequest dto) {
        Post post = getById(id);
        mapDtoToPost(dto, post);
        post.setReadTime(calculateReadTime(dto.getContent()));
        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    // ---------- Helpers ----------

    private void mapDtoToPost(Dtos.PostRequest dto, Post post) {
        post.setTitle(dto.getTitle());
        if (dto.getSlug() != null && !dto.getSlug().isBlank()) {
            post.setSlug(dto.getSlug());
        }
        post.setContent(dto.getContent());
        post.setExcerpt(dto.getExcerpt());
        post.setCategory(dto.getCategory());
        post.setTags(dto.getTags());
        post.setCoverImage(dto.getCoverImage());
        post.setIsPublished(dto.getIsPublished() != null ? dto.getIsPublished() : false);
        post.setMetaTitle(dto.getMetaTitle());
        post.setMetaDesc(dto.getMetaDesc());
    }

    private String generateSlug(String title) {
        String normalized = Normalizer.normalize(title, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized)
                .replaceAll("")
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("[\\s-]+", "-")
                .replaceAll("^-|-$", "");
    }

    private int calculateReadTime(String content) {
        if (content == null) return 1;
        int wordCount = content.trim().split("\\s+").length;
        return Math.max(1, (int) Math.ceil(wordCount / 200.0));
    }
}
