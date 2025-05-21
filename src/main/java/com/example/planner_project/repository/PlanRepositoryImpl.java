package com.example.planner_project.repository;

import com.example.planner_project.dto.PlanRequestDTO;
import com.example.planner_project.dto.PlanResponseDTO;
import com.example.planner_project.plan.Plan;
import org.springframework.stereotype.Repository;


import java.util.*;


@Repository
public class PlanRepositoryImpl implements PlanRepository{

    private final Map<Long, Plan> planList = new HashMap<>();

    @Override
    public Plan savePlan(Plan plan) {
        Long planId = planList.isEmpty() ? 1 : Collections.max(planList.keySet()) + 1;
        plan.setId(planId);

        planList.put(planId, plan);

        return plan;

    }

    @Override
    public List<Plan> findAllPlans() {
        List<Plan> allPlans = new ArrayList<>();

        for (Plan plan : planList.values()) {
            Plan newPlan = new Plan(plan);
            allPlans.add(newPlan);
        }
        return allPlans;
    }

    @Override
    public Plan findPlanById(Long id) {
        return planList.get(id);
    }

    @Override
    public void deletePlan(Long id) {

        planList.remove(id);
    }


}
