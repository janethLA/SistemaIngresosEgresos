package com.dev.SistemaIngresosEgresos.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.SistemaIngresosEgresos.entity.Income;
import com.dev.SistemaIngresosEgresos.entity.IncomeUser;
import com.dev.SistemaIngresosEgresos.entity.UserSis;
import com.dev.SistemaIngresosEgresos.input.IncomeInput;
import com.dev.SistemaIngresosEgresos.input.IncomeUserInput;
import com.dev.SistemaIngresosEgresos.output.IncomeOutput;
import com.dev.SistemaIngresosEgresos.output.ReportOutput;
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
		newIncome.setActive(true);
		newIncome.setUser(user);
		incomeRepository.save(newIncome);
		
	     return  income;
	}
	
	public Iterable<IncomeOutput> getAllIncomes(long id) {
		
		UserSis user = userService.findById(id);
		List<Income> allIncomes = user.getIncome();
		List<IncomeOutput> allIncomesByOrder = new ArrayList<IncomeOutput>();

		for (Income found : allIncomes) {

			if(found.isActive()) {
				IncomeOutput income = new IncomeOutput();
				income.setIdIncome(found.getIdIncome());
				income.setIncomeName(found.getIncomeName());
				income.setRegistrationDate(found.getRegistrationDate());
				
				allIncomesByOrder.add(income);
			}
			
		}

		return allIncomesByOrder;
	}
	
	public Income save(Income income) {

		return incomeRepository.save(income);
	}

	public List<Income> findAll() {
		return incomeRepository.findAll();
	}

	public Income findById(long id) {
		return incomeRepository.findById(id).get();
	}
	
	public String deleteIncome(long id) {
		try {
			Income income = incomeRepository.findById(id).get();
			income.setActive(false);
			incomeRepository.save(income);
			return "Se eliminó la cuenta de ingreso "+income.getIncomeName();
		} catch (Exception e) {
			return "No se eliminó la cuenta de ingreso";
		}

	}
	
   public IncomeInput updateIncome( long id, IncomeInput incomeUser) {
		
		Income income=incomeRepository.findById(id).get();
		
		if(!incomeUser.getIncomeName().isEmpty()) {
			income.setIncomeName(incomeUser.getIncomeName());
		}
		
		incomeRepository.save(income);
		return incomeUser;
	}
   
  /* public ReportOutput incomesReport(long id, String year) {
		
		UserSis user = userService.findById(id);
		List<Income> allIncomes = user.getIncome();
		List<ReportOutput> allIncomesByOrder = new ArrayList<ReportOutput>();

		
		for (Income found : allIncomes) {

			if(year==year) {
				ReportOutput income = new ReportOutput();
				income.setAccountName(found.getIncomeName());
				Double [] amountForMonth=new Double[12];
				for(int j=0;j<found.getIncomeUser().size();j++) {
					IncomeUser incomeUser=found.getIncomeUser().get(j);
					if(incomeUser.getMoon()) {
						
					}
				}
				for (int i=0;i<amountForMonth.length; i++) {
					
				}
				income.setIdIncomeUser(found.getIdIncomeUser());
				income.setDate(found.getDate());
				income.setMoon(found.getMoon());
				income.setAmount(found.getAmount());
				income.setConcept(found.getConcept());
				income.setComment(found.getComment());
				income.setIncomeAccount(found.getIncome().getIncomeName());
				
				allIncomesByOrder.add(income);
			}
			

		}

		return allIncomesByOrder;
	}*/

}
