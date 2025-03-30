package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	private static String stringConnection = "jdbc:mysql://localhost:3306/ouvidoriafase3";
	private static String user = "root";
	private static String password = "122333";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection(stringConnection, user, password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		return null;
	}
	public static void closeConnection (Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //adicionando comentario de exemplo, novo exemplo
}
