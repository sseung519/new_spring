package com.example.guestbook.service;

import com.example.guestbook.dao.GuestbookDao;
import com.example.guestbook.model.Guestbook;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestbookService {

    private final GuestbookDao guestbookDao;

    public GuestbookService(@Qualifier("jdbcTemplateGuestbookDao") GuestbookDao guestbookDao) {
        this.guestbookDao = guestbookDao;
    }

    public void addGuestbook(Guestbook guestbook) {
        validateInput(guestbook);

        guestbookDao.create(guestbook);
    }

    // TODO Optional 처리
    public Optional<Guestbook> getGuestbook(int id) {
        return guestbookDao.findById(id);
    }

    public List<Guestbook> getAllGuestbooks() {
        return guestbookDao.findAll();
    }

    public void updateGuestbook(Guestbook guestbook) {
        if (guestbook.getId() <= 0) {
            throw new IllegalArgumentException("유효하지 않은 ID입니다.");
        }
        validateInput(guestbook);

        guestbookDao.update(guestbook);
    }

    public void deleteGuestbook(int id, String password) {
        if (id <= 0) {
            throw new IllegalArgumentException("유효하지 않은 ID입니다.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }

        guestbookDao.delete(id, password);
    }

    private void validateInput(Guestbook guestbook) {
        if (guestbook.getWriter() == null || guestbook.getWriter().trim().isEmpty()) {
            throw new IllegalArgumentException("작성자 이름은 필수입니다.");
        }
        if (guestbook.getPassword() == null || guestbook.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호는 필수입니다.");
        }
        if (guestbook.getContent() == null || guestbook.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("내용은 필수입니다.");
        }
    }

}