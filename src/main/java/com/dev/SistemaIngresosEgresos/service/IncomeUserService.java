package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.SistemaIngresosEgresos.entity.Expense;
import com.dev.SistemaIngresosEgresos.entity.Income;
import com.dev.SistemaIngresosEgresos.entity.IncomeUser;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.IncomeUserInput;
import com.dev.SistemaIngresosEgresos.output.IncomeUserOutput;
import com.dev.SistemaIngresosEgresos.output.ReportOutput;
import com.dev.SistemaIngresosEgresos.output.UserOutput;
import com.dev.SistemaIngresosEgresos.repository.IncomeUserRepository;

@Service
public class IncomeUserService {

	@Autowired
	private IncomeUserRepository incomeUserRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserService userService;
	@Autowired
	private IncomeService incomeService;
	
	
	public IncomeUserInput save(IncomeUserInput income, long id) {
		
		UserSis user=userService.findById(id);
		Income newIncome=incomeService.findById(income.getIdIncome());
		IncomeUser incomeUser=new IncomeUser();
		incomeUser.setDate(income.getDate());
		incomeUser.setMonth(income.getMonth());;
		incomeUser.setAmount(income.getAmount());
		incomeUser.setComment(income.getComment());
		incomeUser.setConcept(income.getConcept());
		incomeUser.setUser(user);
		incomeUser.setIncome(newIncome);
		
		incomeUserRepository.save(incomeUser);
		
	     return  income;
	}
	
	public Iterable<IncomeUserOutput> allIncomesByUser(long id) {
		
		UserSis user = userService.findById(id);
		List<IncomeUser> allIncomes = user.getIncomeUser();
		List<IncomeUserOutput> allIncomesByOrder = new ArrayList<IncomeUserOutput>();

		for (IncomeUser found : allIncomes) {

			IncomeUserOutput income = new IncomeUserOutput();
			income.setIdIncomeUser(found.getIdIncomeUser());
			income.setDate(found.getDate());
			income.setMonth(found.getMonth());
			income.setAmount(found.getAmount());
			income.setConcept(found.getConcept());
			income.setComment(found.getComment());
			income.setIncomeAccount(found.getIncome().getIncomeName());
			
			allIncomesByOrder.add(income);

		}

		return allIncomesByOrder;
	}
	
	public IncomeUserInput updateIncomeOfUser( long id, IncomeUserInput incomeUser) {
		
		IncomeUser income=incomeUserRepository.findById(id).get();
		
		if(incomeUser.getDate()!=null) {
			//System.out.println("incomeUser.getDate()  "+incomeUser.getDate());
			income.setDate(incomeUser.getDate());
		}
		if(incomeUser.getAmount()!=0) {
			income.setAmount(incomeUser.getAmount());
		}
		if(!incomeUser.getMonth().isEmpty()) {
			income.setMonth(incomeUser.getMonth());
		}
		if(!incomeUser.getConcept().isEmpty()) {
			income.setConcept(incomeUser.getConcept());
		}
		if(!incomeUser.getComment().isEmpty()) {
			income.setComment(incomeUser.getComment());
		}
		if(incomeUser.getIdIncome()!=0) {
			Income e=incomeService.findById(incomeUser.getIdIncome());
			income.setIncome(e);
		}
		incomeUserRepository.save(income);
		return incomeUser;
	}
	
	
	
}
