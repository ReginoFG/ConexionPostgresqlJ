package edu.jdbc.conexionPostgresql.servicios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Implementación de la interfaz de conexión a PostgreSQL
 * 220923 - rfg
 */
public class ConexionPostgresqlImplementacion implements ConexionPostgresqlInterfaz {

	@Override
	public Connection generaConexion() {
		
		Connection conexion = null;
		String[] parametrosConexion = configuracionConexion(); //url, user, pass
		
		if(!parametrosConexion[2].isEmpty()) { //Se controla que los parámetros de conexión se completen
			try {
				//Instancia un objeto de la clase interfaz que se le pasa
				Class.forName("org.postgresql.Driver");
				
				//Se establece la conexión
				//Si pgadmin no tiene abierta la bd, no será posible establecer conexion contra ella
				conexion = DriverManager.getConnection(parametrosConexion[0],parametrosConexion[1],parametrosConexion[2]);
				boolean esValida = conexion.isValid(50000);
				if(esValida == false) {
					conexion = null;
				}
				System.out.println(esValida ? "[INFORMACIÓN-ConexionPostgresqlImplementacion-generaConexion] Conexión a PostgreSQL válida" : "[ERROR-ConexionPostgresqlImplementacion-generaConexion] Conexión a PostgreSQL no válida");
	            return conexion;
	            
			} catch (ClassNotFoundException cnfe) {
				System.out.println("[ERROR-ConexionPostgresqlImplementacion-generaConexion] Error en registro driver PostgreSQL: " + cnfe);
				return conexion = null;
			} catch (SQLException jsqle) {
				System.out.println("[ERROR-ConexionPostgresqlImplementacion-generaConexion] Error en conexión a PostgreSQL (" + parametrosConexion[0] + "): " + jsqle);
				return conexion = null;
			}
			
		}else {
			System.out.println("[ERROR-ConexionPostgresqlImplementacion-generaConexion] Los parametros de conexion no se han establecido correctamente");	
			return conexion;
		}
		
	}

	
	/**
	 * Método configura los parámetros de la conexión a PostgreSQL a partir del .properties
	 * 220923 - rfg
	 * @param user
	 * @param pass
	 * @param port
	 * @param host
	 * @param db
	 * return parámetros para abrir la conexión
	 */
	private String[] configuracionConexion() {
		
		String user="", pass="", port="", host="", db="", url="";	
		
		Properties propiedadesConexionPostgresql = new Properties();
		try {
			//propiedadesConexionPostgresql.load(getClass().getResourceAsStream("conexion_postgresql.properties"));
			propiedadesConexionPostgresql.load(new FileInputStream(new File("C:\\Users\\Trabajo\\dws-workspace\\edu.jdbc.conexionPostgresql\\src\\edu\\jdbc\\conexionPostgresql\\util\\conexion_postgresql.properties")));
			user = propiedadesConexionPostgresql.getProperty("user");
			pass = propiedadesConexionPostgresql.getProperty("pass");
			port = propiedadesConexionPostgresql.getProperty("port");
			host = propiedadesConexionPostgresql.getProperty("host");
			db = propiedadesConexionPostgresql.getProperty("db");
			url = "jdbc:postgresql://" + host + ":" + port + "/" + db;
			String[] stringConfiguracion = {url,user,pass};
			
			return stringConfiguracion;
			
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR-ConexionPostgresqlImplementacion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");
			return null;
		} catch (IOException e) {
			System.out.println("[ERROR-ConexionPostgresqlImplementacion-configuracionConexion] - Error al acceder al fichero propiedades de conexion.");
			return null;
		}

	}
}
