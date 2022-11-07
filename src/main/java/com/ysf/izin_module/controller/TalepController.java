package com.ysf.izin_module.controller;

import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.models.entity.IzinTalepEntity;
import com.ysf.izin_module.service.TalepService;
import com.ysf.izin_module.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("talep")
@RequiredArgsConstructor
public class TalepController {
    private final TalepService talepService;

    @GetMapping("/days")
    public void numberOfDays(@RequestBody TalepDTO talepDTO) throws ParseException {
    talepService.izinGunSayisi(talepDTO);
    }
    @PostMapping("/years")
    public void numberOfYears(@RequestBody TalepDTO talepDTO) throws ParseException {
        talepService.ToplamHizmetSuresi(talepDTO);
    }
    @PostMapping("/kayit")
    public Result<IzinTalepEntity> addIzin(@RequestBody TalepDTO talepDTO) throws ParseException {
        return talepService.saveIzin(talepDTO);
    }

}
