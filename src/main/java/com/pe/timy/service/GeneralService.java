package com.pe.timy.service;

import java.util.List;

import com.pe.timy.util.ProductoMapper;

public interface GeneralService {

	String encryptPassword(String contrasena);

	List<ProductoMapper> mapperList();
}
