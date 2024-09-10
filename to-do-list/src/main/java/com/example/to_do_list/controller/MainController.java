package com.example.to_do_list.controller;

import com.example.to_do_list.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    ToDoService service;

    @PostMapping("/add")
    public String add(@RequestParam String todo) {
        service.addList(todo);
        return "추가 성공: " + todo;
    }

    @GetMapping("/list")
    public List<String> getList() {
        return service.getList();
    }

    @PutMapping("/edit")
    public String edit(@RequestParam String ori, String todo) {
        service.edit(ori, todo);
        return "수정 성공 " + ori + "를 " + todo + " 수정하였습니다.";
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam String todo) {
        service.delete(todo);
        return "삭제 성공: " + todo;
    }
}
