package com.dev.SistemaIngresosEgresos.output;

import java.time.LocalDate;

public class IncomeUserOutput {
	
	private long idIncomeUser;
	private LocalDate date;
	private String month;
	private String concept;
	private double amount;
	private String comment;
	private String incomeAccount;
	
	public long getIdIncomeUser() {
		return idIncomeUser;
	}
	public void setIdIncomeUser(long idIncomeUser) {
		this.idIncomeUser = idIncomeUser;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getConcept() {
		return concept;
	}
	public void setConcept(String concept) {
		this.concept = concept;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIncomeAccount() {
		return incomeAccount;
	}
	public void setIncomeAccount(String incomeAccount) {
		this.incomeAccount = incomeAccount;
	}
	
}
