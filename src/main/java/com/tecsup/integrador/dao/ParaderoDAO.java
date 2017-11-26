package com.tecsup.integrador.dao;

import java.util.List;


import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.ParaderoApi;



public interface ParaderoDAO {
	List<ParaderoApi> findAllEmpresas() throws DAOException, EmptyResultException ;
	ParaderoApi findEmpresa(int id) throws DAOException, EmptyResultException;
	
	void create(String nombre, Double latitud, Double longitud) throws DAOException;

	void delete(String nombre) throws DAOException;

	void update(String nombre, Double latitud, Double longitud, long id_empresa) throws DAOException;
}
