package com.ysf.izin_module.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "izin_hakedis")
@Data
public class IzinHakedisEntity extends BaseEntity{
    private int izinGunSayisi;
    private int izinCompleted;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id")
    @JsonIgnore
    private KullaniciEntity kullaniciEntity;
}
