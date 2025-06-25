package com.petcare.petCarepp.Hospital.repository;

import com.petcare.petCarepp.Hospital.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    Optional<Hospital> findByPlaceId(String placeId);
}
