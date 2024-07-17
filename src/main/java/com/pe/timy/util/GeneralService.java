package com.pe.timy.util;

import java.util.List;

public interface GeneralService {

	String encryptPassword(String contrasena);

	List<ProductoMapper> productoMapper();
	
	List<InventarioMapper> inventarioMappers();
}
