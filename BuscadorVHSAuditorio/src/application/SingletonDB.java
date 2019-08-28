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
			sentencia = conexion.prepareStatement("SELECT codigo, contenido, fecha FROM vhs WHERE contenido LIKE ? OR fecha LIKE ? ORDER BY codigo");
			sentencia.setString(1, "%"+textoBuscar+"%");
			sentencia.setString(2, "%"+textoBuscar+"%");
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
