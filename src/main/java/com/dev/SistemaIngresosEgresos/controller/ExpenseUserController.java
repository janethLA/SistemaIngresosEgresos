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

import com.dev.SistemaIngresosEgresos.input.ExpenseUserInput;
import com.dev.SistemaIngresosEgresos.output.ExpenseUserOutput;
import com.dev.SistemaIngresosEgresos.service.ExpenseUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/expenseUser")
public class ExpenseUserController {

	@Autowired
	private ExpenseUserService expenseUserService;
	@Autowired
	private ModelMapper modelMapper;
	

	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@PostMapping("/registerExpense/{id}")
	public ResponseEntity<?> registerExpense(@RequestBody ExpenseUserInput expense, @PathVariable Long id){
		
		return ResponseEntity.ok(expenseUserService.save(expense,id));
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@GetMapping("/allExpesesByUser/{id}")
	public Iterable<ExpenseUserOutput> allExpesesByUser(@PathVariable Long id){
		
		return expenseUserService.allExpesesByUser(id);
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")
	@PutMapping("/updateExpenseOfUser/{id}")
	public ResponseEntity<?> updateExpenseOfUser(@PathVariable long id,@RequestBody ExpenseUserInput expense){
		return ResponseEntity.ok(expenseUserService.updateExpenseOfUser(id, expense));
	}
	
}
