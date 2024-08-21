package br.com.gustavo.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryProduto {

private static Connection connection;
	
	private ConnectionFactoryProduto(Connection connection) {
		
	}
	
	public static Connection getConnection() throws java.sql.SQLException {
		if (connection == null) {
			connection = initConnection();
			return connection;
		} else if (connection.isClosed()) {
			connection = initConnection();
			return connection;
		} else {
			return connection;
		}
				
	}

	private static Connection initConnection() {
		try {
            return DriverManager.getConnection(
            		"jdbc:postgresql://localhost:5432/TestandoConexao", "postgres", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
