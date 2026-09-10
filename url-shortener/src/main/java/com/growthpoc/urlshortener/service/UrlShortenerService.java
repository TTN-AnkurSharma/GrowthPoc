package com.growthpoc.urlshortener.service;

import com.growthpoc.urlshortener.entity.UrlMapping;
import com.growthpoc.urlshortener.generator.Base62Generator;
import com.growthpoc.urlshortener.repository.UrlRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UrlShortenerService {

    private final UrlRepository urlRepository;
    private final Base62Generator base62Generator;

    public UrlShortenerService(
            UrlRepository urlRepository,
            Base62Generator base62Generator
    ) {
        this.urlRepository = urlRepository;
        this.base62Generator = base62Generator;
    }

    @Transactional
    public String shortenUrl(String originalUrl) {

        validateUrl(originalUrl);

        UrlMapping mapping =
                new UrlMapping(
                        originalUrl,
                        LocalDateTime.now(),
                        null
                );

        mapping = urlRepository.save(mapping);

        Long id = mapping.getId();
        String shortCode =
                base62Generator.encode(id);

        mapping.setShortCode(shortCode);

        urlRepository.save(mapping);

        return shortCode;
    }

    @Transactional(readOnly = true)
    public String getOriginalUrl(String shortCode) {

        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Short URL not found"
                                )
                        );

        if (mapping.isExpired()) {
            log.error("No short url found");
            return "Invalid url";
        }

        return mapping.getOriginalUrl();
    }

    private void validateUrl(String url) {

        if (url == null || url.isBlank()) {

            throw new IllegalArgumentException(
                    "URL cannot be empty"
            );
        }
    }
}