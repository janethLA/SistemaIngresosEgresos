package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Role;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.repository.UserRepository;

@Service
public class AuthUserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserSis us= userRepository.findByUserName(username);
		List <GrantedAuthority> roles=new ArrayList<>();
		Role rol=us.getRole();
		roles.add(new SimpleGrantedAuthority(rol.getRoleName()));
		
		UserDetails userDetails=new User(us.getUserName(),us.getPassword(),roles);
		return userDetails;
	}
	
	public String getNameUser(String name) {
		return userRepository.findByUserName(name).getName();
	}
	
	public long getIdUser(String name) {
		return userRepository.findByUserName(name).getIdUser();
	}
	
	public String getName(String name) {
		return userRepository.findByUserName(name).getName();
	}
	
	public LocalDate getExpiryDate(String name) {
		return userRepository.findByUserName(name).getExpiryDate();
	}
	
	/*public String messageExpiry(String name) {
		LocalDate expiryDate=getExpiryDate(name);
        LocalDate nowDate=LocalDate.now();
        
	}*/
	
}
