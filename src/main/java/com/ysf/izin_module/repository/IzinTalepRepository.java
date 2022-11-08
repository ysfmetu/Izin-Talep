package com.ysf.izin_module.repository;

import com.ysf.izin_module.models.entity.IzinTalepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IzinTalepRepository extends JpaRepository<IzinTalepEntity,Long> {
    IzinTalepEntity findByUsername(String username);
}
