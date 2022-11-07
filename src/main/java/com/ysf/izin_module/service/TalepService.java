package com.ysf.izin_module.service;

import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.models.entity.IzinTalepEntity;
import com.ysf.izin_module.utils.Result;

import java.text.ParseException;

public interface TalepService {
    public int izinGunSayisi(TalepDTO talepDTO) throws ParseException;
    public int ToplamHizmetSuresi(TalepDTO talepDTO);
    public Result<IzinTalepEntity> saveIzin(TalepDTO talepDTO) throws ParseException;
}
