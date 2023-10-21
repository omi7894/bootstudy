package com.example.bootstudy.service;

import com.example.bootstudy.data.dto.MemberDTO;
import org.springframework.http.ResponseEntity;

public interface RestTemplateService {

    public String getAroundHub();

    public String getName();

    public String getName2();

    public ResponseEntity<MemberDTO> postDto();

    public ResponseEntity<MemberDTO> addHeader();

}
