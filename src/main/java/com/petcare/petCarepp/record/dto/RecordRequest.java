package com.petcare.petCarepp.record.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Data
public class RecordRequest {
    private Long userId;
    private String placeId;
    private String hospitalName;
    private Long petId;
    private Long hospitalId;
    private LocalDate date;
    private String symptoms;
    private String imageUrl;
    private String diagnosis;
    private LocalDate nextSchedule;
    private String memo;
}
