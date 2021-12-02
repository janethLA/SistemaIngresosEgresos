package com.dev.SistemaIngresosEgresos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SistemaIngresosEgresos.entity.Expense;

@Repository
public interface ExpenseRepository  extends JpaRepository<Expense,Long> {

}

