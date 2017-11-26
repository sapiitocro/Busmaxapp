package com.tecsup.integrador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.integrador.dao.ParaderoDAO;
import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.ParaderoApi;
@Service
public class ParaderoServiceImpl implements ParaderoService{
	@Autowired
	private ParaderoDAO paraderoDAO;
	
	@Override
	public List<ParaderoApi> findAll() throws DAOException, EmptyResultException {
		
		List<ParaderoApi> prds = paraderoDAO.findAllEmpresas();
	
		return prds;
	}

	@Override
	public ParaderoApi find(int id_empresa) throws DAOException, EmptyResultException {
		ParaderoApi prd = paraderoDAO.findEmpresa(id_empresa);

		return prd;
		
	}
	
	

	@Override
	public void update(String nombre, Double latitud, Double longitud, long id_empresa) throws DAOException {
		paraderoDAO.update(nombre, latitud,longitud, id_empresa);
		
	}

	@Override
	public void delete(String nombre) throws DAOException {
		paraderoDAO.delete(nombre);
		
	}

	@Override
	public void create(String nombre, Double latitud, Double longitud) throws DAOException {
		paraderoDAO.create(nombre,latitud,longitud);
		
	}



}
