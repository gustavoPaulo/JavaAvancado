package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCursoJsp;
import dao.DaoUsuario;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public CarregarDadosDataTable() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
		
		List<BeanCursoJsp> usuarios = daoUsuario.getUsuarios();
		
		if(!usuarios.isEmpty()) {

			String data = "";
			int totalUsuarios = usuarios.size();
			int index = 1;
			
			for(BeanCursoJsp usuario : usuarios) {
				
				data += "["+
					      "\""+usuario.getId()+"\","+
					      "\""+usuario.getLogin()+"\","+
					      "\""+usuario.getSenha()+"\""+
					    "]";
				
				if(index < totalUsuarios) {
					
					data += ",";
				}
				
				index++;
			}
			
		String json = "{"+
						 "\"draw\": 1,"+
						 "\"recordsTotal\": "+usuarios.size()+","+
						 "\"recordsFiltered\": "+usuarios.size()+","+
						 "\"data\": ["+data+"]"+
					   "}";
		
		
		response.setStatus(200); //Resposta completa OK
		response.getWriter().write(json); //Json de respota ao navegador
		
			}
		}catch(Exception e){
			
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
