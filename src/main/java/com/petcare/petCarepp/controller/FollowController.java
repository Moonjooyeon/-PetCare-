package com.petcare.petCarepp.controller;

import com.petcare.petCarepp.Hospital.entity.Hospital;
import com.petcare.petCarepp.Hospital.repository.HospitalRepository;
import com.petcare.petCarepp.auth.CustomOAuth2User;
import com.petcare.petCarepp.follow.dto.FollowRequest;
import com.petcare.petCarepp.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final HospitalRepository hospitalRepository;

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<?> followHospital(@RequestBody FollowRequest dto, @AuthenticationPrincipal CustomOAuth2User user) {

        log.info("✅ userId = {}", user.getId());
        log.info("✅ hospitalId = {}", dto.getHospitalId());
        log.info("🔥 팔로우 성공: userId={}, hospitalId={}", user.getId(), dto.getHospitalId());

        followService.followHospital(user.getId(), dto.getHospitalId());

        System.out.println("🔥 팔로우 성공: userId=" + user.getId() + ", hospitalId=" + dto.getHospitalId());

        return ResponseEntity.ok(Map.of("code", 200, "result", "팔로우 완료"));
    }





    @PostMapping("/{hospitalId}")
    public ResponseEntity<?> follow(@AuthenticationPrincipal CustomOAuth2User user,
                                    @PathVariable Long hospitalId) {
        followService.followHospital(user.getId(), hospitalId);
        return ResponseEntity.ok(Map.of("code", 200, "message", "팔로우 성공"));
    }

    @DeleteMapping("/{hospitalId}")
    public ResponseEntity<?> unfollow(@AuthenticationPrincipal CustomOAuth2User user,
                                      @PathVariable Long hospitalId) {
        followService.unfollowHospital(user.getId(), hospitalId);
        return ResponseEntity.ok(Map.of("code", 200, "message", "언팔로우 성공"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getFollowedHospitals(@AuthenticationPrincipal CustomOAuth2User user) {
        var hospitalIds = followService.getFollowedHospitalIds(user.getId());
        return ResponseEntity.ok(Map.of("code", 200, "result", hospitalIds));
    }
}
