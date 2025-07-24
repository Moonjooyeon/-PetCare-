package com.petcare.petCarepp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/kakao")
@RequiredArgsConstructor
public class KakaoProxyController {

    @Value("${kakao.rest-api-key}")
    private String kakaoApiKey;

    @GetMapping("/search")
    public ResponseEntity<?> searchByKeyword(@RequestParam String query) {
        RestTemplate restTemplate = new RestTemplate();

        String encodedQuery = UriUtils.encode("청담동 동물병원", StandardCharsets.UTF_8);

        String url = "https://dapi.kakao.com/v2/local/search/keyword.json?query=" + encodedQuery;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "KakaoAK " + kakaoApiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            return ResponseEntity.ok().body(response.getBody());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("카카오 API 호출 실패: " + e.getMessage());
        }
    }
}
