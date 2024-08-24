package com.example.guestbook.dao;

import com.example.guestbook.model.Guestbook;

import java.util.List;
import java.util.Optional;

public interface GuestbookDao {

    void create(Guestbook guestbook);

    Optional<Guestbook> findById(int id);

    List<Guestbook> findAll();

    void update(Guestbook guestbook);

    void delete(int id, String password);

}