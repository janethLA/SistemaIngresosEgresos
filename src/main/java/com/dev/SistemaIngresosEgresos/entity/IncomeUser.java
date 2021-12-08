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

@Entity(name = "IncomeUser")
@Table(name = "INCOME_USER")
public class IncomeUser {

	@Id
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private long idIncomeUser;
	
	@Column
	private LocalDate date;
	@Column
	private String moon;
	@Column
	private String concept;
	@Column
	private double amount;
	@Column
	private String comment;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idUser")
	@JsonBackReference(value="userI")
	private UserSis user;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="idIncome")
	@JsonBackReference(value="income")
	private Income income;

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

	public String getMoon() {
		return moon;
	}

	public void setMoon(String moon) {
		this.moon = moon;
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

	public Income getIncome() {
		return income;
	}

	public void setIncome(Income income) {
		this.income = income;
	}
	
	
}
