package com.ysf.izin_module.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysf.izin_module.models.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KullaniciDTO {
    private Long id;
    private String username;
    private Set<String> role;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate baslangicDate;
    private IzinHakedisDTO izinHakedisDTO;
    private String password;


}
