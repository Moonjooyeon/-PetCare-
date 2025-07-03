package com.petcare.petCarepp.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@RequiredArgsConstructor
public class S3Uploader {

    public String upload(MultipartFile file, String dirName) {
        // 실제 S3 대신, 가짜 URL 반환 - 테스트용
        return "https://placehold.co/300x200?text=" + file.getOriginalFilename();
    }
}

