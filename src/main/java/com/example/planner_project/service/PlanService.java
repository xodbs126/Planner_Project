package com.example.planner_project.service;

import com.example.planner_project.dto.PlanRequestDTO;
import com.example.planner_project.dto.PlanResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanService {
    PlanResponseDTO savePlan(PlanRequestDTO requestDTO);


    List<PlanResponseDTO> findPlanByWriterAndEditAt(String writer, LocalDateTime editAtDateTime);

    PlanResponseDTO findPlanById(Long id);

    PlanResponseDTO updatePlan(Long id, String content, String writer,String password);

    void deletePlan(Long id);
}
