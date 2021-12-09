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
   
    public List<ReportOutput> incomesReport(long id, String year) {
		
		UserSis user = userService.findById(id);
		List<Income> allIncomes = user.getIncome();
		List<ReportOutput> allIncomesForReport = new ArrayList<ReportOutput>();

		
		for (Income found : allIncomes) {

			ReportOutput income = new ReportOutput();
			income.setAccountName(found.getIncomeName());

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

			for (int j = 0; j < found.getIncomeUser().size(); j++) {
				
				IncomeUser incomeUser = found.getIncomeUser().get(j);
				if(incomeUser.getDate().getYear()==Integer.parseInt(year)) {
					
					if (incomeUser.getMonth().equalsIgnoreCase("Enero")) {
						amountJanuary += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Febrero")) {
						amountFebruary += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Marzo")) {
						amountMarch += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Abril")) {
						amountApril += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Mayo")) {
						amountMay += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Junio")) {
						amountJune += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Julio")) {
						amountJuly += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Agosto")) {
						amountAugust += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Septiembre")) {
						amountSeptember += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Octubre")) {
						amountOctuber += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Noviembre")) {
						amountNovember += incomeUser.getAmount();
					}
					if (incomeUser.getMonth().equalsIgnoreCase("Diciembre")) {
						amountDecember += incomeUser.getAmount();
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

			income.setAmount(amountForMonth);

			allIncomesForReport.add(income);
		}

		return allIncomesForReport;
	}

}
