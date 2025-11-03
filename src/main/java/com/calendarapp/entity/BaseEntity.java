package com.calendarapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //자식 클래스에게 매필 정보를 전달
@EntityListeners(AuditingEntityListener.class) //
public abstract class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    public LocalDateTime createdAt;

    @LastModifiedDate
    private  LocalDateTime modifiedAt;
}
