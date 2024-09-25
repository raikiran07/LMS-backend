package com.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lms.security.JwtAuthenticationEntryPoint;
import com.lms.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity 	
public class SecurityConfig {
	
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
			JwtAuthenticationFilter jwtAuthenticationFilter) {
		super();
		this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
	}
	
	@Bean
	public AuthenticationManager authenticationManager
	(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(config->config.disable())
		.authorizeHttpRequests(auth->auth
				.requestMatchers("/api/login").permitAll()
				.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
				.anyRequest().authenticated())
		.exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
				
//				.requestMatchers(HttpMethod.POST,"/api/**").permitAll()
//				.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
//				.requestMatchers(HttpMethod.DELETE,"/api/**").permitAll()
//				.requestMatchers(HttpMethod.PUT,"/api/**").permitAll()

//				.requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
//				.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("MANAGER")
//				.anyRequest().authenticated())
//		.httpBasic(Customizer.withDefaults())
		

		return http.build();
	}	

	/**
	 * create a PasswordEncoder bean for encoding passwords.
	 * @return The PasswordEncoder object
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
