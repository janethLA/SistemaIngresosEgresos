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

import com.dev.SistemaIngresosEgresos.input.ExpenseInput;
import com.dev.SistemaIngresosEgresos.input.ExpenseUserInput;
import com.dev.SistemaIngresosEgresos.output.ExpenseOutput;
import com.dev.SistemaIngresosEgresos.output.ReportOutput;
import com.dev.SistemaIngresosEgresos.service.ExpenseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private ModelMapper modelMapper;
	

	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@PostMapping("/createExpense/{id}")
	public ResponseEntity<?> createExpense(@RequestBody ExpenseInput expense, @PathVariable Long id){
		
		return ResponseEntity.ok(expenseService.save(expense,id));
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@GetMapping("/allExpenses/{id}")
	public Iterable<ExpenseOutput> getAllExpenses(@PathVariable Long id){
		
		return expenseService.getAllExpenses(id);
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")
	@PutMapping("/deleteExpense/{id}")
	public ResponseEntity<?> deleteExpense(@PathVariable Long id){
	    
		return ResponseEntity.ok(expenseService.deleteExpense(id));
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")
	@PutMapping("/updateExpense/{id}")
	public ResponseEntity<?> updateExpense(@PathVariable long id,@RequestBody ExpenseInput expense){
		return ResponseEntity.ok(expenseService.updateExpense(id, expense));
	}
	
	@PreAuthorize("hasRole('ROLE_USER_FINAL')")	
	@GetMapping("/expensesReport/{id}/{year}")
	public Iterable<ReportOutput> expensesReport(@PathVariable Long id, @PathVariable String year){
		
		return expenseService.expensesReport(id, year);
	}
}
