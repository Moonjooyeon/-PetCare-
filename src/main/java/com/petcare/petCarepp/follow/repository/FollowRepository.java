package com.petcare.petCarepp.follow.repository;

import com.petcare.petCarepp.follow.Entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    List<Follow> findByUserId(Long userId);
    boolean existsByUserIdAndHospitalId(Long userId, Long hospitalId);
    void deleteByUserIdAndHospitalId(Long userId, Long hospitalId);
}
