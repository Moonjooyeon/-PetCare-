package com.petcare.petCarepp.service;

import com.petcare.petCarepp.Hospital.entity.Hospital;
import com.petcare.petCarepp.Hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public Hospital registerHospital(String placeId, String name, String address, Double lat, Double lng,
                                     String specialties, String description, MultipartFile profileImage, boolean isMock) {

        if (hospitalRepository.findByPlaceId(placeId).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 병원입니다.");
        }

        // 이미지 저장 로직은 생략하고 null 처리 (나중에 추가)
        Hospital hospital = Hospital.builder()
                .placeId(placeId)
                .name(name)
                .address(address)
                .lat(lat)
                .lng(lng)
                .specialties(specialties)
                .description(description)
                .profileImage(null)
                .isMock(isMock)
                .build();

        return hospitalRepository.save(hospital);
    }
}
