package com.petcare.petCarepp.record.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordResponse {
    private LocalDate nextSchedule;
    private LocalDate date;
    private String symptoms;
    private String diagnosis;
    private String hospitalName;
}
