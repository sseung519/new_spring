package com.example.to_do_list.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoRepository {
    private List<String> toDoList = new ArrayList<>();
    //추가
    public void add(String todo) {
        toDoList.add(todo);
    }

    //조회
    public List<String> getToDoList() {
        return toDoList;
    }

    //수정
    public void edit(String ori, String todo) {
        int index = toDoList.indexOf(ori);
        if(index != -1) {
            toDoList.set(index, todo);
        } else {
            throw new RuntimeException("수정할 항목이 존재하지 않아요!");
        }
    }

    //삭제
    public void delete(String todo) {
        toDoList.remove(todo);
    }
}
