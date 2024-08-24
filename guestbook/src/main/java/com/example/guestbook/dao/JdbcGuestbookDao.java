package com.example.guestbook.dao;

import com.example.guestbook.model.Guestbook;
import conn.DBConnection;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("jdbcGuestbookDao")
public class JdbcGuestbookDao implements GuestbookDao {

    @Override
    public void create(Guestbook guestbook) {
        String sql = "INSERT INTO guestbook (writer, password, content) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, guestbook.getWriter());
            pstmt.setString(2, guestbook.getPassword());
            pstmt.setString(3, guestbook.getContent());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new DataIntegrityViolationException("방명록 작성에 실패했습니다. 데이터베이스에 변경된 내용이 없습니다.");
            }
        } catch (SQLException e) {
            throw new DataAccessException("방명록 작성 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public Optional<Guestbook> findById(int id) {
        String sql = "SELECT * FROM guestbook WHERE id = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(extractGuestbookFromResultSet(rs));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("ID에 해당하는 방명록을 찾는 중 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public List<Guestbook> findAll() {
        List<Guestbook> guestbooks = new ArrayList<>();
        String sql = "SELECT * FROM guestbook ORDER BY createdAt DESC";
        try (Connection conn = DBConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                guestbooks.add(extractGuestbookFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException("방명록 목록을 가져오는 중 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
        return guestbooks;
    }

    @Override
    public void update(Guestbook guestbook) {
        String sql = "UPDATE guestbook SET writer = ?, content = ? WHERE id = ? AND password = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, guestbook.getWriter());
            pstmt.setString(2, guestbook.getContent());
            pstmt.setInt(3, guestbook.getId());
            pstmt.setString(4, guestbook.getPassword());
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new EmptyResultDataAccessException("방명록 수정에 실패했습니다. 비밀번호가 일치하지 않거나 해당 ID의 방명록이 존재하지 않습니다.", 1);
            }
        } catch (SQLException e) {
            throw new DataAccessException("방명록 수정 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    @Override
    public void delete(int id, String password) {
        String sql = "DELETE FROM guestbook WHERE id = ? AND password = ?";
        try (Connection conn = DBConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, password);
            int affectedRows = pstmt.executeUpdate();

            if (affectedRows == 0) {
                throw new EmptyResultDataAccessException("방명록 삭제에 실패했습니다. 비밀번호가 일치하지 않거나 해당 ID의 방명록이 존재하지 않습니다.", 1);
            } else if (affectedRows > 1) {
                throw new IncorrectResultSizeDataAccessException("예상치 못한 여러 개의 방명록이 삭제되었습니다.", 1, affectedRows);
            }
        } catch (SQLException e) {
            throw new DataAccessException("방명록 삭제 중 데이터베이스 오류가 발생했습니다: " + e.getMessage(), e) {
            };
        }
    }

    private Guestbook extractGuestbookFromResultSet(ResultSet rs) throws SQLException {
        Guestbook guestbook = new Guestbook();
        guestbook.setId(rs.getInt("id"));
        guestbook.setWriter(rs.getString("writer"));
        guestbook.setPassword(rs.getString("password"));
        guestbook.setContent(rs.getString("content"));
        guestbook.setCreatedAt(rs.getTimestamp("createdAt").toLocalDateTime());
        Timestamp updatedAt = rs.getTimestamp("updatedAt");
        if (updatedAt != null) {
            guestbook.setUpdatedAt(updatedAt.toLocalDateTime());
        }
        return guestbook;
    }

}