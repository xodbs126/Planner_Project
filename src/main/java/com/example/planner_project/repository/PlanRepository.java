package com.example.planner_project.repository;



import com.example.planner_project.entity.Plan;

import java.util.List;

public interface PlanRepository {

    Plan savePlan(Plan plan);

    List<Plan> findAllPlans();

    Plan findPlanById(Long id);

    void deletePlan(Long id);
}
