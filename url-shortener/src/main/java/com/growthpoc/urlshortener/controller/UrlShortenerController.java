package com.growthpoc.urlshortener.controller;

import com.growthpoc.urlshortener.dto.ShortenUrlRequest;
import com.growthpoc.urlshortener.dto.ShortenUrlResponse;
import com.growthpoc.urlshortener.service.UrlShortenerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/urls")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(
            UrlShortenerService urlShortenerService
    ) {
        this.urlShortenerService = urlShortenerService;
    }

    @PostMapping
    public ResponseEntity<ShortenUrlResponse> shortenUrl(
            @Valid @RequestBody ShortenUrlRequest request
    ) {

        String shortCode =
                urlShortenerService.shortenUrl(
                        request.originalUrl()
                );

        String shortUrl =
                "http://localhost:8080/api/v1/urls/" + shortCode;

        return ResponseEntity.ok(
                new ShortenUrlResponse(shortUrl)
        );
    }

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(
            @PathVariable String shortCode
    ) {

        String originalUrl =
                urlShortenerService.getOriginalUrl(
                        shortCode
                );

        return ResponseEntity
                .status(302)
                .header(
                        "Location",
                        originalUrl
                )
                .build();
    }
}
