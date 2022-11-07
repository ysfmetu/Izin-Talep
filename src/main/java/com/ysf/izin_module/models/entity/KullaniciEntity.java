package com.ysf.izin_module.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysf.izin_module.enums.RoleEnum;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "kullanici")
@Data
public class KullaniciEntity extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "baslangic_tarihi")
    private LocalDate startDate;
    @Column(name = "role")
    private RoleEnum roleEnum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kullaniciEntity")
    @JsonIgnore
    private List<IzinHakedisEntity> izinHakedis=new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kullaniciEntity")
    @JsonIgnore
    private List<IzinTalepEntity> izinTalepEntity=new ArrayList<>();


}
