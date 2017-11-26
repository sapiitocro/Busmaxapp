package com.tecsup.integrador.dao;

import java.util.List;


import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.EmpresaApi;



public interface EmpresaDAO {
	List<EmpresaApi> findAllEmpresas() throws DAOException, EmptyResultException ;
	EmpresaApi findEmpresa(int id) throws DAOException, EmptyResultException;
	
	void create(String nombre, String descripcion) throws DAOException;

	void delete(String nombre) throws DAOException;

	void update(String nombre, String descripcion, long id_empresa) throws DAOException;
}
