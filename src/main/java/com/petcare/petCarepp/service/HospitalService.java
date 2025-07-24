package com.petcare.petCarepp.service;

import com.petcare.User.Repository.UserRepository;
import com.petcare.User.entity.User;
import com.petcare.petCarepp.Hospital.DTO.HospitalDto;
import com.petcare.petCarepp.Hospital.entity.Hospital;
import com.petcare.petCarepp.Hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    // ✅ [1] 기존 등록: @RequestParam 방식 등록에 사용됨
    public Hospital registerHospital(String placeId, String name, String address, Double lat, Double lng,
                                     String specialties, String description, MultipartFile profileImage, boolean isMock, String registeredByEmail) {

        if (hospitalRepository.findByPlaceId(placeId).isPresent()) {
            throw new IllegalArgumentException("이미 등록된 병원입니다.");
        }

        User user = userRepository.findByEmail(registeredByEmail)
                .orElseThrow(() -> new IllegalArgumentException("등록자 이메일로 사용자를 찾을 수 없습니다."));

        Hospital hospital = Hospital.builder()
                .placeId(placeId)
                .name(name)
                .address(address)
                .lat(lat)
                .lng(lng)
                .specialties(specialties != null ? specialties : "기본값")
                .description(description != null ? description : "등록된 병원입니다.")
                .profileImage(null) // 이미지 처리 추후 구현
                .registeredBy(user.getId())
                .isMock(isMock)
                .createdAt(new Timestamp(System.currentTimeMillis()))
                .updatedAt(new Timestamp(System.currentTimeMillis()))
                .build();

        return hospitalRepository.save(hospital);
    }

    // ✅ [2] 카카오 연동 등 JSON 기반 병원 등록에 사용됨
    public Hospital registerOrGetHospital(HospitalDto dto, Long userId, boolean isMock) {
        return hospitalRepository.findByPlaceId(dto.getPlaceId())
                .orElseGet(() -> {
                    Hospital h = Hospital.builder()
                            .placeId(dto.getPlaceId())
                            .name(dto.getPlaceName())
                            .address(dto.getAddressName())
                            .lat(dto.getLat())
                            .lng(dto.getLng())
                            .specialties("기본값")
                            .description("등록된 병원입니다.")
                            .profileImage(null)
                            .registeredBy(userId)
                            .isMock(isMock)
                            .createdAt(new Timestamp(System.currentTimeMillis()))
                            .updatedAt(new Timestamp(System.currentTimeMillis()))
                            .build();
                    return hospitalRepository.save(h);
                });
    }
}
