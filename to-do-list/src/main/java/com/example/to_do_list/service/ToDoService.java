package com.example.to_do_list.service;

import com.example.to_do_list.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ToDoService {
    @Autowired
    ToDoRepository toDoRepository;

    public void addList(String todo) {
        toDoRepository.add(todo);
    }

    public List<String> getList(){
       return toDoRepository.getToDoList();
    }

    public void edit(String ori, String todo) {
        toDoRepository.edit(ori, todo);
    }

    public void delete(String todo) {
        toDoRepository.delete(todo);
    }
}
