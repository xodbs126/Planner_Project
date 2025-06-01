package com.example.planner_project.repository;

import com.example.planner_project.entity.User;
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
        String userName = user.getUserName();
        String pw = user.getPw();
        String email = user.getEmail();

        String sql = "INSERT INTO users (user_name, pw, email, created_at, edited_at) " +
                "VALUES (?, ?, ?, NOW(), NOW())";
        System.out.println("User 저장");
        jdbcTemplate.update(sql, userName, pw, email);
    }

    @Override
    public Long findIdByName(String userName) {
        String sql = "SELECT id FROM users WHERE user_name = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, userName);
    }

    private static class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("user_name"));
            user.setPw(rs.getString("pw"));
            user.setEmail(rs.getString("email"));
            user.setPlanId(rs.getLong("plan_id"));
            user.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
            user.setEdited_at(rs.getTimestamp("edited_at").toLocalDateTime());
            return user;
        }
    }
}
