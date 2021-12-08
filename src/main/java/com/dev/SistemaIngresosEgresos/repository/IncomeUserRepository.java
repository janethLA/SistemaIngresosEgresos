package com.dev.SistemaIngresosEgresos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SistemaIngresosEgresos.entity.IncomeUser;

@Repository
public interface IncomeUserRepository  extends JpaRepository<IncomeUser,Long> {

}
