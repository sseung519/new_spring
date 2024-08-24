package com.example.guestbook.dao;

import com.example.guestbook.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class JdbcTemplateUserDao implements UserDao {

    private static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users(id, username, pwd) VALUES (?, ?, ?);";

        jdbcTemplate.update(sql, user.getId(), user.getUsername(), user.getPwd());
    }

    @Override
    public Optional<User> findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?;";

        try {
            User user = jdbcTemplate.queryForObject(sql, USER_ROW_MAPPER, id);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private static class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPwd(rs.getString("pwd"));

            return user;
        }

    }

}
