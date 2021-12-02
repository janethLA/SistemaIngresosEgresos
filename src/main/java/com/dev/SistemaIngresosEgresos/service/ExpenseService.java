package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Expense;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.ExpenseInput;
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
		newExpense.setUserS(user);
		expenseRepository.save(newExpense);
		
	     return  expense;
	}
	
    public Iterable<ExpenseOutput> getAllExpenses(long id) {
		
		UserSis user = userService.findById(id);
		List<Expense> allExpenses = user.getExpense();
		List<ExpenseOutput> allExpensesByOrder = new ArrayList<ExpenseOutput>();

		for (Expense found : allExpenses) {

			ExpenseOutput expense = new ExpenseOutput();
			expense.setIdExpense(found.getIdExpense());
			expense.setExpenseName(found.getExpenseName());
			expense.setRegistrationDate(found.getRegistrationDate());
			
			allExpensesByOrder.add(expense);

		}

		return allExpensesByOrder;
	}
	
}
