package com.tecsup.integrador.model;


public class UserApi {

    private long id;
    private String username;
    private String password;
    private String fulname;
    private String email;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getFulname() {
		return fulname;
	}

	public void setFulname(String fulname) {
		this.fulname = fulname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserApi [id=" + id + ", username=" + username + ", password=" + password + ", fulname=" + fulname
				+ ", email=" + email + "]";
	}

	
}
