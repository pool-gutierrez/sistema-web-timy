package com.pe.timy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pe.timy.service.GeneralService;

@Service
public class GeneralServiceImpl implements GeneralService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public String encryptPassword(String contrasena) {
		return bCryptPasswordEncoder.encode(contrasena);
	}
}