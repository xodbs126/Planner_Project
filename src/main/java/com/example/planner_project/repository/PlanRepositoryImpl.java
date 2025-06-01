package com.example.planner_project.repository;

import com.example.planner_project.entity.Plan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PlanRepositoryImpl implements PlanRepository {

    private final JdbcTemplate jdbcTemplate;

    public PlanRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Plan savePlan(Plan plan) {
        String sql = "INSERT INTO plan (content, writer, password, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                plan.getContent(),
                plan.getWriter(),
                plan.getPassword(),
                plan.getCreatedAt(),
                plan.getUpdatedAt());

        Long generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        plan.setId(generatedId);
        return plan;
    }

    @Override
    public List<Plan> findAllPlans() {
        String sql = "SELECT * FROM plan";
        return jdbcTemplate.query(sql, new PlanRowMapper());
    }

    @Override
    public Plan findPlanById(Long id) {
        String sql = "SELECT * FROM plan WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new PlanRowMapper(), id);
    }

    @Override
    public void deletePlan(Long id) {
        String sql = "DELETE FROM plan WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private static class PlanRowMapper implements RowMapper<Plan> {
        @Override
        public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
            Plan plan = new Plan();
            plan.setId(rs.getLong("id"));
            plan.setContent(rs.getString("content"));
            plan.setWriter(rs.getString("writer"));
            plan.setPassword(rs.getString("password"));
            plan.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            plan.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return plan;
        }
    }
}
