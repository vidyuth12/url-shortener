package com.urlshortener.service;

import com.urlshortener.model.UrlMapping;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UrlService {
    private final Map<String, UrlMapping> urlMap = new HashMap<>();

    public String shortenUrl(String originalUrl) {
        // Generate a unique short ID (using UUID for simplicity)
        String shortId = UUID.randomUUID().toString().substring(0, 8);
        String shortUrl = "http://short.url/" + shortId;

        // Store the mapping
        urlMap.put(shortId, new UrlMapping(originalUrl, shortUrl));

        return shortUrl;
    }

    public String getOriginalUrl(String shortId) {
        UrlMapping urlMapping = urlMap.get(shortId);
        return (urlMapping != null) ? urlMapping.getOriginalUrl() : null;
    }
}