package com.example.planner_project.service;

import com.example.planner_project.dto.PlanRequestDTO;
import com.example.planner_project.dto.PlanResponseDTO;
import com.example.planner_project.plan.Plan;
import com.example.planner_project.repository.PlanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {

    private final PlanRepository planRepository;


    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public PlanResponseDTO savePlan(PlanRequestDTO requestDTO) {

        Plan plan = new Plan(requestDTO.getId(), requestDTO.getContent(), requestDTO.getWriter(), requestDTO.getPassword());

        Plan savePlan = planRepository.savePlan(plan);

        PlanResponseDTO plans = new PlanResponseDTO(plan);

        return new PlanResponseDTO(plan);

    }

    @Override
    public List<PlanResponseDTO> findPlanByWriterAndEditAt(String writer, LocalDateTime editAtDateTime) {
        List<Plan> allPlans = planRepository.findAllPlans();
        List<PlanResponseDTO> plans = new ArrayList<>();

        for (Plan plan : allPlans) {
            boolean writerMatch = (writer == null || writer.equals(plan.getWriter()));
            boolean editAtMatch = (editAtDateTime == null || editAtDateTime.toLocalDate().equals(plan.getUpdatedAt().toLocalDate()));


            if (writerMatch && editAtMatch) {
                plans.add(new PlanResponseDTO(plan));
            }
        }
        plans.sort((a, b) -> b.getEditedAt().compareTo(a.getEditedAt()));
        return plans;
    }

    @Override
    public PlanResponseDTO findPlanById(Long id) {
        Plan plan = planRepository.findPlanById(id);

    if (plan == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, id+"는 DB에 저장되어있지 않습니다.");
    }
        return new PlanResponseDTO(plan);
    }

    @Override
    public PlanResponseDTO updatePlan(Long id, String content, String writer,String password) {
        Plan plan = planRepository.findPlanById(id);

        if (plan == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, id+"는 DB에 저장되어있지 않습니다.");
        else if(!plan.getPassword().equals(password))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "잘못된 비밀번호 입니다.");
        plan.setWriter(writer);
        plan.setContent(content);
        plan.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.DAYS));

        return new PlanResponseDTO(plan);
    }

    @Override
    public void deletePlan(Long id) {
        Plan plan = planRepository.findPlanById(id);

        if(plan==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, id + "는 DB에 저장되어있지 않습니다.");

        planRepository.deletePlan(id);
    }

}
