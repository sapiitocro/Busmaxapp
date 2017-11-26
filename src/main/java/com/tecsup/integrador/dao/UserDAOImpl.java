package com.tecsup.integrador.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.tecsup.integrador.exception.DAOException;
import com.tecsup.integrador.exception.EmptyResultException;
import com.tecsup.integrador.mapper.UserMapper;
import com.tecsup.integrador.model.UserApi;
@Repository
public class UserDAOImpl implements UserDAO{
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public UserApi findEmpresa(int id_empresa) throws DAOException, EmptyResultException {

		String query = "SELECT id,username,fullname,email "
				+ " FROM usuarios WHERE id = ?";

		Object[] params = new Object[] { id_empresa };

		try {

			UserApi emp = (UserApi) jdbcTemplate.queryForObject(query, params, new UserMapper());
			//
			return emp;
			//return null;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	@Override
	public List<UserApi> findAllEmpresas() throws DAOException, EmptyResultException {
		String query = "SELECT id,username, fullname,email FROM usuarios";

		try {

			List<UserApi> empresas = jdbcTemplate.query(query, new UserMapper());
			//
			return empresas;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	

	@Override
	public void delete(String username) throws DAOException {

		String query = "DELETE FROM  usuarios WHERE username = ? ";

		Object[] params = new Object[] { username };

		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void update(String username, String fullname,String email, long id) throws DAOException {

//		String query = "UPDATE employees SET password = ?, first_name =?, last_name = ?, salary = ?, department_id = ? WHERE login = ?";
//
//		Object[] params = new Object[] { password, lastname, firstname, salary, dptId, login };

		String query = "UPDATE usuarios SET username = ?, fullname =?, email =? WHERE id = ?";

		Object[] params = new Object[] { username, fullname,email,id};

		
		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

}
