package com.ysf.izin_module.repository;

import com.ysf.izin_module.models.entity.KullaniciEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KullaniciRepository extends JpaRepository<KullaniciEntity,Long> {
}
