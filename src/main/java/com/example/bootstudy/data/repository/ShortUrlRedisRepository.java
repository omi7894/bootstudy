package com.example.bootstudy.data.repository;

import com.example.bootstudy.data.dto.ShortUrlResponseDto;
import org.springframework.data.repository.CrudRepository;

public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDto, String> {
}
