package com.dev.SistemaIngresosEgresos.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name = "ExpenseUser")
@Table(name = "EXPENSE_USER")
public class ExpenseUser {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private long idExpenseUser;
	@Column
	private LocalDate date;
	@Column
	private String month;
	@Column
	private String concept;
	@Column
	private double amount;
	@Column
	private String comment;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference(value="userE")
	private UserSis user;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idExpense")
	@JsonBackReference(value="expense")
	private Expense expense;

	public long getIdExpenseUser() {
		return idExpenseUser;
	}

	public void setIdExpenseUser(long idExpenseUser) {
		this.idExpenseUser = idExpenseUser;
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

	public UserSis getUser() {
		return user;
	}

	public void setUser(UserSis user) {
		this.user = user;
	}

	public Expense getExpense() {
		return expense;
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	
}
