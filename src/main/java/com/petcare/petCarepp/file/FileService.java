package com.petcare.petCarepp.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    // application.yml 또는 properties에서 설정
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String save(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("업로드된 파일이 비어 있습니다.");
        }

        // 파일 이름 중복 방지
        String originalFilename = file.getOriginalFilename();
        String ext = originalFilename.substring(originalFilename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString();
        String savedFileName = uuid + ext;

        // 저장 디렉토리 경로 생성
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File dest = new File(dir, savedFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }

        // DB에 저장할 경로 리턴 (ex: /uploads/uuid.jpg)
        return "/uploads/" + savedFileName;
    }
}
