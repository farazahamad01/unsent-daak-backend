package com.theunsentdaak.api.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

public class Dtos {

    @Data
    public static class LoginRequest {
        @NotBlank
        private String email;
        @NotBlank
        private String password;
    }

    @Data
    public static class LoginResponse {
        private String token;
        private String email;

        public LoginResponse(String token, String email) {
            this.token = token;
            this.email = email;
        }
    }

    @Data
    public static class PostRequest {
        @NotBlank
        private String title;
        private String slug;
        @NotBlank
        private String content;
        private String excerpt;
        private String category;
        private String tags;
        private String coverImage;
        private Boolean isPublished;
        private String metaTitle;
        private String metaDesc;
    }

    @Data
    public static class BookRequest {
        @NotBlank
        private String title;
        private String author;
        private String description;
        private String coverImage;
        private java.math.BigDecimal price;
        @NotBlank
        private String buyLink;
        private String bookType;
        private Boolean isFeatured;
    }

    @Data
    public static class DashboardStats {
        private long totalPosts;
        private long publishedPosts;
        private long draftPosts;
        private long totalBooks;

        public DashboardStats(long total, long published, long drafts, long books) {
            this.totalPosts = total;
            this.publishedPosts = published;
            this.draftPosts = drafts;
            this.totalBooks = books;
        }
    }
}
