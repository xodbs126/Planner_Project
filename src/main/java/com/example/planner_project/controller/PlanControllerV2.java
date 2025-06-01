package com.example.planner_project.controller;


import com.example.planner_project.dto.*;
import com.example.planner_project.service.PlanServiceV2;
import com.example.planner_project.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
public class PlanControllerV2 {

    private final PlanServiceV2 planServiceV2;
    private final UserService userService;

    public PlanControllerV2(PlanServiceV2 planServiceV2, UserService userService) {
        this.planServiceV2 = planServiceV2;
        this.userService = userService;
    }

    @PostMapping("/register") // USER 등록
    public ResponseEntity<UserRegisterDTO> userRegister(
            @RequestBody @Valid UserRegisterDTO registerDTO
    ) {
        System.out.println(registerDTO);
        return new ResponseEntity<>(userService.registerUser(registerDTO), HttpStatus.CREATED);
    }

    @PostMapping("/v2")
    public ResponseEntity<PlanResponseDTOV2> createPlanV2(
            @RequestBody @Valid PlanRequestDTO_V2 requestDTO) {
        log.info("PlanV2 생성 시작");
        return new ResponseEntity<>(planServiceV2.savePlan(requestDTO), HttpStatus.CREATED);
    }



    @GetMapping("/view/v2")
    public ResponseEntity<Page<PlanResponseDTOV2>> findPlanByWriterAndEditAtV2(
            @RequestParam(required = false) String writer,
            @RequestParam(required = false) String editAt,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        LocalDateTime editAtDateTime = (editAt != null) ? LocalDateTime.parse(editAt) : null;
        Page<PlanResponseDTOV2> result = planServiceV2.findPlanByWriterAndEditAtV2(writer, editAtDateTime, page - 1, size);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/v2/{userId}")
    public ResponseEntity<List<PlanResponseDTOV2>> findPlanByIdV2(
            @PathVariable Long userId
    ) {
        List<PlanResponseDTOV2> result = planServiceV2.findPlanById(userId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/v2/edit/{id}")
    public ResponseEntity<PlanResponseDTOV2> updatePlanV2(
            @PathVariable Long id,
            @RequestBody PlanUpdateDtoV2 requestDTO) {
        return new ResponseEntity<>(planServiceV2.updatePlan(id, requestDTO.getContent(), requestDTO.getUserName(), requestDTO.getPassword()), HttpStatus.OK);
    }

    @DeleteMapping("/v2/del/{id}")
    public ResponseEntity<Void> deletePlanV2(
            @PathVariable Long id,
            @RequestBody String pw) {
        planServiceV2.deletePlan(id, pw);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
