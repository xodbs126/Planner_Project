package com.example.planner_project.controller;


import com.example.planner_project.dto.PlanRequestDTO;
import com.example.planner_project.dto.PlanResponseDTO;
import com.example.planner_project.plan.Plan;
import com.example.planner_project.service.PlanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/plans")
public class PlanController {

    private final Map<Long, Plan> planList = new HashMap<>();
    private final PlanService planService;


    public PlanController(PlanService planService) {
        this.planService = planService;
    }


    @PostMapping
    public ResponseEntity<PlanResponseDTO> createPlan(@RequestBody PlanRequestDTO requestDTO) {
        return new ResponseEntity<>(planService.savePlan(requestDTO), HttpStatus.OK);
    }

    @GetMapping("/view")
    public ResponseEntity<List<PlanResponseDTO>> findPlanByWriterAndEditAt(
            @RequestParam(required = false) String writer,
            @RequestParam(required = false) String editAt
    ) {
        LocalDateTime editAtDateTime = (editAt != null) ? LocalDateTime.parse(editAt) : null;
        List<PlanResponseDTO> result = planService.findPlanByWriterAndEditAt(writer, editAtDateTime);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{userId}")
    public ResponseEntity<PlanResponseDTO> findPlanById(
            @PathVariable Long userId) {
        return new ResponseEntity<>(planService.findPlanById(userId), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PlanResponseDTO> updatePlan(
            @PathVariable Long id,
            @RequestBody PlanRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(planService.updatePlan(id, requestDTO.getContent(), requestDTO.getWriter(), requestDTO.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlan(
            @PathVariable Long id
    ) {
        planService.deletePlan(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
