package com.tecsup.integrador.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tecsup.integrador.model.UserApi;



public class UserMapper implements RowMapper<UserApi>{

	public UserApi mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserApi emp = new UserApi();
		emp.setId(rs.getInt("id"));
		emp.setUsername(rs.getString("username"));
		emp.setFulname(rs.getString("fullname"));
		emp.setEmail(rs.getString("email"));
		return emp;
	}
}
