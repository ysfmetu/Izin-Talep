package com.ysf.izin_module.models.entity;

import com.ysf.izin_module.enums.IzinStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Entity(name = "izin_talep")
@Data
public class IzinTalepEntity extends BaseEntity {

    private String username;
    private LocalDate izinBaslangicTarihi;
    private LocalDate izinBitisTarihi;
    private IzinStatusEnum durum;
    private int izinGunSayisi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "kullanici_id")
    private KullaniciEntity kullaniciEntity;

}
