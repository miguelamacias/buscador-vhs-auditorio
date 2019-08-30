package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingletonDB {
	public static SingletonDB instancia = new SingletonDB();
	
	
	public SingletonDB() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String buscarTexto(String textoBuscar) {
		StringBuilder listado = new StringBuilder();
		
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:vhs.db");
			
			//Crear la la string SQL con el número de parámetros adecuados
			String[] palabras = textoBuscar.split(" ");
			StringBuilder query = new StringBuilder("SELECT codigo, contenido, fecha FROM vhs WHERE contenido LIKE ? ");
			for (int i = 0; i < palabras.length - 1; i++) {
				query.append("AND contenido LIKE ? ");				
			}
			
			//Añadir otros parámetros extra
			query.append("OR fecha LIKE ? ORDER BY codigo");
			
			//preparar la sentencia
			sentencia = conexion.prepareStatement(query.toString());
			
			for (int i = 0; i < palabras.length; i++) {
				sentencia.setString(i + 1, "%"+palabras[i]+"%");
				int suma = i+1;
				System.out.println(palabras[i] + " " + suma);				
			}
			
			//Poner la fecha
			sentencia.setString(palabras.length + 1, "%"+textoBuscar+"%");
			
			resultado = sentencia.executeQuery();
			
			
			while (resultado.next()) {
				if (resultado.getString(2).length() > 86) {
					String contenido = resultado.getString(2);
					contenido = contenido.substring(0, 82) + "...";
					
					listado.append(String.format("%-6s%-86s%s%n", resultado.getInt(1), contenido, resultado.getString(3)));
				} else {
					listado.append(String.format("%-6s%-86s%s%n", resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		
		return listado.toString();
	}
	
	public String buscarCodigo(String codigoBuscar) {
		StringBuilder listado = new StringBuilder();
		
		Connection conexion = null;
		PreparedStatement sentencia = null;
		ResultSet resultado = null;
		
		try {
			conexion = DriverManager.getConnection("jdbc:sqlite:vhs.db");
			sentencia = conexion.prepareStatement("SELECT codigo, contenido, fecha FROM vhs WHERE codigo = ?");
			sentencia.setString(1, codigoBuscar);
			resultado = sentencia.executeQuery();
			
			
			while (resultado.next()) {
				listado.append(String.format("%-10s%-82s%s%n", resultado.getInt(1), resultado.getString(2), resultado.getString(3)));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}	
		}
		
		return listado.toString();
	}
	
	public static SingletonDB getInstancia() {
		return instancia;
	}
}
