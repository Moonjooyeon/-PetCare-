package com.petcare.petCarepp.controller;

import com.petcare.petCarepp.file.FileService;
import com.petcare.petCarepp.pet.DTO.PetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.petcare.petCarepp.pet.service.PetService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;
    private final FileService fileService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> registerPet(
            @RequestPart("dto") PetRequestDto dto,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) {
        String savedPath = null;
        if (file != null && !file.isEmpty()) {
            savedPath = fileService.save(file);
        }
        dto.setProfileImage(savedPath);

        petService.savePet(dto);

        return ResponseEntity.ok(Map.of("code", 200, "message", "펫 등록 완료"));
    }

    // ✅ 펫 상세 조회
    @GetMapping("/{petId}")
    public ResponseEntity<?> getPetDetail(@PathVariable Long petId) {
        PetRequestDto pet = petService.getPetById(petId);
        return ResponseEntity.ok(Map.of("code", 200, "result", pet));
    }

    // ✅ 펫 수정 (multipart/form-data: dto + optional file)
    @PutMapping(value = "/{petId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePet(@PathVariable Long petId,
                                       @RequestPart("dto") PetRequestDto dto,
                                       @RequestPart(value = "file", required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String newPath = fileService.save(file);
            dto.setProfileImage(newPath);
        }

        dto.setId(petId); // pathVariable과 dto 연동
        petService.updatePet(dto);

        return ResponseEntity.ok(Map.of("code", 200, "message", "펫 정보 수정 완료"));
    }


}