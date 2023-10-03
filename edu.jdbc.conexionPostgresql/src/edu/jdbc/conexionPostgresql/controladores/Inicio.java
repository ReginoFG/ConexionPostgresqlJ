package edu.jdbc.conexionPostgresql.controladores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.jdbc.conexionPostgresql.dtos.LibroDto;
import edu.jdbc.conexionPostgresql.servicios.ConexionPostgresqlImplementacion;
import edu.jdbc.conexionPostgresql.servicios.ConexionPostgresqlInterfaz;
import edu.jdbc.conexionPostgresql.servicios.ConsultasPostgresqlImplementacion;
import edu.jdbc.conexionPostgresql.servicios.ConsultasPostgresqlInterfaz;

/**
 * Clase principal de la aplicación
 * 220923 - rfg
 */
public class Inicio {

	/**
	 * Método de acceso a la aplicación de consola
	 * @param args
	 */
	public static void main(String[] args) {
		
		ConexionPostgresqlInterfaz cpi = new ConexionPostgresqlImplementacion();
		ConsultasPostgresqlInterfaz consultaspi = new ConsultasPostgresqlImplementacion();
		ArrayList<LibroDto> listaLibros = new ArrayList<>();
		
		try {
			Connection conexion = cpi.generaConexion();
			
			if(conexion != null) {
				listaLibros = consultaspi.seleccionaTodosLibros(conexion);
				for(int i=0;i<listaLibros.size();i++) {
					System.out.println(listaLibros.get(i).toString());
				}
			}	
			
		} catch (Exception e) {
			System.err.println("[ERROR-Main] Se ha producido un error al ejecutar la aplicación: " + e);
		}
		
	
		
	}
	
	

}
