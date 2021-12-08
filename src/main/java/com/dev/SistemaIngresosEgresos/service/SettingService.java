package com.dev.SistemaIngresosEgresos.service;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.dev.SistemaIngresosEgresos.entity.Setting;
import com.dev.SistemaIngresosEgresos.repository.SettingRepository;



@Service
public class SettingService {
	@Autowired
	private ModelMapper modelMapper;
    @Autowired
    private SettingRepository settingRepository;
	
	
	@Transactional
	public Setting save(Setting setting) {

	    return settingRepository.save(setting);
	}
	
	public Setting findById(int id) {
		return settingRepository.findById(id).get();
	}
	
	public String updateWelcomeMessage(String message) {
		
		Setting s=settingRepository.findById(1).get();
		s.setWelcomeMessage(message);
		settingRepository.save(s);
		return "Se actualizó el mensaje de bienvenida";
	}
	
   public String updateImage(MultipartFile image) {
		
		Setting s=settingRepository.findById(1).get();
		try {
			s.setImage(image.getBytes());
			settingRepository.save(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Se actualizó la imagen";
	}
   
    public Setting getSetting() {
		
		return settingRepository.findById(1).get();
	}
   
	
}
