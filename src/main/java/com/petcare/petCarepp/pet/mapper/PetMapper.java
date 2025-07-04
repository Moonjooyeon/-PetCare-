package com.petcare.petCarepp.pet.mapper;

import com.petcare.petCarepp.pet.DTO.PetRequestDto;
 import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    void insertPet(PetRequestDto dto);

    PetRequestDto selectPetById(Long petId);
    void updatePet(PetRequestDto dto);
}
