package com.tecsup.integrador.services;

import java.util.List;


import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.UserApi;



public interface UserService {
	UserApi find(int id) throws DAOException, EmptyResultException;

	List<UserApi> findAll() 
			throws DAOException, EmptyResultException; 

	

	void update(String username, String fullname,String email, long id)
			throws DAOException;

	void delete(String username) throws DAOException;

	
}
