package com.growthpoc.urlshortener.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String shortCode;

    @Column(nullable = false, length = 2048)
    String originalUrl;

    @Column(nullable = false)
    LocalDateTime createdAt;
    
    LocalDateTime expiresAt;

    public boolean isExpired() {
        return expiresAt != null
                && LocalDateTime.now().isAfter(expiresAt);
    }
    protected UrlMapping() {
    }

    public UrlMapping(
            String originalUrl,
            LocalDateTime createdAt,
            LocalDateTime expiresAt
    ) {
        this.originalUrl = originalUrl;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }
}
