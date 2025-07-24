package com.petcare.petCarepp.service;

import com.petcare.petCarepp.Hospital.entity.Hospital;
import com.petcare.petCarepp.Hospital.mapper.HospitalMapper;
import com.petcare.petCarepp.file.FileService;
import com.petcare.petCarepp.pet.mapper.PetMapper;
import com.petcare.petCarepp.record.Entity.MedicalRecord;
import com.petcare.petCarepp.record.dto.RecordRequest;
import com.petcare.petCarepp.record.dto.RecordResponse;
import com.petcare.petCarepp.record.mapper.RecordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final PetMapper petMapper;
    private final RecordMapper recordMapper;
    private final HospitalMapper hospitalMapper;
    private final FileService fileService;

    public void createRecord(RecordRequest dto, MultipartFile photo, Long userId) {
        String imageUrl = null;

        // ✅ 반려동물 ID 조회
        Long petId = dto.getPetId();
        if (petId == null) {
            petId = petMapper.findFirstPetIdByUser(userId);
        }

        // ✅ 이미지 저장
        if (photo != null && !photo.isEmpty()) {
            imageUrl = fileService.save(photo);
        }

        // ✅ 병원 placeId로 병원 조회 or 등록
        String placeId = dto.getPlaceId();
        Hospital hospital = hospitalMapper.findByPlaceId(placeId);

        log.info("🧩 placeId = {}", placeId);
        log.info("🧩 hospitalName = {}", dto.getHospitalName());

        if (hospital == null) {
            hospitalMapper.insert(Hospital.builder()
                    .placeId(placeId)
                    .name(dto.getHospitalName())
                    .address("주소 미지정")
                    .lat(0.0)
                    .lng(0.0)
                    .specialties("기본값")
                    .description("카카오 동기화 병원")
                    .profileImage(null)
                    .isMock(false)
                    .registeredBy(userId)
                    .build());

            hospital = hospitalMapper.findByPlaceId(placeId); // 다시 조회
        }

        MedicalRecord record = MedicalRecord.builder()
                .userId(userId)
                .petId(petId)
                .hospital(Hospital.builder().id(hospital.getId()).build()) // 이 부분 중요
                .date(dto.getDate())
                .symptoms(dto.getSymptoms())
                .diagnosis(dto.getDiagnosis())
                .nextSchedule(dto.getNextSchedule())
                .memo(dto.getMemo())
                .imageUrl(imageUrl)
                .placeId(dto.getPlaceId())
                .hospitalName(dto.getHospitalName())
                .build();

        recordMapper.insertRecord(record); // ✅ parameterType="MedicalRecord"니까 이걸 넘겨야 함

    }

    public List<RecordResponse> getRecordsByUserId(Long userId) {
        return recordMapper.findByUserId(userId);
    }
}
