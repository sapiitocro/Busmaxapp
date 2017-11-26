package com.tecsup.integrador.dao;

import java.util.List;


import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.model.UserApi;



public interface UserDAO {
	List<UserApi> findAllEmpresas() throws DAOException, EmptyResultException ;
	UserApi findEmpresa(int id) throws DAOException, EmptyResultException;
	void delete(String username) throws DAOException;

	void update(String username, String fullname,String email, long id) throws DAOException;
}
