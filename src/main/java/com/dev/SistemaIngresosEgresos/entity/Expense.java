package com.dev.SistemaIngresosEgresos.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	@Column(columnDefinition = "boolean default true")
	private boolean active;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference(value="user-expense")
	private UserSis userS;

	@OneToMany(mappedBy = "expense",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference(value="expense")
	private List<ExpenseUser> expenseUser;
	
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

	public List<ExpenseUser> getExpenseUser() {
		return expenseUser;
	}

	public void setExpenseUser(List<ExpenseUser> expenseUser) {
		this.expenseUser = expenseUser;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
