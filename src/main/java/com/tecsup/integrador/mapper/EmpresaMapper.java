package com.tecsup.integrador.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tecsup.integrador.model.EmpresaApi;



public class EmpresaMapper implements RowMapper<EmpresaApi>{

	public EmpresaApi mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmpresaApi emp = new EmpresaApi();
		emp.setId_empresa(rs.getInt("id_empresa"));
		emp.setNombre(rs.getString("nombre"));
		emp.setDescripcion(rs.getString("descripcion"));
		return emp;
	}
}
