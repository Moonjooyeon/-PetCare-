package com.petcare.petCarepp.timeline.DTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
@Getter
@Setter
public class TimelineUpdateRequest {
    private String title;
    private String content;
    private MultipartFile image;
}
