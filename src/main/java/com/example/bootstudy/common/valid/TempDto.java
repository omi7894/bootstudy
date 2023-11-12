package com.example.bootstudy.common.valid;

import com.example.bootstudy.common.annotation.ValidationAnnotation;
import lombok.Getter;

@Getter
public class TempDto {
    @ValidationAnnotation
    private String value;
}
