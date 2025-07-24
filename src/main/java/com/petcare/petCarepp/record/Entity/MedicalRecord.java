package com.petcare.petCarepp.record.Entity;

import com.petcare.petCarepp.Hospital.entity.Hospital;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long petId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital; // ✅ hospitalId -> hospital 객체로 변경

    private LocalDate date;
    private String symptoms;
    private String diagnosis;
    private LocalDate nextSchedule;
    private String memo;
    private String imageUrl;
    private String placeId;
    private String hospitalName;

    public Long getHospitalId() {
        return hospital != null ? hospital.getId() : null;
    }

}
