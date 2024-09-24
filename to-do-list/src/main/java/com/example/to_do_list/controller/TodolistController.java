package com.example.to_do_list.controller;

import com.example.to_do_list.dto.TodoDto;
import com.example.to_do_list.entity.Todo;
import com.example.to_do_list.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") //WebConfig에 전역으로 설정함
@RequestMapping("/api/to-do")
public class TodolistController {
    @Autowired
    ToDoService service;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody TodoDto todo) {
        service.save(todo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getList() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> edit(@PathVariable("id") Long id,
                                       @RequestBody TodoDto todo) {
        service.update(id, todo);
        return ResponseEntity.ok("수정성공");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok("삭제성공");
    }
}
