package com.theunsentdaak.api.repository;

import com.theunsentdaak.api.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findBySlugAndIsPublishedTrue(String slug);

    Page<Post> findByIsPublishedTrue(Pageable pageable);

    Page<Post> findByCategoryAndIsPublishedTrue(String category, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.isPublished = true AND " +
           "(LOWER(p.title) LIKE LOWER(CONCAT('%', :q, '%')) OR " +
           "LOWER(p.excerpt) LIKE LOWER(CONCAT('%', :q, '%')))")
    Page<Post> searchPublished(String q, Pageable pageable);

    List<Post> findTop3ByCategoryAndIsPublishedTrueAndIdNot(String category, Long id);

    long countByIsPublishedTrue();

    long countByIsPublishedFalse();
}
