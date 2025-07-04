package com.petcare.petCarepp.controller;


import com.petcare.petCarepp.service.TimelineService;
import com.petcare.petCarepp.timeline.DTO.TimelineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hospitals")
public class HospitalTimelineController {

    private final TimelineService timelineService;

    @GetMapping("/{hospitalId}/timeline")
    public ResponseEntity<?> getTimeline(@PathVariable Long hospitalId) {
        List<TimelineResponse> timelines = timelineService.getTimelineByHospitalId(hospitalId);
        return ResponseEntity.ok().body(Map.of("code", 200, "result", timelines));
    }
}