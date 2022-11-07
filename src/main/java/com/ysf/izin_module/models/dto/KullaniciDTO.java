package com.ysf.izin_module.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysf.izin_module.enums.RoleEnum;
import lombok.Data;

import java.util.Date;

@Data
public class KullaniciDTO {
    private Long id;
    private String username;
    private RoleEnum role;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date baslangicDate;
}
