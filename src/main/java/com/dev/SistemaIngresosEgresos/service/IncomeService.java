package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Income;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.IncomeInput;
import com.dev.SistemaIngresosEgresos.output.IncomeOutput;
import com.dev.SistemaIngresosEgresos.output.UserOutput;
import com.dev.SistemaIngresosEgresos.repository.IncomeRepository;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserService userService;
	
	
	public IncomeInput save(IncomeInput income, long id) {
		
		UserSis user=userService.findById(id);
		Income newIncome=new Income();
		newIncome.setIncomeName(income.getIncomeName());
		newIncome.setRegistrationDate(LocalDate.now());
		newIncome.setUser(user);
		incomeRepository.save(newIncome);
		
	     return  income;
	}
	
	public Iterable<IncomeOutput> getAllIncomes(long id) {
		
		UserSis user = userService.findById(id);
		List<Income> allIncomes = user.getIncome();
		List<IncomeOutput> allIncomesByOrder = new ArrayList<IncomeOutput>();

		for (Income found : allIncomes) {

			IncomeOutput income = new IncomeOutput();
			income.setIdIncome(found.getIdIncome());
			income.setIncomeName(found.getIncomeName());
			income.setRegistrationDate(found.getRegistrationDate());
			
			allIncomesByOrder.add(income);

		}

		return allIncomesByOrder;
	}
	
}
