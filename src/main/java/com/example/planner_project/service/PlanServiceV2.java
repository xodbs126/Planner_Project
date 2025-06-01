package com.example.planner_project.service;

import com.example.planner_project.dto.PlanDeleteDTO;
import com.example.planner_project.dto.PlanRequestDTO_V2;
import com.example.planner_project.dto.PlanResponseDTOV2;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface PlanServiceV2 {
    PlanResponseDTOV2 savePlan(@Valid PlanRequestDTO_V2 requestDTO);

    List<PlanResponseDTOV2> findPlanById(Long userId);

    PlanResponseDTOV2 updatePlan(Long id, String content, String userName, String password);

    void deletePlan(Long id, String pw);

    Page<PlanResponseDTOV2> findPlanByWriterAndEditAtV2(String writer, LocalDateTime editAtDateTime, int page, int size);
}
