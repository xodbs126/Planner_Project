package com.example.planner_project.repository;

import com.example.planner_project.entity.PlanV2;

import java.util.List;

public interface PlanRepositoryV2 {
    void savePlan(PlanV2 plan);

    List<PlanV2> findAllPlans();

    List<PlanV2> findPlanByUserId(Long userId);

    PlanV2 findPlanById(Long id);

    void deletePlan(Long id);
}
