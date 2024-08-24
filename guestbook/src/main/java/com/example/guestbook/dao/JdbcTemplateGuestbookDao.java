package com.example.guestbook.dao;

import com.example.guestbook.model.Guestbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("jdbcTemplateGuestbookDao")
public class JdbcTemplateGuestbookDao implements GuestbookDao {

    private static final RowMapper<Guestbook> GUESTBOOK_ROW_MAPPER = new GuestbookRowMapper();
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateGuestbookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Guestbook guestbook) {
        String sql = "INSERT INTO guestbook (writer, password, content) VALUES (?, ?, ?)";
        try {
            int affectedRows = jdbcTemplate.update(sql, guestbook.getWriter(), guestbook.getPassword(), guestbook.getContent());
            if (affectedRows == 0) {
                throw new DataIntegrityViolationException("방명록 작성에 실패했습니다. 데이터베이스에 변경된 내용이 없습니다.");
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("방명록 작성 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public Optional<Guestbook> findById(int id) {
        String sql = "SELECT * FROM guestbook WHERE id = ?";
        try {
            Guestbook guestbook = jdbcTemplate.queryForObject(sql, GUESTBOOK_ROW_MAPPER, id);
            return Optional.ofNullable(guestbook);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException e) {
            throw new DataAccessException("ID에 해당하는 방명록을 찾는 중 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Guestbook> findAll() {
        String sql = "SELECT * FROM guestbook ORDER BY createdAt DESC";
        try {
            return jdbcTemplate.query(sql, GUESTBOOK_ROW_MAPPER);
        } catch (DataAccessException e) {
            throw new DataAccessException("방명록 목록을 가져오는 중 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void update(Guestbook guestbook) {
        String sql = "UPDATE guestbook SET writer = ?, content = ? WHERE id = ? AND password = ?";
        try {
            int updatedRows = jdbcTemplate.update(sql, guestbook.getWriter(), guestbook.getContent(), guestbook.getId(), guestbook.getPassword());
            if (updatedRows == 0) {
                throw new EmptyResultDataAccessException("방명록 수정에 실패했습니다. 비밀번호가 일치하지 않거나 해당 ID의 방명록이 존재하지 않습니다.", 1);
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("방명록 수정 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(int id, String password) {
        String sql = "DELETE FROM guestbook WHERE id = ? AND password = ?";
        try {
            int deletedRows = jdbcTemplate.update(sql, id, password);
            if (deletedRows == 0) {
                throw new EmptyResultDataAccessException("방명록 삭제에 실패했습니다. 비밀번호가 일치하지 않거나 해당 ID의 방명록이 존재하지 않습니다.", 1);
            } else if (deletedRows > 1) {
                throw new IncorrectResultSizeDataAccessException("예상치 못한 여러 개의 방명록이 삭제되었습니다.", 1, deletedRows);
            }
        } catch (DataAccessException e) {
            throw new DataAccessException("방명록 삭제 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    private static class GuestbookRowMapper implements RowMapper<Guestbook> {

        @Override
        public Guestbook mapRow(ResultSet rs, int rowNum) throws SQLException {
            Guestbook guestbook = new Guestbook();
            guestbook.setId(rs.getInt("id"));
            guestbook.setWriter(rs.getString("writer"));
            guestbook.setPassword(rs.getString("password"));
            guestbook.setContent(rs.getString("content"));
            guestbook.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
            java.sql.Timestamp updatedAt = rs.getTimestamp("updatedAt");
            if (updatedAt != null) {
                guestbook.setUpdatedAt(updatedAt.toLocalDateTime());
            }
            return guestbook;
        }

    }

}