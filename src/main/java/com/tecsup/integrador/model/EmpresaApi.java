package com.tecsup.integrador.model;

public class EmpresaApi {
private long id_empresa;
private String nombre;
private String descripcion;
public long getId_empresa() {
	return id_empresa;
}
public void setId_empresa(long id_empresa) {
	this.id_empresa = id_empresa;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
@Override
public String toString() {
	return "EmpresaApi [id_empresa=" + id_empresa + ", nombre=" + nombre + ", descripcion=" + descripcion + "]";
}


}
