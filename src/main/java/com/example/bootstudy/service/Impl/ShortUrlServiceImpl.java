package com.example.bootstudy.service.impl;

import com.example.bootstudy.data.dao.ShortUrlDAO;
import com.example.bootstudy.data.dto.NaverUriDto;
import com.example.bootstudy.data.dto.ShortUrlResponseDto;
import com.example.bootstudy.data.entity.ShortUrlEntity;
import com.example.bootstudy.service.ShortUrlService;
import net.bytebuddy.utility.nullability.NeverNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    private final Logger LOGGER = LoggerFactory.getLogger(ShortUrlServiceImpl.class);
    private final ShortUrlDAO shortUrlDAO;

    @Autowired
    public ShortUrlServiceImpl(ShortUrlDAO shortUrlDAO) {
        this.shortUrlDAO = shortUrlDAO;
    }

    @Override
    public ShortUrlResponseDto generateShortUrl(String clientId, String clientSecret, String originalUrl) {

        LOGGER.info("[generateShortUrl] request data : {}", originalUrl);

        ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

        String orgUrl = responseEntity.getBody().getResult().getOrgUrl();
        String shortUrl = responseEntity.getBody().getResult().getUrl();
        String hash = responseEntity.getBody().getResult().getHash();

        ShortUrlEntity shortUrlEntity = new ShortUrlEntity();
        shortUrlEntity.setOrgUrl(orgUrl);
        shortUrlEntity.setUrl(shortUrl);
        shortUrlEntity.setHash(hash);

        shortUrlDAO.saveShortUrl(shortUrlEntity);

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);
        LOGGER.info("[generateShortUrl] Response DTO : {}", shortUrlResponseDto.toString());

        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto getShortUrl(String clientId, String clientSecret, String originalUrl) {

        LOGGER.info("[getShortUrl] request data : {}", originalUrl);
        ShortUrlEntity getShortUrlEntity = shortUrlDAO.getShortUrl(originalUrl);

        String orgUrl;
        String shortUrl;

        if(getShortUrlEntity == null){
            LOGGER.info("[getShortUrl] No Entity in Database.");
            ResponseEntity<NaverUriDto> responseEntity = requestShortUrl(clientId, clientSecret, originalUrl);

            orgUrl = responseEntity.getBody().getResult().getOrgUrl();
            shortUrl = responseEntity.getBody().getResult().getUrl();

        }else{
            orgUrl = getShortUrlEntity.getOrgUrl();
            shortUrl = getShortUrlEntity.getUrl();
        }

        ShortUrlResponseDto shortUrlResponseDto = new ShortUrlResponseDto(orgUrl, shortUrl);

        LOGGER.info("[getShortUrl] Response DTO : {}", shortUrlResponseDto.toString());
        return shortUrlResponseDto;
    }

    @Override
    public ShortUrlResponseDto updateShortUrl(String clientId, String clientSecret, String originalUrl) {
        return null;
    }

    @Override
    public void deleteShortUrl(String url) {
        if(url.contains("me2.do")){
            LOGGER.info("[deleteShortUrl] Request Url is 'ShortUrl'.");
            deleteByShortUrl(url);
        }else{
            LOGGER.info("[deleteShortUrl] Request Url is 'OriginalUrl'.");
            deleteByOriginalUrl(url);
        }

    }

    private void deleteByShortUrl(String url) {
        LOGGER.info("[deleteByShortUrl] delete record");
        shortUrlDAO.deleteByShortUrl(url);
    }

    private void deleteByOriginalUrl(String url) {
        LOGGER.info("[deleteByOriginalUrl] delete record");
        shortUrlDAO.deleteByOriginalUrl(url);
    }

    private ResponseEntity<NaverUriDto> requestShortUrl(String clientId, String clientSecret, String originalUrl) {

        LOGGER.info("[requestShortUrl] client ID : {}, client Secret : {}, original URL : {}", clientId, clientSecret, originalUrl);

        //URI객체 사용
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/util/shorturl") //세부주소
                .queryParam("url", originalUrl)
                .encode()
                .build()
                .toUri();

        LOGGER.info("[requestShortUrl] set HTTP Request Header");
        //HttpHeaders 객체사용
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>("", headers); //body와 header를 조합해주는 객체
                                                                        // body엔 따로 뭐 안한다
        RestTemplate restTemplate = new RestTemplate();

        LOGGER.info("[requestShortUrl] request by restTemplate");
        ResponseEntity<NaverUriDto> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,
                entity, NaverUriDto.class); // NaverUriDto 형태로 결과를 받는다

        LOGGER.info("[requestShortUrl] request has been successfully complete.");

        return responseEntity;
    }

}
