package com.dev.SistemaIngresosEgresos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.SistemaIngresosEgresos.entity.Setting;

public interface SettingRepository extends JpaRepository<Setting, Integer> {

}
