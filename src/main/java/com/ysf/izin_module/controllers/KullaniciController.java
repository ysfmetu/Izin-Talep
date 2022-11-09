package com.ysf.izin_module.controllers;

import com.ysf.izin_module.models.dto.KullaniciDTO;
import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.service.KullaniciService;
import com.ysf.izin_module.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kullanici")
@RequiredArgsConstructor
@Api(value = "Kullanıcı kayıt Rest Api", description = "Kullanıcı kayıt Servisleri")
public class KullaniciController {

    private final KullaniciService kullaniciService;

    @ApiOperation(value = "Tüm kullanıcıların listesini çeker"  )
    @GetMapping("/all")
    public ResponseEntity<List<KullaniciEntity>> getAll(){
        List<KullaniciEntity> users=kullaniciService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
