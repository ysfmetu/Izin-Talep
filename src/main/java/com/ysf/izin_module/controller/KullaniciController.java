package com.ysf.izin_module.controller;

import com.ysf.izin_module.models.dto.KullaniciDTO;
import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.service.KullaniciService;
import com.ysf.izin_module.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kullanici")
@RequiredArgsConstructor
public class KullaniciController {
    private final KullaniciService kullaniciService;

    @PostMapping("/add")
    public Result<KullaniciEntity> add(@RequestBody KullaniciDTO kullaniciDTO){
        return kullaniciService.add(kullaniciDTO);

    }
}
