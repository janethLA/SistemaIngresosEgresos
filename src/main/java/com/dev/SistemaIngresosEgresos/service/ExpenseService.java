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
import com.dev.SistemaIngresosEgresos.input.IncomeInput;
import com.dev.SistemaIngresosEgresos.output.ExpenseOutput;
import com.dev.SistemaIngresosEgresos.output.ReportOutput;
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
    
    
    public List<ReportOutput> expensesReport(long id, String year) {
		
		UserSis user = userService.findById(id);
		List<Expense> allIncomes = user.getExpense();
		List<ReportOutput> allIncomesForReport = new ArrayList<ReportOutput>();

		
		for (Expense found : allIncomes) {

			ReportOutput expense = new ReportOutput();
			expense.setAccountName(found.getExpenseName());

			ArrayList<Double> amountForMonth = new ArrayList<Double>();
			double amountJanuary = 0.0;
			double amountFebruary = 0.0;
			double amountMarch = 0.0;
			double amountApril = 0.0;
			double amountMay = 0.0;
			double amountJune = 0.0;
			double amountJuly = 0.0;
			double amountAugust = 0.0;
			double amountSeptember = 0.0;
			double amountOctuber = 0.0;
			double amountNovember = 0.0;
			double amountDecember = 0.0;

			for (int j = 0; j < found.getExpenseUser().size(); j++) {
				
				ExpenseUser expenseUser = found.getExpenseUser().get(j);
				if(expenseUser.getDate().getYear()==Integer.parseInt(year)) {
					
					if (expenseUser.getMonth().equalsIgnoreCase("Enero")) {
						amountJanuary += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Febrero")) {
						amountFebruary += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Marzo")) {
						amountMarch += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Abril")) {
						amountApril += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Mayo")) {
						amountMay += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Junio")) {
						amountJune += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Julio")) {
						amountJuly += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Agosto")) {
						amountAugust += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Septiembre")) {
						amountSeptember += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Octubre")) {
						amountOctuber += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Noviembre")) {
						amountNovember += expenseUser.getAmount();
					}
					if (expenseUser.getMonth().equalsIgnoreCase("Diciembre")) {
						amountDecember += expenseUser.getAmount();
					}

				}
				
			}
			amountForMonth.add(amountJanuary);
			amountForMonth.add(amountFebruary);
			amountForMonth.add(amountMarch);
			amountForMonth.add(amountApril);
			amountForMonth.add(amountMay);
			amountForMonth.add(amountJune);
			amountForMonth.add(amountJuly);
			amountForMonth.add(amountAugust);
			amountForMonth.add(amountSeptember);
			amountForMonth.add(amountOctuber);
			amountForMonth.add(amountNovember);
			amountForMonth.add(amountDecember);

			expense.setAmount(amountForMonth);

			allIncomesForReport.add(expense);
		}

		return allIncomesForReport;
	}

}
