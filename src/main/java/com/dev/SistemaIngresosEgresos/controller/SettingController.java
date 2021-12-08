package com.dev.SistemaIngresosEgresos.controller;

import javax.annotation.security.PermitAll;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dev.SistemaIngresosEgresos.entity.Setting;
import com.dev.SistemaIngresosEgresos.service.SettingService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	private SettingService settingService;
	@Autowired
	private ModelMapper modelMapper;


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/updateWelcomeMessage")
	public ResponseEntity<?> updateWelcomeMessage(@RequestParam("welcomeMessage") String message) {

		return ResponseEntity.ok(settingService.updateWelcomeMessage(message));
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/updateImage")
	public ResponseEntity<?> updateImage( @RequestParam("image") MultipartFile image) {

		return ResponseEntity.ok(settingService.updateImage(image));
	}

	@PermitAll
	@GetMapping("/getSetting")
	public Setting getSetting() {

		return settingService.getSetting();
	}

}
