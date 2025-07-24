package com.petcare.petCarepp.follow.service;

import com.petcare.petCarepp.Hospital.entity.Hospital;
import com.petcare.petCarepp.Hospital.repository.HospitalRepository;
import com.petcare.petCarepp.follow.Entity.Follow;
import com.petcare.petCarepp.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final HospitalRepository hospitalRepository;

    public void followHospital(Long userId, Long hospitalId) {
        if (!followRepository.existsByUserIdAndHospitalId(userId, hospitalId)) {

            // 🔥 반드시 실제 DB에서 병원 가져와야 외래 키 만족함
            Hospital hospital = hospitalRepository.findById(hospitalId)
                    .orElseThrow(() -> new IllegalArgumentException("해당 병원이 존재하지 않습니다."));

            Follow follow = Follow.builder()
                    .userId(userId)
                    .hospital(hospital) // ✅ 여기 중요
                    .build();

            followRepository.save(follow);
        }
    }

    public void unfollowHospital(Long userId, Long hospitalId) {
        followRepository.deleteByUserIdAndHospitalId(userId, hospitalId);
    }

    public List<Long> getFollowedHospitalIds(Long userId) {
        return followRepository.findByUserId(userId).stream()
                .map(follow -> follow.getHospital().getId())
                .toList();
    }
}
