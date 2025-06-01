package com.example.planner_project.repository;


import com.example.planner_project.entity.UserV2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryV2Impl implements UserRepositoryV2 {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryV2Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserV2 findUserById(Long id) {

        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.query(sql, new UserRepositoryV2Impl.UserRowMapper(), id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserV2 findUserByWriter(String writer) {
        String sql = "SELECT * FROM users WHERE user_name =?";
        return jdbcTemplate.query(sql, new UserRepositoryV2Impl.UserRowMapper(), writer)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(UserV2 user) {
        String userName = user.getUserName();
        String pw = user.getPw();
        String email = user.getEmail();

        String sql = "INSERT INTO users (user_name, pw, email, created_at, edited_at) " +
                "VALUES (?, ?, ?, NOW(), NOW())";
        System.out.println("User 저장");
        jdbcTemplate.update(sql, userName, pw, email);
    }

    @Override
    public void updateUser(UserV2 user) {
        String userName = user.getUserName();
        Long userId = user.getId();

        String sql = "UPDATE users SET user_name = ?, edited_at = NOW() WHERE id = ?";
        System.out.println("User 이름 수정");
        jdbcTemplate.update(sql, userName, userId);
    }


    private static class UserRowMapper implements RowMapper<UserV2> {
        @Override
        public UserV2 mapRow(ResultSet rs, int rowNum) throws SQLException {
            UserV2 user = new UserV2();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setPw(rs.getString("pw"));
            user.setEmail(rs.getString("email"));
            user.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
            user.setEdited_at(rs.getTimestamp("edited_at").toLocalDateTime());
            return user;
        }
    }
}
