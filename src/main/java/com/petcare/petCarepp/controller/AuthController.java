package com.petcare.petCarepp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "회원가입 및 로그인 관련 API")
public class AuthController {

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "아이디, 비밀번호, 이름, 프로필 이미지를 받아 회원가입을 처리합니다.")
    public ResponseEntity<?> signup(
            @RequestParam String loginId,
            @RequestParam String password,
            @RequestParam String name,
            @RequestParam(required = false) MultipartFile profileImage
    ) {
        // 테스트용 처리~
        return ResponseEntity.ok("회원가입 성공 (mock)");
    }
}
