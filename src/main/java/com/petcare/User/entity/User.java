package com.petcare.User.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "user") // DB의 테이블명이 user라면 반드시 명시!
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "***REMOVED***_provider")
    private String ***REMOVED***Provider;

    @Column(nullable = false)
    private String name;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(nullable = false)
    private String role;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;


}

