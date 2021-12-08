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

@Entity(name = "Income")
@Table(name = "INCOME")
public class Income {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private long idIncome;
	@Column
	private String incomeName;
	@Column
	private LocalDate registrationDate;
	@Column(columnDefinition = "boolean default true")
	private boolean active;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference(value="user-income")
	private UserSis user;
	
	@OneToMany(mappedBy = "income",cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JsonManagedReference(value="income")
	private List<IncomeUser> incomeUser;

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

	public UserSis getUser() {
		return user;
	}

	public void setUser(UserSis user) {
		this.user = user;
	}

	public List<IncomeUser> getIncomeUser() {
		return incomeUser;
	}

	public void setIncomeUser(List<IncomeUser> incomeUser) {
		this.incomeUser = incomeUser;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
