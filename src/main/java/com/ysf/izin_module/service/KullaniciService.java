package com.ysf.izin_module.service;

import com.ysf.izin_module.models.dto.KullaniciDTO;
import com.ysf.izin_module.models.entity.KullaniciEntity;
import com.ysf.izin_module.utils.Result;

import java.util.List;

public interface KullaniciService {
    Result<KullaniciEntity> add(KullaniciEntity kullaniciEntity);
    List<KullaniciEntity> getAll();
}
