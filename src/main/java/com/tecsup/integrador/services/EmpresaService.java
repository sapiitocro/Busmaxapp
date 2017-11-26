package com.tecsup.integrador.services;

import java.util.List;


import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.EmpresaApi;



public interface EmpresaService {
	EmpresaApi find(int id_empresa) throws DAOException, EmptyResultException;

	List<EmpresaApi> findAll() 
			throws DAOException, EmptyResultException; 

	

	void update(String nombre, String descripcion, long id_empresa)
			throws DAOException;

	void delete(String nombre) throws DAOException;

	void create(String nombre, String descripcion) throws DAOException;
}
