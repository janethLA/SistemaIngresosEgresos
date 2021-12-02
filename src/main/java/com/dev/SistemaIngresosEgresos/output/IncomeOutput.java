package com.dev.SistemaIngresosEgresos.output;

import java.time.LocalDate;

public class IncomeOutput {

	private long idIncome;
	private String incomeName;
	private LocalDate registrationDate;
	
	public long getIdIncome() {
		return idIncome;
	}
	public void setIdIncome(long idIncome) {
		this.idIncome = idIncome;
	}
	public String getIncomeName() {
		return incomeName;
	}
	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	
}
