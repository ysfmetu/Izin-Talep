package com.ysf.izin_module.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ysf.izin_module.enums.IzinStatusEnum;
import com.ysf.izin_module.enums.RoleEnum;
import lombok.Data;

import java.util.Date;

@Data
public class TalepDTO {
    private Long id;
    private String username;
    private IzinStatusEnum durum;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date startDate;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date endDate;
}
