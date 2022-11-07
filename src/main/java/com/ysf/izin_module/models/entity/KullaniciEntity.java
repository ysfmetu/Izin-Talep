package com.ysf.izin_module.models.entity;

import com.ysf.izin_module.enums.RoleEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "kullanici")
@Data
public class KullaniciEntity extends BaseEntity {

    @Column(name = "username")
    private String username;

    @Column(name = "baslangic_tarihi")
    private Date startDate;
    @Column(name = "role")
    private RoleEnum roleEnum;

    @OneToOne(mappedBy = "kullaniciEntity")
    private IzinHakedisEntity izinHakedis;

    @OneToMany(mappedBy = "kullaniciEntity")
    private List<IzinTalepEntity> izinTalepEntity=new ArrayList<>();


}
