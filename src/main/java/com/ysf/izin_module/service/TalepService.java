package com.ysf.izin_module.service;

import com.ysf.izin_module.models.dto.TalepDTO;

import java.text.ParseException;

public interface TalepService {
    public int izinGunSayisi(TalepDTO talepDTO) throws ParseException;
    public int ToplamHizmetSuresi(TalepDTO talepDTO);
}
