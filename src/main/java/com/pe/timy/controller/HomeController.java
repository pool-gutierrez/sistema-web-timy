package com.pe.timy.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

	@GetMapping(value = "/login")
	public String login(Authentication authentication) {
		if (authentication != null) {
			return "redirect:/producto";
		}
		return "admin/login";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}
}
