package com.dev.SistemaIngresosEgresos.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Role;
import com.dev.SistemaIngresosEgresos.repository.RoleRepository;


@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private ModelMapper modelMapper;

	public Role findById(int idRole) {
		return roleRepository.findById(idRole).get();
	}

	 public List<Role> findAll(){
	    	return roleRepository.findAll();
	 }
	 
	 public Role save(Role role) {
		 return roleRepository.save(role);
	 }
}
