package com.example.bootstudy.data.dao;

import com.example.bootstudy.data.entity.ShortUrlEntity;

public interface ShortUrlDAO {
    ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrlEntity);

    ShortUrlEntity getShortUrl(String originalUrl);

    ShortUrlEntity getOriginalUrl(String shortUrl);

    ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrlEntity);

    void deleteByShortUrl(String shortUrl);

    void deleteByOriginalUrl(String originalUrl);
}
