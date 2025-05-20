package com.example.planner_project.repository;

import com.example.planner_project.plan.Plan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlanRepository {
    public static Map<Long, Plan> database = new HashMap<>();

    public static PlanRepository planRepository = new PlanRepository();

    public static Long incrementId = 0L;


    public PlanRepository() {
    }

    public static PlanRepository getInstance() {

        return planRepository;
    }


    private Plan save(Plan plan) {
        plan.setId(++incrementId);
        plan.setPlanDescription(plan.getPlanDescription());
        plan.setPlanName(plan.getPlanName());
        plan.setEditDate(plan.getEditDate());
        plan.setStartDate(plan.getStartDate());
        plan.setEndDate(plan.getEndDate());
        plan.setPostDate(plan.getPostDate());
        plan.setPriority(plan.getPriority());
        plan.setStatus(plan.getStatus());

        return plan;
    }

    public List<Plan> findALlPlans(){
        return new ArrayList<>(database.values());
    }


}
