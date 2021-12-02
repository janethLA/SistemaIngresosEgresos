package com.dev.SistemaIngresosEgresos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SistemaIngresosEgresos.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

}
