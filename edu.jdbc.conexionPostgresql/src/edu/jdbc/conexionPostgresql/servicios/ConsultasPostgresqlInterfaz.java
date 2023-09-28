package edu.jdbc.conexionPostgresql.servicios;

import java.sql.Connection;
import java.util.ArrayList;

import edu.jdbc.conexionPostgresql.dtos.LibroDto;

/**
 * Interfaz que define los métodos de consulta a Postgresql
 * 220923 - rfg
 */
public interface ConsultasPostgresqlInterfaz {
	
	/**
	 * Método que realiza un select all sobre el catálogo de libros
	 * 220923 - rfg
	 * @param conexionGenerada
	 * @return lista de libros
	 */
	public ArrayList<LibroDto> seleccionaTodosLibros(Connection conexionGenerada);
	
}
