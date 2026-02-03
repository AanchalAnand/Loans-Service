package com.eazybytes.loans.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter @Setter @ToString  @MappedSuperclass
public class BaseEntity {

    private String createdBy;

    private String updatedBy;


    private Date createdAt;

    private Date updatedAt;
}
