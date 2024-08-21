package br.com.gustavo.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

	private static Connection connection;
	
	private ConnectionFactory(Connection connection) {
		
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
            		"jdbc:postgresql://localhost:5432/TestandoConexao", "postgres", "g230395");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}
}
