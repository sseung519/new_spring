package com.example.to_do_list.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class TodoDto {
    private Long id;
    private String content;
    // private LocalDate createdDate;

    public TodoDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
