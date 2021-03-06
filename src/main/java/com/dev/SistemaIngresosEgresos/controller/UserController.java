package com.dev.SistemaIngresosEgresos.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.SistemaIngresosEgresos.input.UserInput;
import com.dev.SistemaIngresosEgresos.output.UserOutput;
import com.dev.SistemaIngresosEgresos.service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;
	
	public UserController() {	
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@PostMapping("/createUser")
	public ResponseEntity<?> createUser(@RequestBody UserInput user){
		
		return ResponseEntity.ok(userService.save(user));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")	
	@GetMapping("/allUser")
	public Iterable<UserOutput> getAllUser(){
		
		return userService.getAllUser();
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/uniqueUserName/{userName}")
	public ResponseEntity<?> uniqueUserName(@PathVariable String userName){
		
		return ResponseEntity.ok(userService.noExistsUserName(userName));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value = "/deleteUser/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer id){
	    
		return ResponseEntity.ok(userService.deleteUser(id));
	}
	
	
}

