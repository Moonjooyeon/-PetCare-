package com.petcare.petCarepp.timeline.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

//등록용
@Getter
@Setter
public class TimelineCreateRequest {
    private Long hospitalId;
    private String title;
    private String content;
    private MultipartFile image;
}
