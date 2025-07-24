package com.petcare.petCarepp.controller;

import com.petcare.petCarepp.Hospital.DTO.HospitalDto;
import com.petcare.petCarepp.Hospital.entity.Hospital;
import com.petcare.petCarepp.Hospital.repository.HospitalRepository;
import com.petcare.petCarepp.auth.CustomOAuth2User;
import com.petcare.petCarepp.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hospitals")
@Tag(name = "Hospital", description = "병원 관련 API")
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalService hospitalService;
    private final HospitalRepository hospitalRepository;

    @PostMapping
    @Operation(summary = "병원 등록", description = "외부 placeId 기반으로 병원을 등록합니다.")
    public ResponseEntity<?> registerHospital(
            @RequestParam String placeId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam Double lat,
            @RequestParam Double lng,
            @RequestParam(required = false) String specialties,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) MultipartFile profileImage,
            @RequestParam boolean isMock,
            @AuthenticationPrincipal CustomOAuth2User user
    ) {
        String email = user.getEmail();

        Hospital hospital = hospitalService.registerHospital(
                placeId, name, address, lat, lng, specialties, description,
                profileImage, isMock, email
        );
        return ResponseEntity.ok("병원 등록 성공");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllHospitals() {
        List<Hospital> hospitals = hospitalRepository.findAll();
        return ResponseEntity.ok(Map.of("result", hospitals));
    }

    @PostMapping("/save-or-get")
    @Operation(summary = "병원 저장 또는 조회", description = "기존 병원이 있으면 반환, 없으면 등록 후 반환")
    public ResponseEntity<?> saveOrGetHospital(
            @RequestBody HospitalDto dto,
            @AuthenticationPrincipal CustomOAuth2User user
    ) {
        Long userId = user.getId();
        Hospital hospital = hospitalService.registerOrGetHospital(dto, userId, false);
        return ResponseEntity.ok(hospital);
    }

    @PostMapping("/sync")
    @Operation(summary = "병원 동기화", description = "카카오 API에서 가져온 병원을 DB에 등록하거나 기존 병원 리턴")
    public ResponseEntity<?> syncHospital(
            @RequestBody HospitalDto dto,
            @AuthenticationPrincipal CustomOAuth2User user
    ) {
        Long userId = user.getId();
        Hospital hospital = hospitalService.registerOrGetHospital(dto, userId, false);
        return ResponseEntity.ok(hospital);
    }
}
