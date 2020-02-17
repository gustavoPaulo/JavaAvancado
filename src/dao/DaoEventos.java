package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.BeanEventos;
import connection.SingleConnection;

public class DaoEventos {

	
	private Connection connection;

	public DaoEventos() {

		connection = SingleConnection.getConnection();
	}

		
	public List<BeanEventos> getEventos() throws Exception {
		List<BeanEventos> eventos = new ArrayList<BeanEventos>();
		
		String sql = " select * from eventos ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		
		while (resultSet.next()) {
			
			BeanEventos evento = new BeanEventos();
			evento.setId(resultSet.getLong("id"));
			evento.setDescricao(resultSet.getString("descricao"));
			evento.setDataevento(resultSet.getString("dataevento"));
			
			eventos.add(evento);
		}
		return eventos;
	}
}