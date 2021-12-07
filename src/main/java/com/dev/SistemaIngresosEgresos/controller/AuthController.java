package com.dev.SistemaIngresosEgresos.controller;


import java.time.LocalDate;
import java.util.Collection;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dev.SistemaIngresosEgresos.Config.JWTUtil;
import com.dev.SistemaIngresosEgresos.input.AuthenticationRequest;
import com.dev.SistemaIngresosEgresos.output.AuthenticationResponse;
import com.dev.SistemaIngresosEgresos.service.AuthUserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request) {
    
    	  try {
              authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
              UserDetails userDetails = authUserService.loadUserByUsername(request.getUsername());
              String jwt = jwtUtil.generateToken(userDetails);
              Collection<? extends GrantedAuthority> roles=userDetails.getAuthorities();
            
              if(LocalDate.now().isBefore(authUserService.getExpiryDate(request.getUsername()))) {
            	  return new ResponseEntity<>(new AuthenticationResponse(jwt,roles, authUserService.getIdUser(request.getUsername()),
             				authUserService.getNameUser(request.getUsername()), authUserService.getName(request.getUsername()))
        	                    , HttpStatus.OK);
              }else {
            	  return new ResponseEntity<>("Su cuenta ha expirado", HttpStatus.FORBIDDEN);
              }
              
          } catch (BadCredentialsException e) {
        	  return new ResponseEntity<>("Username o password incorrecto",HttpStatus.FORBIDDEN);
          }
    	
    }
    
}
