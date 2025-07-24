// HospitalMapper.java
package com.petcare.petCarepp.Hospital.mapper;

import com.petcare.petCarepp.Hospital.DTO.HospitalDto;
import com.petcare.petCarepp.Hospital.entity.Hospital;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HospitalMapper {

    // 병원 placeId로 찾기 (이미 있음)
    Hospital findByPlaceId(@Param("placeId") String placeId);

    // 병원 id로 찾기 (추가해야 함)
    Hospital findById(@Param("id") Long id);

    // 병원 등록
    void insert(Hospital hospital);
}

