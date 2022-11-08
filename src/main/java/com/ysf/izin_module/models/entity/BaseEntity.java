package com.ysf.izin_module.models.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class BaseEntity {
    @ApiModelProperty(notes = "Database tarafından üretilen Id adresidir.")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

