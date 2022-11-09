package com.ysf.izin_module.repository;

import com.ysf.izin_module.models.entity.KullaniciEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<KullaniciEntity,Long> {
    KullaniciEntity findByUsername(String username);
    Boolean existsByUsername(String username);
}
