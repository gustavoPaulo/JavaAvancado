package filter;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnection;
import user.UserLogado;


@WebFilter(urlPatterns = {"/pages/*"})
public class FilterAutenticacao implements Filter{

	private static Connection connection;
	
	//Faz alguma coisa quando a aplicação é derrubada
	@Override
	public void destroy() {
		
	}
	
	//Intercepta todas as requisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String urlParaAutenticar = req.getServletPath();
		
		
		//Retorna null caso não esteja logado
		UserLogado userLogado = (UserLogado) session.getAttribute("usuario");
		
		if(userLogado == null && !urlParaAutenticar.equalsIgnoreCase("/pages/ServletAutentica")) {//Usuario não logado
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/autenticar.jsp?url="+urlParaAutenticar);
			dispatcher.forward(request, response);
			
			return;//Para o processo para redirecionar
		}
		
		
		//Executa as ações do request e response
		chain.doFilter(request, response);
	}
	
	//Executa alguma coisa quando a aplicação é iniciada
	@Override
	public void init(FilterConfig arg0) throws ServletException{
		
		connection = SingleConnection.getConnection();
	}
}