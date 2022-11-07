package com.ysf.izin_module.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysf.izin_module.enums.IzinStatusEnum;
import com.ysf.izin_module.enums.RoleEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class TalepDTO {
    private Long id;
    private String username;
    private IzinStatusEnum durum;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate endDate;
}
