package com.example.bootstudy.data.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@RedisHash(value = "shortUrl", timeToLive = 60) //key에 붙일수 있는값, 사라지는 시간
public class ShortUrlResponseDto implements Serializable {

    private static final long serialVersionUID = -214490344996507077L;

    @Id
    private String orgUrl;
    private String shortUrl;
}
