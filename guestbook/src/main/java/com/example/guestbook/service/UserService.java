package com.example.guestbook.service;

import com.example.guestbook.dao.UserDao;
import com.example.guestbook.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void join(User user) {
        if (validateDuplicatedUser(user)) userDao.create(user);
        else throw new IllegalArgumentException("해당 아이디는 사용중입니다.");
    }

    private boolean validateDuplicatedUser(User user) {
        return userDao.findById(user.getId()).isEmpty();
    }

    public User login(User user) {
        Optional<User> userOptional = userDao.findById(user.getId());

        if (userOptional.isPresent()) {
            checkUserInfo(user, userOptional.get());
            return userOptional.get();
        } else {
            throw new IllegalArgumentException("로그인에 실패했습니다. 아이디를 확인해주세요");
        }
    }

    private void checkUserInfo(User user, User actualUser) {
        if (user.getId() == null || user.getPwd() == null) {
            throw new IllegalArgumentException("로그인에 실패했습니다. 아이디와 비밀번호를 모두 입력해주세요.");
        }

        if (!user.getPwd().equals(actualUser.getPwd()))
            throw new IllegalArgumentException("로그인에 실패했습니다. 비밀번호를 확인해주세요.");
    }


}
