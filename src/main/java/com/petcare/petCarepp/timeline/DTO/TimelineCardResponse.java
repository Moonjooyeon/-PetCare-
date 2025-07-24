package com.petcare.petCarepp.timeline.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TimelineCardResponse {
    private Long hospitalId;
    private String hospitalName;
    private String hospitalImage; // profileImage URL
    private String content;
    private String date;
}

