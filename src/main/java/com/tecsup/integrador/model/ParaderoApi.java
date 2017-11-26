package com.tecsup.integrador.model;

public class ParaderoApi {
private long id_paradero;
private String nombre;
private Double longitud;
private Double latitud;


public long getId_paradero() {
	return id_paradero;
}
public void setId_paradero(long id_paradero) {
	this.id_paradero = id_paradero;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public Double getLongitud() {
	return longitud;
}
public void setLongitud(Double longitud) {
	this.longitud = longitud;
}
public Double getLatitud() {
	return latitud;
}
public void setLatitud(Double latitud) {
	this.latitud = latitud;
}
@Override
public String toString() {
	return "ParaderoApi [id_paradero=" + id_paradero + ", nombre=" + nombre + ", longitud=" + longitud + ", latitud="
			+ latitud + "]";
}





}
