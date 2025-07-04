package com.petcare.petCarepp.timeline.repository;

import com.petcare.petCarepp.timeline.entity.Timeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimelineRepository extends JpaRepository<Timeline, Long> {
    List<Timeline> findAllByHospitalId(Long hospitalId);

    List<Timeline> findByHospitalId(Long hospitalId);
}

