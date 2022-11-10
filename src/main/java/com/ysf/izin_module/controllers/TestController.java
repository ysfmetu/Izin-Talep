package com.ysf.izin_module.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
@Api(value = "TEST API", description = "yetki bazlı test işlemlerinin yapıldığı , web ortamında testlerin yapıldığı katman")
public class TestController {

  @GetMapping("/all")
  @ApiOperation(value = "kimlik doğrulama olmaksızın sisteme ulaşabilir."  )
  public String allAccess() {
    return "Serbest giris.";
  }

  @GetMapping("/user")
  @ApiOperation(value = "User/manager/admin yetkisine sahip kullanıcıların giriş yapabilidiği katman"  )
  @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
  public String userAccess() {
    return "User Sayfasi.";
  }

  @GetMapping("/manager")
  @PreAuthorize("hasRole('MANAGER')")
  @ApiOperation(value = "manager yetkisine sahip kullanıcıların giriş yapabilidiği katman"  )
  public String moderatorAccess() {
    return "Manager Sayfasi.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  @ApiOperation(value = "admin yetkisine sahip kullanıcıların giriş yapabilidiği katman"  )
  public String adminAccess() {
    return "Admin Sayfasi.";
  }
}
