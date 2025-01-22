package com.urlshortener.controller;

import com.urlshortener.service.UrlService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UrlControllerTest {
    @Mock
    private UrlService urlService;

    @InjectMocks
    private UrlController urlController;

    @Test
    public void testShortenUrl() {
        String originalUrl = "https://example.com";
        String shortUrl = "http://short.url/abc123";

        when(urlService.shortenUrl(originalUrl)).thenReturn(shortUrl);

        ResponseEntity<String> response = urlController.shortenUrl(originalUrl);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shortUrl, response.getBody());
    }

    @Test
    public void testRedirectToOriginal() {
        String shortId = "abc123";
        String originalUrl = "https://example.com";

        when(urlService.getOriginalUrl(shortId)).thenReturn(originalUrl);

        ResponseEntity<Void> response = urlController.redirectToOriginal(shortId);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

    @Test
    public void testRedirectToOriginalNotFound() {
        String shortId = "invalidId";

        when(urlService.getOriginalUrl(shortId)).thenReturn(null);

        ResponseEntity<Void> response = urlController.redirectToOriginal(shortId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}