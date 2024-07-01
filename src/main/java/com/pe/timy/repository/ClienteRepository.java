package com.pe.timy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pe.timy.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	List<Cliente> findAllByEstado(Boolean estado);
}
