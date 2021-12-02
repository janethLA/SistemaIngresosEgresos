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
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference(value="user-income")
	private UserSis user;

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
	
	
}
