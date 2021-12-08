package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Expense;
import com.dev.SistemaIngresosEgresos.entity.Income;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.ExpenseInput;
import com.dev.SistemaIngresosEgresos.input.IncomeInput;
import com.dev.SistemaIngresosEgresos.output.ExpenseOutput;
import com.dev.SistemaIngresosEgresos.repository.ExpenseRepository;


@Service
public class ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserService userService;
	
	public ExpenseInput save(ExpenseInput expense, long id) {
		
		UserSis user=userService.findById(id);
		Expense newExpense=new Expense();
		newExpense.setExpenseName(expense.getExpenseName());
		newExpense.setRegistrationDate(LocalDate.now());
		newExpense.setActive(true);
		newExpense.setUserS(user);
		expenseRepository.save(newExpense);
		
	     return  expense;
	}
	
    public Iterable<ExpenseOutput> getAllExpenses(long id) {
		
		UserSis user = userService.findById(id);
		List<Expense> allExpenses = user.getExpense();
		List<ExpenseOutput> allExpensesByOrder = new ArrayList<ExpenseOutput>();

		for (Expense found : allExpenses) {

			if(found.isActive()) {
				ExpenseOutput expense = new ExpenseOutput();
				expense.setIdExpense(found.getIdExpense());
				expense.setExpenseName(found.getExpenseName());
				expense.setRegistrationDate(found.getRegistrationDate());
				
				allExpensesByOrder.add(expense);

			}
			
		}

		return allExpensesByOrder;
	}
    
    public Expense save(Expense expense) {

		return expenseRepository.save(expense);
	}

	public List<Expense> findAll() {
		return expenseRepository.findAll();
	}

	public Expense findById(long id) {
		return expenseRepository.findById(id).get();
	}
	
	public String deleteExpense(long id) {
		try {
			Expense expense = expenseRepository.findById(id).get();
			expense.setActive(false);
			expenseRepository.save(expense);
			return "Se eliminó la cuenta de egreso "+expense.getExpenseName();
		} catch (Exception e) {
			return "No se eliminó la cuenta de egreso";
		}

	}
	
    public ExpenseInput updateExpense( long id, ExpenseInput expenseUser) {
		
    	Expense expense=expenseRepository.findById(id).get();
		
		if(!expenseUser.getExpenseName().isEmpty()) {
			expense.setExpenseName(expenseUser.getExpenseName());
		}
		
		expenseRepository.save(expense);
		return expenseUser;
	}
}
