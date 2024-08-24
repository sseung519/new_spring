package com.example.guestbook.dao;

import com.example.guestbook.model.User;

import java.util.Optional;

public interface UserDao {

    void create(User user);

    Optional<User> findById(String id);

}
