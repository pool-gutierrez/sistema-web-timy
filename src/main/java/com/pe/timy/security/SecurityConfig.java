package com.pe.timy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.pe.timy.service.impl.EmpleadoServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	@Autowired
	private EmpleadoServiceImpl empleadoServiceImpl;
	
	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authRequest -> authRequest
						.requestMatchers("/empleado", "/empleado/**").hasRole("Administrador")
						.requestMatchers("/cliente", "/empleado/**").hasAnyRole("Administrador", "Vendedor")
						.requestMatchers("/producto", "/producto/**").hasAnyRole("Administrador", "Almacenero", "Vendedor")
						.requestMatchers("/categoria", "/categoria/**").hasAnyRole("Administrador", "Almacenero")
						.requestMatchers("/proveedores", "/proveedor/**").hasAnyRole("Administrador", "Almacenero")
						.requestMatchers("/inventario", "/inventario/**").hasAnyRole("Administrador", "Almacenero")
						.requestMatchers("/cotizacion", "/cotizacion/**").hasAnyRole("Administrador", "Vendedor")
						.requestMatchers("/compra", "/compra/**").hasAnyRole("Administrador", "Almacenero")
						.requestMatchers("/venta", "/venta/**").hasAnyRole("Administrador", "Vendedor")
						.anyRequest().permitAll())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
						.usernameParameter("usuario").passwordParameter("password")
						.defaultSuccessUrl("/producto", true).failureUrl("/login?error").permitAll())
				.exceptionHandling(exception -> exception.accessDeniedPage("/login?denied"))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout")
						.invalidateHttpSession(true).deleteCookies("JSESSIONID"))
				.build();
	}
	
	@Bean
	AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(empleadoServiceImpl);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}
}
