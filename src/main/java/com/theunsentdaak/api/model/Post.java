package com.theunsentdaak.api.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false)
    private String slug;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 500)
    private String excerpt;

    @Column(length = 50)
    private String category; // poem, letter, story, reflection

    private String tags; // comma-separated

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "is_published")
    private Boolean isPublished = false;

    @Column(name = "read_time")
    private Integer readTime;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_desc", length = 500)
    private String metaDesc;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
