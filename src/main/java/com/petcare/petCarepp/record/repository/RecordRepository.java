package com.petcare.petCarepp.record.repository;

import com.petcare.petCarepp.record.Entity.MedicalRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<MedicalRecord, Long> {

    @Query("SELECT DISTINCT r.hospital.id FROM MedicalRecord r WHERE r.userId = :userId")
    List<Long> findHospitalIdsWithRecords(@Param("userId") Long userId);


    List<MedicalRecord> findByUserId(Long userId);

}
