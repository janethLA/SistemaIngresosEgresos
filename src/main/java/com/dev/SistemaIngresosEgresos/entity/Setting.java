package com.dev.SistemaIngresosEgresos.entity;

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

@Entity(name = "Setting")
@Table(name = "SETTING")
public class Setting {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSetting;
	@Column
	private String welcomeMessage;
	@Column(columnDefinition = "longblob")
	private byte[] image;
	
	public int getIdSetting() {
		return idSetting;
	}
	public void setIdSetting(int idSetting) {
		this.idSetting = idSetting;
	}
	public String getWelcomeMessage() {
		return welcomeMessage;
	}
	public void setWelcomeMessage(String welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
}

