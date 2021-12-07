package com.dev.SistemaIngresosEgresos.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.dev.SistemaIngresosEgresos.service.AuthUserService;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled =true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthUserService authUService;
	
	@Autowired
    private JwtFilterRequest jwtFilterRequest;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
	   return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(authUService);
	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**/authenticate").permitAll()
        		//.antMatchers("/quotation/getById/*").permitAll()
        		//.antMatchers("/**/updateQuotationAddingBusiness/*").permitAll()
        		//.antMatchers("/**/createEmpresa").permitAll()
        		//.antMatchers("/**/registerBusiness").permitAll()
        		//.antMatchers("/**/updateQuotation/*").permitAll()
        		//.antMatchers("/**/RelatingPriceQuotationToDetails").permitAll()
        		//.antMatchers("/**/uploadDetail").permitAll()
        		//.antMatchers("/api/request").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
}
