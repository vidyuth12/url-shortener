package com.urlshortener.controller;

import com.urlshortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestParam String url) {
        if (url == null || url.isEmpty()) {
            return ResponseEntity.badRequest().body("URL cannot be empty");
        }

        String shortUrl = urlService.shortenUrl(url);
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping("/{shortId}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortId) {
        String originalUrl = urlService.getOriginalUrl(shortId);

        if (originalUrl == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", originalUrl)
                .build();
    }
}