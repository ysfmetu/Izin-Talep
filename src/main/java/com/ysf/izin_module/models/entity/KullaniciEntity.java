package com.ysf.izin_module.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysf.izin_module.enums.RoleEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "kullanici")
@Data
public class KullaniciEntity extends BaseEntity {
    @ApiModelProperty(notes = "kullancı adı tanımlanır")
    @Column(name = "username")
    private String username;

    @ApiModelProperty(notes = "Personel ilk kayıt tarihidir.")
    @Column(name = "baslangic_tarihi")
    private LocalDate startDate;

    @ApiModelProperty(notes = "yetki tanımlaması yapılır.User veya Manager olarak iki yetkimiz vardır.")
    @Column(name = "role")
    private RoleEnum roleEnum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kullaniciEntity")
    @JsonIgnore
    private List<IzinHakedisEntity> izinHakedis=new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kullaniciEntity")
    @JsonIgnore
    private List<IzinTalepEntity> izinTalepEntity=new ArrayList<>();


}
