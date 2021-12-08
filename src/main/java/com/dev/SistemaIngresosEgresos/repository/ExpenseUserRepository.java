package com.dev.SistemaIngresosEgresos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SistemaIngresosEgresos.entity.ExpenseUser;

@Repository
public interface ExpenseUserRepository  extends JpaRepository<ExpenseUser,Long> {

}

