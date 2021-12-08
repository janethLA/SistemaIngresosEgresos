package com.dev.SistemaIngresosEgresos.output;

import java.util.ArrayList;

public class ReportOutput {

	private String accountName;
	private ArrayList<Double> amount;
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public ArrayList<Double> getAmount() {
		return amount;
	}
	public void setAmount(ArrayList<Double> amount) {
		this.amount = amount;
	}
	
	
	
}
