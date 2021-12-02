package com.dev.SistemaIngresosEgresos.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.SistemaIngresosEgresos.input.IncomeInput;
import com.dev.SistemaIngresosEgresos.output.IncomeOutput;
import com.dev.SistemaIngresosEgresos.output.UserOutput;
import com.dev.SistemaIngresosEgresos.service.IncomeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/income")
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	@Autowired
	private ModelMapper modelMapper;
	

	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@PostMapping("/createIncome/{id}")
	public ResponseEntity<?> createIncome(@RequestBody IncomeInput income, @PathVariable Long id){
		
		return ResponseEntity.ok(incomeService.save(income,id));
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@GetMapping("/allIncomes/{id}")
	public Iterable<IncomeOutput> getAllIncomes(@PathVariable Long id){
		
		return incomeService.getAllIncomes(id);
	}
}
