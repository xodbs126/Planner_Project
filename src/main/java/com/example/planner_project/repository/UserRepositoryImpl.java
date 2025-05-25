package com.example.planner_project.repository;


import com.example.planner_project.user.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, new UserRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void saveUser(User user) {
        String user_id = user.getUserName();
        String password = user.getPw();
        String email = user.getEmail();
        String sql = "INSERT INTO users (user_id, password, email, created_at, edited_at) VALUES (?, ?, ?, NOW(), NOW())";

        jdbcTemplate.update(sql, user_id, password, email);
    }

    @Override
    public Long findIdByName(String writer) {
        String sql = "SELECT id FROM users WHERE writer = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, writer);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("username"));
            user.setPw(rs.getString("pw"));
            return user;
        }
    }
}
