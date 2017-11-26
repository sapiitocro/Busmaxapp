package com.tecsup.integrador.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.integrador.dao.UserDAO;
import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.UserApi;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<UserApi> findAll() throws DAOException, EmptyResultException {
		
		List<UserApi> emps = userDAO.findAllEmpresas();
	
		return emps;
	}

	@Override
	public UserApi find(int id_empresa) throws DAOException, EmptyResultException {
		UserApi emp = userDAO.findEmpresa(id_empresa);

		return emp;
		
	}

	@Override
	public void update(String username, String fullname,String email, long id) throws DAOException {
		userDAO.update(username,fullname,email,id);
		
	}

	@Override
	public void delete(String nombre) throws DAOException {
		userDAO.delete(nombre);
		
	}

	

}
