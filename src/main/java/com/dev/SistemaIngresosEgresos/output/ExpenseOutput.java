package com.dev.SistemaIngresosEgresos.output;

import java.time.LocalDate;

public class ExpenseOutput {

	private long idExpense;
	private String expenseName;
	private LocalDate registrationDate;
	public long getIdExpense() {
		return idExpense;
	}
	public void setIdExpense(long idExpense) {
		this.idExpense = idExpense;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	
	
}
