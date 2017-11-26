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
import com.tecsup.integrador.mapper.EmpresaMapper;
import com.tecsup.integrador.model.EmpresaApi;
@Repository
public class EmpresaDAOImpl implements EmpresaDAO{
	private static final Logger logger = LoggerFactory.getLogger(EmpresaDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public EmpresaApi findEmpresa(int id_empresa) throws DAOException, EmptyResultException {

		String query = "SELECT id_empresa,nombre, descripcion "
				+ " FROM empresas WHERE id_empresa = ?";

		Object[] params = new Object[] { id_empresa };

		try {

			EmpresaApi emp = (EmpresaApi) jdbcTemplate.queryForObject(query, params, new EmpresaMapper());
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
	public List<EmpresaApi> findAllEmpresas() throws DAOException, EmptyResultException {
		String query = "SELECT id_empresa,nombre, descripcion FROM empresas";

		try {

			List<EmpresaApi> empresas = jdbcTemplate.query(query, new EmpresaMapper());
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
	public void create(String nombre, String descripcion) throws DAOException {

		String query = "INSERT INTO empresas (nombre, descripcion)  VALUES ( ?,?)";

		Object[] params = new Object[] { nombre, descripcion};

		//Employee emp = null;
		
		try {
			// create
			jdbcTemplate.update(query, params);

		} catch (Exception e) {
			//logger.error("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
		

	}

	@Override
	public void delete(String nombre) throws DAOException {

		String query = "DELETE FROM  empresas WHERE nombre = ? ";

		Object[] params = new Object[] { nombre };

		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void update(String nombre, String descripcion, long id_empresa) throws DAOException {

//		String query = "UPDATE employees SET password = ?, first_name =?, last_name = ?, salary = ?, department_id = ? WHERE login = ?";
//
//		Object[] params = new Object[] { password, lastname, firstname, salary, dptId, login };

		String query = "UPDATE empresas SET nombre = ?, descripcion =? WHERE id_empresa = ?";

		Object[] params = new Object[] { nombre, descripcion, id_empresa};

		
		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

}
