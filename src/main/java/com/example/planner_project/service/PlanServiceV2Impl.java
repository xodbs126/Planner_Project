package com.example.planner_project.service;

import com.example.planner_project.dto.PlanRequestDTO_V2;
import com.example.planner_project.dto.PlanResponseDTOV2;
import com.example.planner_project.entity.PlanV2;
import com.example.planner_project.entity.UserV2;
import com.example.planner_project.exception.PasswordMismatchException;
import com.example.planner_project.exception.PlanNotFoundException;
import com.example.planner_project.repository.PlanRepositoryV2;
import com.example.planner_project.repository.UserRepositoryV2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceV2Impl implements PlanServiceV2 {

    private final UserRepositoryV2 userRepository;
    private final PlanRepositoryV2 planRepository;

    public PlanServiceV2Impl(UserRepositoryV2 userRepositoryV2, PlanRepositoryV2 planRepositoryV2) {
        this.userRepository = userRepositoryV2;
        this.planRepository = planRepositoryV2;
    }

    @Override
    public PlanResponseDTOV2 savePlan(PlanRequestDTO_V2 requestDTO) {


        UserV2 user = userRepository.findUserById(requestDTO.getUserId());
        if (user == null || !requestDTO.getUserId().equals(user.getId())) {
            throw new PlanNotFoundException("사용자를 찾을 수 없습니다.");
        }

        System.out.println(requestDTO.getUserId());
        PlanV2 plan = new PlanV2();
        plan.setContent(requestDTO.getContent());
        plan.setUserId(user.getId());
        plan.setCreatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        plan.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        planRepository.savePlan(plan);
        return new PlanResponseDTOV2(plan);
    }


    @Override
    public List<PlanResponseDTOV2> findPlanById(Long userId) {
        UserV2 user = userRepository.findUserById(userId);
        if (user == null) {
            throw new PlanNotFoundException("사용자 ID " + userId + " 에 해당하는 사용자가 없습니다.");
        }

        List<PlanV2> allPlans = planRepository.findAllPlans();
        List<PlanResponseDTOV2> plans = new ArrayList<>();

        for (PlanV2 plan : allPlans) {
            if (user.getId().equals(plan.getUserId())) {
                plans.add(new PlanResponseDTOV2(plan));
            }
        }

        plans.sort((a, b) -> b.getEditedAt().compareTo(a.getEditedAt()));
        return plans;
    }

    @Override
    public PlanResponseDTOV2 updatePlan(Long id, String content, String userName, String password) {
        UserV2 user = userRepository.findUserById(id);
        if (user == null) {
            throw new PlanNotFoundException("사용자 ID " + id + " 에 해당하는 사용자가 없습니다.");
        }

        if (!user.getPw().equals(password)) {
            throw new PasswordMismatchException();
        }

        user.setUserName(userName);
        PlanV2 plan = planRepository.findPlanById(id);
        if (plan == null) {
            throw new PlanNotFoundException("일정 ID " + id + " 에 해당하는 일정이 없습니다.");
        }

        plan.setContent(content);
        plan.setUpdatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        userRepository.updateUser(user);

        return new PlanResponseDTOV2(plan);
    }

    @Override
    public void deletePlan(Long id, String pw) {
        UserV2 user = userRepository.findUserById(id);
        if (user == null) {
            throw new PlanNotFoundException("사용자 ID " + id + " 에 해당하는 사용자가 없습니다.");
        }

        if (!user.getPw().equals(pw)) {
            throw new PasswordMismatchException();
        }

        planRepository.deletePlan(id);
    }

    @Override
    public Page<PlanResponseDTOV2> findPlanByWriterAndEditAtV2(String writer, LocalDateTime editAtDateTime, int page, int size) {
        UserV2 user = userRepository.findUserByWriter(writer);
        if (user == null) {
            throw new PlanNotFoundException("작성자 정보를 찾을 수 없습니다.");
        }

        List<PlanV2> allPlans = planRepository.findAllPlans();

        List<PlanResponseDTOV2> filtered = allPlans.stream()
                .filter(plan -> plan.getUserId().equals(user.getId()))
                .filter(plan -> editAtDateTime == null || plan.getUpdatedAt().toLocalDate().equals(editAtDateTime.toLocalDate()))
                .sorted((a, b) -> b.getUpdatedAt().compareTo(a.getUpdatedAt()))
                .map(PlanResponseDTOV2::new)
                .toList();

        int start = page * size;
        int end = Math.min(start + size, filtered.size());
        List<PlanResponseDTOV2> pageContent = (start >= filtered.size()) ? List.of() : filtered.subList(start, end);

        return new PageImpl<>(pageContent, PageRequest.of(page, size), filtered.size());
    }

}
