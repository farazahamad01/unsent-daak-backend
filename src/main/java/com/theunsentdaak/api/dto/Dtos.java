package com.theunsentdaak.api.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public class Dtos {

    public static class LoginRequest {
        @NotBlank
        private String email;
        @NotBlank
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class LoginResponse {
        private String token;
        private String email;

        public LoginResponse(String token, String email) {
            this.token = token;
            this.email = email;
        }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
    }

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

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getSlug() { return slug; }
        public void setSlug(String slug) { this.slug = slug; }

        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }

        public String getExcerpt() { return excerpt; }
        public void setExcerpt(String excerpt) { this.excerpt = excerpt; }

        public String getCategory() { return category; }
        public void setCategory(String category) { this.category = category; }

        public String getTags() { return tags; }
        public void setTags(String tags) { this.tags = tags; }

        public String getCoverImage() { return coverImage; }
        public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

        public Boolean getIsPublished() { return isPublished; }
        public void setIsPublished(Boolean isPublished) { this.isPublished = isPublished; }

        public String getMetaTitle() { return metaTitle; }
        public void setMetaTitle(String metaTitle) { this.metaTitle = metaTitle; }

        public String getMetaDesc() { return metaDesc; }
        public void setMetaDesc(String metaDesc) { this.metaDesc = metaDesc; }
    }

    public static class BookRequest {
        @NotBlank
        private String title;
        private String author;
        private String description;
        private String coverImage;
        private BigDecimal price;
        @NotBlank
        private String buyLink;
        private String bookType;
        private Boolean isFeatured;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }

        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getCoverImage() { return coverImage; }
        public void setCoverImage(String coverImage) { this.coverImage = coverImage; }

        public BigDecimal getPrice() { return price; }
        public void setPrice(BigDecimal price) { this.price = price; }

        public String getBuyLink() { return buyLink; }
        public void setBuyLink(String buyLink) { this.buyLink = buyLink; }

        public String getBookType() { return bookType; }
        public void setBookType(String bookType) { this.bookType = bookType; }

        public Boolean getIsFeatured() { return isFeatured; }
        public void setIsFeatured(Boolean isFeatured) { this.isFeatured = isFeatured; }
    }

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

        public long getTotalPosts() { return totalPosts; }
        public long getPublishedPosts() { return publishedPosts; }
        public long getDraftPosts() { return draftPosts; }
        public long getTotalBooks() { return totalBooks; }
    }
}