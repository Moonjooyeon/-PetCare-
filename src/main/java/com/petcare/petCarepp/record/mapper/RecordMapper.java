package com.petcare.petCarepp.record.mapper;

import com.petcare.petCarepp.record.Entity.MedicalRecord;
import com.petcare.petCarepp.record.dto.RecordResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    void insertRecord(MedicalRecord record);

    List<RecordResponse> findByUserId(Long userId);

}

