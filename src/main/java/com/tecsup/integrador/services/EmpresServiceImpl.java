package com.tecsup.integrador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.integrador.dao.EmpresaDAO;
import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.EmpresaApi;
@Service
public class EmpresServiceImpl implements EmpresaService{
	@Autowired
	private EmpresaDAO empresaDAO;
	
	@Override
	public List<EmpresaApi> findAll() throws DAOException, EmptyResultException {
		
		List<EmpresaApi> emps = empresaDAO.findAllEmpresas();
	
		return emps;
	}

	@Override
	public EmpresaApi find(int id_empresa) throws DAOException, EmptyResultException {
		EmpresaApi emp = empresaDAO.findEmpresa(id_empresa);

		return emp;
		
	}

	@Override
	public void update(String nombre, String descripcion, long id_empresa) throws DAOException {
		empresaDAO.update(nombre, descripcion, id_empresa);
		
	}

	@Override
	public void delete(String nombre) throws DAOException {
		empresaDAO.delete(nombre);
		
	}

	@Override
	public void create(String nombre, String descripcion) throws DAOException {
		empresaDAO.create(nombre, descripcion);
		
	}

}
