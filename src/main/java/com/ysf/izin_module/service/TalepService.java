package com.ysf.izin_module.service;

import com.ysf.izin_module.models.dto.TalepDTO;

import java.text.ParseException;
import java.util.Date;

public interface TalepService {
    public int numberOfDays(TalepDTO talepDTO) throws ParseException;
}
