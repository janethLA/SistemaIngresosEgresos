package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.dev.SistemaIngresosEgresos.entity.Role;
import com.dev.SistemaIngresosEgresos.entity.Setting;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.repository.RoleRepository;


@Component
public class CreateUserAdmin implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private SettingService settingService;
	
	@Override
	public void run(String... args) throws Exception {
		
		if(roleService.findAll().size()==0) {
			
			UserSis newUser=new UserSis();
			newUser.setName("Admin");
			newUser.setUserName("Admin");
			newUser.setPassword(encoder.encode("admin2021"));
			newUser.setRegistrationDate(LocalDate.now());
			newUser.setExpiryDate(LocalDate.of(2021, 12, 30));
			newUser.setActive(true);
			UserSis saveUser=userService.save(newUser);
			
			//Rol de superusuario (ADMIN)
			Role role=new Role();
			role.setRoleName("ROLE_ADMIN");
			role.setDescription("El admin se encarga de registrar usuarios");
			Role newRole=roleService.save(role);
			
			//Rol de Usuarios 
			Role role1=new Role();
			role1.setRoleName("ROLE_USER_FINAL");
			role1.setDescription("El usuario va a poder registrar sus ingresos y egresos");
			Role newRole1=roleService.save(role1);
			
			//Rol asignado al admin
			saveUser.setRole(newRole);
			userService.save(newUser);
			
			Setting setting=new Setting();
			settingService.save(setting);
		}
		
	}

}
