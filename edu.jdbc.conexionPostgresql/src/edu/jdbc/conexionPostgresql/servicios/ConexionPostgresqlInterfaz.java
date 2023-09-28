package edu.jdbc.conexionPostgresql.servicios;

import java.sql.Connection;

/**
 * Clase que realiza la conexión a PostgreSQL de forma parametrizada
 * 220923 - rfg
 */
public interface ConexionPostgresqlInterfaz {

	/**
	 * Método que genera la conexión a partir de la configuración guardada en 
	 * .properties
	 * 220923 - rfg
	 * @return Conexión a postgresql abierta
	 */
	public Connection generaConexion();
	
}
