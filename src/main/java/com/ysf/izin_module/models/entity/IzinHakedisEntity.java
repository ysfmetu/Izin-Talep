package com.ysf.izin_module.models.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "izin_hakedis")
@Data
public class IzinHakedisEntity extends BaseEntity{
    private int izinGunSayisi;
    private int izinCompleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "kullanici_id")
    private KullaniciEntity kullaniciEntity;
}
