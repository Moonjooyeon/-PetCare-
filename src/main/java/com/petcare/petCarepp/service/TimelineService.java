package com.petcare.petCarepp.service;

import com.petcare.petCarepp.timeline.DTO.TimelineResponse;
import com.petcare.petCarepp.timeline.repository.TimelineRepository;
import com.petcare.petCarepp.Hospital.repository.HospitalRepository;
import com.petcare.petCarepp.timeline.entity.Timeline;
import com.petcare.petCarepp.timeline.DTO.TimelineCreateRequest;
import com.petcare.petCarepp.timeline.DTO.TimelineUpdateRequest;
import com.petcare.petCarepp.Hospital.entity.Hospital;
import com.petcare.petCarepp.global.util.S3Uploader; // or 너가 정의한 경로

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimelineService {

    private final TimelineRepository timelineRepository;
    private final HospitalRepository hospitalRepository;
    private final S3Uploader s3Uploader; // 이미지 업로드 유틸


    public List<TimelineResponse> getTimelineByHospitalId(Long hospitalId) {
        List<Timeline> timelines = timelineRepository.findByHospitalId(hospitalId);

        return timelines.stream()
                .map(t -> new TimelineResponse(t.getDate(), t.getContent()))
                .collect(Collectors.toList());
    }
    public Long createTimeline(TimelineCreateRequest request) {
        Hospital hospital = hospitalRepository.findById(request.getHospitalId())
                .orElseThrow(() -> new RuntimeException("병원 없음"));

        String imageUrl = null;
        if (request.getImage() != null && !request.getImage().isEmpty()) {
            imageUrl = s3Uploader.upload(request.getImage(), "timeline"); // 또는 로컬 저장
        }

        Timeline timeline = Timeline.builder()
                .hospital(hospital)
                .title(request.getTitle())
                .content(request.getContent())
                .image(imageUrl)
                .build();

        return timelineRepository.save(timeline).getId();
    }

    public void updateTimeline(Long id, TimelineUpdateRequest request) {
        Timeline timeline = timelineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("공지 없음"));

        timeline.setTitle(request.getTitle());
        timeline.setContent(request.getContent());

        if (request.getImage() != null && !request.getImage().isEmpty()) {
            String imageUrl = s3Uploader.upload(request.getImage(), "timeline");
            timeline.setImage(imageUrl);
        }

        timelineRepository.save(timeline);
    }

    public void deleteTimeline(Long id) {
        timelineRepository.deleteById(id);
    }
}

