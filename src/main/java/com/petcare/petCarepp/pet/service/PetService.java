package com.petcare.petCarepp.pet.service;

import com.petcare.petCarepp.pet.DTO.PetRequestDto;
import com.petcare.petCarepp.pet.mapper.PetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetMapper petMapper;

    public void savePet(PetRequestDto dto) {
        petMapper.insertPet(dto);
    }

    public PetRequestDto getPetById(Long petId) {
        return petMapper.selectPetById(petId);
    }

    public void updatePet(PetRequestDto dto) {
        petMapper.updatePet(dto);
    }

    public List<PetRequestDto> getPetsByUserId(Long userId) {
        return petMapper.selectPetsByUserId(userId);
    }
}

