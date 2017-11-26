package com.tecsup.integrador.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tecsup.integrador.model.ParaderoApi;



public class ParaderoMapper implements RowMapper<ParaderoApi>{

	public ParaderoApi mapRow(ResultSet rs, int rowNum) throws SQLException {
		ParaderoApi prd = new ParaderoApi();
		prd.setId_paradero(rs.getInt("id_paradero"));
		prd.setNombre(rs.getString("nombre"));
		prd.setLatitud(rs.getDouble("latitud"));
		prd.setLongitud(rs.getDouble("longitud"));
	
		return prd;
	}
}
