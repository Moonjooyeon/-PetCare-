package com.petcare.petCarepp.pet.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
public class PetRequestDto {
    private Long id;
    private Long userId;
    private String profileImage;
    private String type;
    private String name;
    private String breed;
    private String gender;
    private String memo;
    private LocalDate birthdate;
    private Boolean neutered;

}

