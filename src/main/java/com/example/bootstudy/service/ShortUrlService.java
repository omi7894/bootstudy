package com.example.bootstudy.service;

import com.example.bootstudy.data.dto.ShortUrlResponseDto;

public interface ShortUrlService {
    ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDto getShortUrl(String clientId, String clientSecret, String originalUrl);

    ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret, String originalUrl);

    void  deleteShortUrl(String url);

}
