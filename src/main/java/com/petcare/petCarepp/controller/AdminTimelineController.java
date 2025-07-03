package com.petcare.petCarepp.controller;

import com.petcare.petCarepp.service.TimelineService;
import com.petcare.petCarepp.timeline.DTO.TimelineCreateRequest;
import com.petcare.petCarepp.timeline.DTO.TimelineUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/timeline")

public class AdminTimelineController {

    private final TimelineService timelineService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@ModelAttribute TimelineCreateRequest request) {
        Long id = timelineService.createTimeline(request);
        return ResponseEntity.ok().body(Map.of("code", 200, "timelineId", id));
    }

    @PutMapping("/{timelineId}")
    public ResponseEntity<?> update(@PathVariable Long timelineId,
                                    @ModelAttribute TimelineUpdateRequest request) {
        timelineService.updateTimeline(timelineId, request);
        return ResponseEntity.ok().body(Map.of("code", 200, "result", "수정 성공"));
    }

    @DeleteMapping("/{timelineId}")
    public ResponseEntity<?> delete(@PathVariable Long timelineId) {
        timelineService.deleteTimeline(timelineId);
        return ResponseEntity.ok().body(Map.of("code", 200, "result", "삭제 성공"));
    }
}

