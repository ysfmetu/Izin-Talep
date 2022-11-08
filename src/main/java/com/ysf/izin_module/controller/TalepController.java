package com.ysf.izin_module.controller;

import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.models.entity.IzinTalepEntity;
import com.ysf.izin_module.service.TalepService;
import com.ysf.izin_module.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("talep")
@RequiredArgsConstructor
@Api(value = "Talep Kontroller", description = "izin talep ve onay işlemleri")
public class TalepController {
    private final TalepService talepService;

    @GetMapping("/days")
    @ApiOperation(value = "Kullanıcının girmiş olduğu tarihler arasında, resmi tarihler ve haftasonlarını izinden saymayarak geçerli gün sayısını bulmaya yarar "  )
    public void numberOfDays(@RequestBody TalepDTO talepDTO) throws ParseException {
    talepService.izinGunSayisi(talepDTO);
    }

    @PostMapping("/years")
    @ApiOperation(value = "Kullanıcı izin formunu doldururken kullanıcı adını alıp işe giriş tarihine göre izin hakediş tablosunu güncelleyip yıllık hakettiği izin değerini almamıza yarar, "  )
    public void numberOfYears(@RequestBody TalepDTO talepDTO) throws ParseException {
        talepService.ToplamHizmetSuresi(talepDTO);
    }
    @PostMapping("/kayit")
    @ApiOperation(value = "kullanıcı izin için değerler girdikten sonra kayıt işlemini yapar , onay durumunu <<beklemede>> diye belirler "  )
    public Result<IzinTalepEntity> addIzin(@RequestBody TalepDTO talepDTO) throws ParseException {
        return talepService.saveIzin(talepDTO);
    }
    @PostMapping("/update")
    @ApiOperation(value = "Yönetici rolü ile giriş yapıldıktan sonra izin durum alanını <<onaylandı>> veya <<red>> olarak seçerek izin onaylanmış olur "  )
    public Result<IzinTalepEntity> updateIzin(@RequestBody TalepDTO talepDTO) throws ParseException {
        return talepService.updateTalep(talepDTO);
    }




}
