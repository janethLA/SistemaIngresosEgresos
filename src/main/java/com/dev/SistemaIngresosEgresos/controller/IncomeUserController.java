package com.dev.SistemaIngresosEgresos.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.SistemaIngresosEgresos.input.IncomeUserInput;
import com.dev.SistemaIngresosEgresos.input.UserInput;
import com.dev.SistemaIngresosEgresos.output.IncomeUserOutput;
import com.dev.SistemaIngresosEgresos.output.ReportOutput;
import com.dev.SistemaIngresosEgresos.output.UserOutput;
import com.dev.SistemaIngresosEgresos.service.IncomeUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/incomeUser")
public class IncomeUserController {

	@Autowired
	private IncomeUserService incomeUserService;
	@Autowired
	private ModelMapper modelMapper;
	

	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@PostMapping("/registerIncome/{id}")
	public ResponseEntity<?> createIncome(@RequestBody IncomeUserInput income, @PathVariable Long id){
		
		return ResponseEntity.ok(incomeUserService.save(income,id));
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@GetMapping("/allIncomesByUser/{id}")
	public Iterable<IncomeUserOutput> allIncomesByUser(@PathVariable Long id){
		
		return incomeUserService.allIncomesByUser(id);
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")
	@PutMapping("/updateIncomeOfUser/{id}")
	public ResponseEntity<?> updateIncomeOfUser(@PathVariable long id,@RequestBody IncomeUserInput income){
		return ResponseEntity.ok(incomeUserService.updateIncomeOfUser(id, income));
	}
	
}
