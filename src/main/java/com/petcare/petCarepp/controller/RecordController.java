package com.petcare.petCarepp.controller;

import com.petcare.petCarepp.auth.CustomOAuth2User;
import com.petcare.petCarepp.record.dto.RecordRequest;
import com.petcare.petCarepp.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService recordService;

    @PostMapping
    public ResponseEntity<?> createRecord(
            @RequestPart("record") RecordRequest recordRequest,
            @RequestPart(value = "photo", required = false) MultipartFile photo,
            @AuthenticationPrincipal CustomOAuth2User user
    ) {

        recordService.createRecord(recordRequest, photo, user.getId());
        return ResponseEntity.ok().body(Map.of("code", 200, "msg", "진료기록 등록 완료"));
    }

    @GetMapping
    public ResponseEntity<?> getRecordsByUser(
            @AuthenticationPrincipal CustomOAuth2User user
    ) {
        var records = recordService.getRecordsByUserId(user.getId());
        return ResponseEntity.ok(Map.of("code", 200, "result", records));
    }

}

