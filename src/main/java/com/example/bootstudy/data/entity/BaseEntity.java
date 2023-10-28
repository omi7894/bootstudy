package com.example.bootstudy.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false) //처음에만 update되도록
    private LocalDateTime createdAt;

    /*
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    */

    @LastModifiedDate
    private LocalDateTime updatedAt;

    /*
    @LastModifiedBy
    private String updatedBy;
    */

}
