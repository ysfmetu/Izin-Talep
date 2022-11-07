package com.ysf.izin_module.repository;

import com.ysf.izin_module.models.entity.IzinHakedisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IzinHakedisRepository extends JpaRepository<IzinHakedisEntity,Long> {
    IzinHakedisEntity findByKullaniciEntity_Username(String username);
}
