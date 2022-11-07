package com.ysf.izin_module.controller;

import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.service.TalepService;
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

}
