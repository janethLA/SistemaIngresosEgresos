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

@Entity(name = "Expense")
@Table(name = "EXPENSE")
public class Expense {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private long idExpense;
	@Column
	private String expenseName;
	@Column
	private LocalDate registrationDate;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference(value="user-expense")
	private UserSis userS;

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

	public UserSis getUserS() {
		return userS;
	}

	public void setUserS(UserSis userS) {
		this.userS = userS;
	}
	
	
}
