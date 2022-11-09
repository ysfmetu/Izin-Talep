package com.ysf.izin_module.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ysf.izin_module.enums.RoleEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "kullanici")
@Data
public class KullaniciEntity extends BaseEntity {

    @ApiModelProperty(notes = "kullancı adı tanımlanır,veritabanında ikinci bir username tanımlanmasına izin verilmez")
    @Column(name = "username",unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @ApiModelProperty(notes = "Personelin sistemimize ilk kayıt tarihidir.")
    @Column(name = "baslangic_tarihi")
    private LocalDate startDate;

    @ApiModelProperty(notes = "yetki tanımlaması yapılır.User,Manager ve Admin olarak 3 yetkimiz vardır.")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kullaniciEntity")
    @JsonIgnore
    private List<IzinHakedisEntity> izinHakedis=new ArrayList<>();;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "kullaniciEntity")
    @JsonIgnore
    private List<IzinTalepEntity> izinTalepEntity=new ArrayList<>();


    public KullaniciEntity(String username, LocalDate baslangicDate, String encode) {

    }
}
