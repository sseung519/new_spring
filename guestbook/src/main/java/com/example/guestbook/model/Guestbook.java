package com.example.guestbook.model;

// import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// @Setter
// @Getter
// @ToString
// @NoArgsConstructor
// @AllArgsConstructor
public class Guestbook {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int id;
    private String writer;
    private String password; // TODO 로그인 기능 추가 이후 제거
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getFormattedCreatedAt() {
        return formatDateTime(createdAt);
    }

    public String getFormattedUpdatedAt() {
        return formatDateTime(updatedAt);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime != null ? dateTime.format(FORMATTER) : "";
    }

    public Guestbook() {
    }

    public Guestbook(int id, String writer, String password, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.writer = writer;
        this.password = password;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}