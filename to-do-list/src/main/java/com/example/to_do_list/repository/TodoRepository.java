package com.example.to_do_list.repository;

import com.example.to_do_list.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByContent(String Content);
    List<Todo> findAllByOrderByIdDesc();
}
