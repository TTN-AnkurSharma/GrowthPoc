# Simple URL Shortener POC

This project demonstrates a small URL shortener using Java, Spring Boot,
Spring Data JPA, Lombok, and an in-memory H2 database.

## Example data

```text
shortCode  : aB72xK
originalUrl: https://example.com/products/123
createdAt  : 2026-09-10
expiresAt  : 2027-09-10
clickCount : 154
status     : ACTIVE
```

## Main flow

### Shorten a URL

1. `UrlShortenerService` validates the original URL.
2. `Base62ShortCodeGenerator` creates a six-character code.
3. A `ShortUrl` object is created with one-year validity.
4. `UrlRepository` saves the object in H2.

### Resolve a URL

1. Find the `ShortUrl` using its short code.
2. Increase its click count.
3. Return the original URL.

## Classes

- `ShortUrl` — the only domain entity.
- `ShortUrlStatus` — contains `ACTIVE` and `EXPIRED`.
- `UrlShortenerService` — contains the shorten and resolve use cases.
- `UrlRepository` — uses Spring Data JPA to access H2.
- `ShortCodeGenerator` — contract for generating codes.
- `Base62ShortCodeGenerator` — generates codes such as `aB72xK`.
- `UrlValidator` — URL validation contract.
- `HttpUrlValidator` — accepts valid HTTP and HTTPS URLs.

## Why Base62?

Base62 uses:

```text
0-9, A-Z, a-z
```

A six-character code has `62^6` possible values, so the URL stays short while
providing many combinations.

## SOLID used here

- **SRP:** each class has one responsibility.
- **OCP:** another generator can implement `ShortCodeGenerator`.
- **LSP:** any valid generator can replace `Base62ShortCodeGenerator`.
- **ISP:** interfaces contain only the methods their clients need.
- **DIP:** the service depends on interfaces instead of concrete implementations.

## H2 configuration

The database is in memory, so its data is removed when the application stops.
Hibernate creates the `short_urls` table from the `ShortUrl` entity.

Configuration is in:

```text
src/main/resources/application.properties
```

## Run

Use JDK 17 or newer:

```bash
./gradlew :url-shortener:bootRun
```

This POC intentionally has no REST controller. Call `UrlShortenerService` from a
controller or `CommandLineRunner` when you want to add an input layer.
