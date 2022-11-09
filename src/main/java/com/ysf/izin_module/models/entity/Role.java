package com.ysf.izin_module.models.entity;

import com.ysf.izin_module.enums.RoleEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
@Data
public class Role  extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RoleEnum name;
}
