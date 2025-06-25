package com.petcare.petCarepp.controller;

import com.petcare.petCarepp.service.HospitalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/hospitals")
@Tag(name = "Hospital", description = "병원 관련 API")
@RequiredArgsConstructor // ✅ 생성자 자동 생성해서 주입해줌
public class HospitalController {

    private final HospitalService hospitalService;

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
            @RequestParam boolean isMock
    ) {
        hospitalService.registerHospital(placeId, name, address, lat, lng, specialties, description, profileImage, isMock);
        return ResponseEntity.ok("병원 등록 성공");
    }
}
