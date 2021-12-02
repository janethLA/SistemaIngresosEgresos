package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.UserInput;
import com.dev.SistemaIngresosEgresos.output.UserOutput;
import com.dev.SistemaIngresosEgresos.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public UserSis save(UserSis user) {
		
	     return  userRepository.save(user);
	}
	
	public UserInput save(UserInput user) {
		UserSis newUser=new UserSis();
		newUser.setName(user.getName());
		newUser.setUserName(user.getUsername());
		newUser.setPassword(encoder.encode(user.getPassword()));
		newUser.setTelephone(user.getTelephone());
		newUser.setRegistrationDate(LocalDate.now());
		newUser.setExpiryDate(user.getExpiryDate());
		//Role role= roleService.findById(2);
		//newUser.setRole(role);
	    userRepository.save(newUser);
	    return user;
	}
	
	public Iterable<UserOutput>  getAllUser(){
		List <UserSis> allUsers = userRepository.findAll();
		List <UserOutput> allUsersByOrder = new ArrayList<UserOutput>();
		
		for (UserSis found : allUsers ) {
		
			if(found.isActive()==true) {
				UserOutput newUser = new UserOutput();
				newUser.setIdUser(found.getIdUser());;
				newUser.setName(found.getName());
				newUser.setUsername(found.getUserName());
				newUser.setTelephone(found.getTelephone());
				newUser.setExpiryDate(found.getExpiryDate());
				newUser.setRegistrationDate(found.getRegistrationDate());
				//newUser.setPassword(found.getPassword());
				allUsersByOrder.add(newUser);
			}
			
		}

		return allUsersByOrder;	
	}
	
    public boolean noExistsUserName(String userName) {
		
		boolean result=true;
		List <UserSis> allUser = userRepository.findAll();
		
		for(UserSis a:allUser) {
			if(a.getUserName()!=null){
			if(a.getUserName().equalsIgnoreCase(userName)) {
				result=false;
			}}
		}
		return result;
	}
    
    public String deleteUser(long id) {
    	try {
    		UserSis user=userRepository.findById(id).get();
    		user.setActive(false);
    		userRepository.save(user);
        	return "Se dio de baja correctamente el usuario";
    	}catch (Exception e) {
			return "No se dio de baja al usuario";
		}
    	
    }
    
    public List<UserSis> findAll(){
    	return userRepository.findAll();
    }
    
    public UserSis findById(long id) {
    	return userRepository.findById(id).get();
    }
   
}