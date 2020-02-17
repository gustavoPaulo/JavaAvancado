package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import connection.SingleConnection;

public class DaoCalculaDataFinal {
	
	
	private Connection connection;
	
	public DaoCalculaDataFinal() {

		connection = SingleConnection.getConnection();
	}
	
	
	public void gravaDataFinal(String date) throws Exception {
		
		String sql = "INSERT INTO finalprojetos (datafinal) VALUES (?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		
		preparedStatement.setString(1, date);
		preparedStatement.execute();
	}
}