package com.dev.SistemaIngresosEgresos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.SistemaIngresosEgresos.entity.UserSis;

@Repository
public interface UserRepository extends JpaRepository<UserSis, Long> {
	UserSis findByUserName(String name);
	UserSis findByTelephone(int telephone);
}
