package com.ysf.izin_module.controller;

import com.ysf.izin_module.models.dto.TalepDTO;
import com.ysf.izin_module.service.TalepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("talep")
@RequiredArgsConstructor
public class TalepController {
    private final TalepService talepService;

    @GetMapping("/days")
    public void numberOfDays(@RequestBody TalepDTO talepDTO) throws ParseException {
    talepService.numberOfDays(talepDTO);
    }

}
