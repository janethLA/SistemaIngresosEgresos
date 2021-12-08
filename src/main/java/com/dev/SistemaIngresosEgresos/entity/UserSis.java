package com.dev.SistemaIngresosEgresos.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity(name = "User")
@Table(name = "USER")
public class UserSis {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private long idUser;
	@Column
	private String name;
	@Column(unique = true)
	private String userName;
	@Column
	private String password;
	@Column
	private int telephone;
	@Column
	private LocalDate registrationDate;
	@Column
	private LocalDate expiryDate;
	@Column(columnDefinition = "boolean default true")
	private boolean active;
	
	@JoinColumn(name = "idRole" )
	@OneToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH} )
	private Role role;
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference(value="user-income")
	private List<Income> income;
	
	@OneToMany(mappedBy = "userS",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference(value="user-expense")
	private List<Expense> expense;
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference(value="userI")
	private List<IncomeUser> incomeUser;
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference(value="userE")
	private List<ExpenseUser> expenseUser;

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Income> getIncome() {
		return income;
	}

	public void setIncome(List<Income> income) {
		this.income = income;
	}

	public List<Expense> getExpense() {
		return expense;
	}

	public void setExpense(List<Expense> expense) {
		this.expense = expense;
	}

	public List<IncomeUser> getIncomeUser() {
		return incomeUser;
	}

	public void setIncomeUser(List<IncomeUser> incomeUser) {
		this.incomeUser = incomeUser;
	}

	public List<ExpenseUser> getExpenseUser() {
		return expenseUser;
	}

	public void setExpenseUser(List<ExpenseUser> expenseUser) {
		this.expenseUser = expenseUser;
	}
	
}
