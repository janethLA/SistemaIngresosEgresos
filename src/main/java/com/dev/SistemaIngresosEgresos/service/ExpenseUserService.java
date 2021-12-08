package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Expense;
import com.dev.SistemaIngresosEgresos.entity.ExpenseUser;
import com.dev.SistemaIngresosEgresos.entity.Income;
import com.dev.SistemaIngresosEgresos.entity.IncomeUser;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.ExpenseInput;
import com.dev.SistemaIngresosEgresos.input.ExpenseUserInput;
import com.dev.SistemaIngresosEgresos.output.ExpenseOutput;
import com.dev.SistemaIngresosEgresos.output.ExpenseUserOutput;
import com.dev.SistemaIngresosEgresos.output.IncomeUserOutput;
import com.dev.SistemaIngresosEgresos.repository.ExpenseRepository;
import com.dev.SistemaIngresosEgresos.repository.ExpenseUserRepository;


@Service
public class ExpenseUserService {

	@Autowired
	private ExpenseUserRepository expenseUserRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private ExpenseService expenseService;
	
	
	public ExpenseUserInput save(ExpenseUserInput expense, long id) {
		
		UserSis user=userService.findById(id);
		Expense newExpense=expenseService.findById(expense.getIdExpense());
		ExpenseUser expenseUser=new ExpenseUser();
		expenseUser.setDate(expense.getDate());
		expenseUser.setMoon(expense.getMoon());
		expenseUser.setAmount(expense.getAmount());
		expenseUser.setComment(expense.getComment());
		expenseUser.setConcept(expense.getConcept());
		expenseUser.setUser(user);
		expenseUser.setExpense(newExpense);
		
		expenseUserRepository.save(expenseUser);
		
	     return  expense;
	}
	
	public Iterable<ExpenseUserOutput> allExpesesByUser(long id) {
		
		UserSis user = userService.findById(id);
		List<ExpenseUser> allExpenses = user.getExpenseUser();
		List<ExpenseUserOutput> allExpensesByOrder = new ArrayList<ExpenseUserOutput>();

		for (ExpenseUser found : allExpenses) {

			ExpenseUserOutput expense = new ExpenseUserOutput();
			expense.setIdExpenseUser(found.getIdExpenseUser());
			expense.setDate(found.getDate());
			expense.setMoon(found.getMoon());
			expense.setAmount(found.getAmount());
			expense.setConcept(found.getConcept());
			expense.setComment(found.getComment());
			expense.setExpenseAccount(found.getExpense().getExpenseName());
			
			allExpensesByOrder.add(expense);

		}

		return allExpensesByOrder;
	}
	
	public ExpenseUserInput updateExpenseOfUser( long id, ExpenseUserInput expenseUser) {
		
		ExpenseUser expense=expenseUserRepository.findById(id).get();
		
		if(expenseUser.getDate()!=null) {
			expense.setDate(expenseUser.getDate());
		}
		if(expenseUser.getAmount()!=0) {
			expense.setAmount(expenseUser.getAmount());
		}
		if(!expenseUser.getMoon().isEmpty()) {
			expense.setMoon(expenseUser.getMoon());
		}
		if(!expenseUser.getConcept().isEmpty()) {
			expense.setConcept(expenseUser.getConcept());
		}
		if(!expenseUser.getComment().isEmpty()) {
			expense.setComment(expenseUser.getComment());
		}
		
		expenseUserRepository.save(expense);
		return expenseUser;
	}
	
}
