package com.tecsup.integrador.services;

import java.util.List;


import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.ParaderoApi;



public interface ParaderoService {
	ParaderoApi find(int id_paradero) throws DAOException, EmptyResultException;

	List<ParaderoApi> findAll() 
			throws DAOException, EmptyResultException; 

	

	void update(String nombre, Double latitud, Double longitud, long id_empresa)
			throws DAOException;

	void delete(String nombre) throws DAOException;

	void create(String nombre, Double latitud, Double longitud) throws DAOException;
}
