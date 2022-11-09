package com.ysf.izin_module.repository;

import com.ysf.izin_module.enums.RoleEnum;
import com.ysf.izin_module.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleEnum role);
}
