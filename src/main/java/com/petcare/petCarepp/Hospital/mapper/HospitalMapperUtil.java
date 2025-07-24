package com.petcare.petCarepp.Hospital.mapper;

import com.petcare.petCarepp.Hospital.DTO.HospitalDto;
import com.petcare.petCarepp.Hospital.entity.Hospital;

public class HospitalMapperUtil {

    public static Hospital toEntity(HospitalDto dto) {
        return Hospital.builder()
                .name(dto.getPlaceName())  // dto 필드명은 placeName이지만 실제 엔티티에는 name
                .address(dto.getAddressName())
                .specialties("기본값")  // dto에는 없는 필드. 임시 값 지정
                .description("등록된 병원입니다.")
                .lat(0.0)  // dto에는 없으니 일단 기본값, 또는 나중에 입력
                .lng(0.0)
                .placeId("임시")  // 마찬가지
                .profileImage(null)
                .isMock(false)
                .registeredBy(1L) // TODO: 추후 로그인 유저 ID로 대체
                .build();
    }
}
