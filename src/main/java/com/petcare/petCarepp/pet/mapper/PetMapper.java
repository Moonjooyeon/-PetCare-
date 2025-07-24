package com.petcare.petCarepp.pet.mapper;

import com.petcare.petCarepp.pet.DTO.PetRequestDto;
 import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PetMapper {
    void insertPet(PetRequestDto dto);

    PetRequestDto selectPetById(Long petId);
    void updatePet(PetRequestDto dto);
    @Select("SELECT petid FROM pet WHERE user_id = #{userId} LIMIT 1")
    Long findFirstPetIdByUser(@Param("userId") Long userId);

    List<PetRequestDto> selectPetsByUserId(Long userId);
}
