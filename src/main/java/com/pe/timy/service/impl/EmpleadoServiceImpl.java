package com.pe.timy.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.timy.entity.Empleado;
import com.pe.timy.entity.Rol;
import com.pe.timy.repository.EmpleadoRepository;
import com.pe.timy.service.EmpleadoService;

@Service
public class EmpleadoServiceImpl implements EmpleadoService, UserDetailsService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAll() {
		return empleadoRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> findAllActive() {
		return empleadoRepository.findAllByEstado(true);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Empleado> findById(Integer empleadoId) {
		return empleadoRepository.findById(empleadoId);
	}
	
	@Override
	@Transactional
	public void save(Empleado empleado) {
		empleadoRepository.save(empleado);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Empleado findByUsuario(String usuario) {
		return empleadoRepository.findByUsuario(usuario);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Empleado empleado = findByUsuario(username);
		if(empleado!=null) {
			Collection<GrantedAuthority> authorities = new ArrayList<>();
			Rol rol = empleado.getRol();
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+rol.getNombre());
			authorities.add(authority);
			
			return new User(empleado.getUsuario(), empleado.getContrasena(), empleado.getEstado(), true, true, true, authorities);
		}
		
		throw new UsernameNotFoundException("Usuario " + username + " no existe en la BD.");
	}
}
