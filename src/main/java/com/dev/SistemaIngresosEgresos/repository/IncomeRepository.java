package com.dev.SistemaIngresosEgresos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SistemaIngresosEgresos.entity.Income;

@Repository
public interface IncomeRepository  extends JpaRepository<Income,Long> {

}
