package com.example.planner_project.controller;


import com.example.planner_project.dto.*;
import com.example.planner_project.service.PlanService;
import com.example.planner_project.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/plans")
public class PlanController {

    private final PlanService planService;
    private final UserService userService;


    public PlanController(PlanService planService, UserService userService)  {
        this.planService = planService;
        this.userService = userService;
    }

    @PostMapping("/register") // USER 등록
    public ResponseEntity<UserRegisterDTO> userRegister(
            @RequestBody @Valid UserRegisterDTO registerDTO
    ) {
        System.out.println(registerDTO);
        return new ResponseEntity<>(userService.registerUser(registerDTO), HttpStatus.CREATED);
    }



    @PostMapping // PLAN 등록
    public ResponseEntity<PlanResponseDTO> createPlan(@RequestBody @Valid PlanRequestDTO requestDTO) {
        log.info("Plan 생성 시작");
        return new ResponseEntity<>(planService.savePlan(requestDTO), HttpStatus.CREATED);
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



    @GetMapping("/{userId}")
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
            @PathVariable Long id,
            @RequestBody PlanDeleteDTO pw
    ) {
        planService.deletePlan(id,pw);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
