package com.petcare.petCarepp.Hospital.entity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "place_id")
    private String placeId;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(nullable = false)
    private Double lng;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String specialties;

    @Column(nullable = false)
    private String description;

    @Column(name = "registered_by", nullable = false)
    private Long registeredBy;

    @Column(name = "is_mock", nullable = false)
    private Boolean isMock = false;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}
