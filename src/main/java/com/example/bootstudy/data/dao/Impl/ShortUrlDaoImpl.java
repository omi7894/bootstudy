package com.example.bootstudy.data.dao.impl;

import com.example.bootstudy.data.dao.ShortUrlDAO;
import com.example.bootstudy.data.entity.ShortUrlEntity;
import com.example.bootstudy.data.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShortUrlDaoImpl implements ShortUrlDAO {

    private final ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlDaoImpl(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository = shortUrlRepository;
    }


    @Override
    public ShortUrlEntity saveShortUrl(ShortUrlEntity shortUrlEntity) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.save(shortUrlEntity);
        return foundShortUrlEntity;
    }

    @Override
    public ShortUrlEntity getShortUrl(String originalUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
        return foundShortUrlEntity;
    }

    @Override
    public ShortUrlEntity getOriginalUrl(String shortUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
        return foundShortUrlEntity;
    }

    //의미없는 매소드
    @Override
    public ShortUrlEntity updateShortUrl(ShortUrlEntity newShortUrlEntity) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(newShortUrlEntity.getOrgUrl());

        foundShortUrlEntity.setUrl(newShortUrlEntity.getUrl());

        ShortUrlEntity savedShortUrlEntity = shortUrlRepository.save(foundShortUrlEntity);

        return savedShortUrlEntity;
    }

    @Override
    public void deleteByShortUrl(String shortUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByUrl(shortUrl);
        shortUrlRepository.delete(foundShortUrlEntity);

    }

    @Override
    public void deleteByOriginalUrl(String originalUrl) {
        ShortUrlEntity foundShortUrlEntity = shortUrlRepository.findByOrgUrl(originalUrl);
        shortUrlRepository.delete(foundShortUrlEntity);
    }
}
