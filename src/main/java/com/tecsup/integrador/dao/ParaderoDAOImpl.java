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
import com.tecsup.integrador.mapper.ParaderoMapper;
import com.tecsup.integrador.model.ParaderoApi;
@Repository
public class ParaderoDAOImpl implements ParaderoDAO{
	private static final Logger logger = LoggerFactory.getLogger(ParaderoDAOImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public ParaderoApi findEmpresa(int id_empresa) throws DAOException, EmptyResultException {

		String query = "SELECT id_paradero,nombre, latitud, longitud "
				+ " FROM paraderos WHERE id_paradero = ?";

		Object[] params = new Object[] { id_empresa };

		try {

			ParaderoApi prd = (ParaderoApi) jdbcTemplate.queryForObject(query, params, new ParaderoMapper());
			//
			return prd;
			//return null;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	@Override
	public List<ParaderoApi> findAllEmpresas() throws DAOException, EmptyResultException {
		String query = "SELECT id_paradero,nombre,latitud,longitud FROM paraderos";

		try {

			List<ParaderoApi> paraderos = jdbcTemplate.query(query, new ParaderoMapper());
			//
			return paraderos;

		} catch (EmptyResultDataAccessException e) {
			throw new EmptyResultException();
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}
	
	@Override
	public void create(String nombre, Double latitud, Double longitud) throws DAOException {

		String query = "INSERT INTO paraderos (nombre,latitud,longitud)  VALUES ( ?,?,?)";

		Object[] params = new Object[] { nombre,latitud,longitud};

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

		String query = "DELETE FROM  paraderos WHERE nombre = ? ";

		Object[] params = new Object[] { nombre };

		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void update(String nombre, Double latitud, Double longitud, long id_empresa) throws DAOException {

//		String query = "UPDATE employees SET password = ?, first_name =?, last_name = ?, salary = ?, department_id = ? WHERE login = ?";
//
//		Object[] params = new Object[] { password, lastname, firstname, salary, dptId, login };

		String query = "UPDATE paraderos SET nombre = ?, latitud =?, longitud =? WHERE id_paradero = ?";

		Object[] params = new Object[] { nombre,latitud, longitud, id_empresa};

		
		try {
			jdbcTemplate.update(query, params);
		} catch (Exception e) {
			logger.info("Error: " + e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

}
