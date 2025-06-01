package com.example.planner_project.repository;

import com.example.planner_project.entity.PlanV2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlanRepositoryV2Impl implements PlanRepositoryV2 {
    private final JdbcTemplate jdbcTemplate;

    public PlanRepositoryV2Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void savePlan(PlanV2 plan) {

        System.out.println("plan sql");
        String sql = "INSERT INTO plans (content, user_id, created_at, updated_at) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                plan.getContent(),
                plan.getUserId(),
                plan.getCreatedAt(),
                plan.getUpdatedAt());

        Long generatedId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Long.class);
        plan.setId(generatedId);
    }

    @Override
    public List<PlanV2> findAllPlans() {
        String sql = "SELECT * FROM plans";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PlanV2 plan = new PlanV2();
            plan.setId(rs.getLong("id"));
            plan.setContent(rs.getString("content"));
            plan.setUserId(rs.getLong("user_id"));
            plan.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            plan.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return plan;
        });
    }

    @Override
    public List<PlanV2> findPlanByUserId(Long userId) {
        String sql = "SELECT * FROM plans WHERE user_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PlanV2 plan = new PlanV2();
            plan.setId(rs.getLong("id"));
            plan.setContent(rs.getString("content"));
            plan.setUserId(rs.getLong("user_id"));
            plan.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            plan.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return plan;
        }, userId);
    }

    @Override
    public PlanV2 findPlanById(Long id) {
        String sql = "SELECT * FROM plans WHERE id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PlanV2 plan = new PlanV2();
            plan.setId(rs.getLong("id"));
            plan.setContent(rs.getString("content"));
            plan.setUserId(rs.getLong("user_id"));
            plan.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            plan.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return plan;
        }, id).stream().findFirst().orElse(null);
    }

    @Override
    public void deletePlan(Long id) {
        String sql = "DELETE FROM plans WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
