package servlet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

import beans.BeanCursoJsp;
import dao.DaoUsuario;


@WebServlet("/pages/fileUpload")
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();
	
    public FileUpload() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String acao = request.getParameter("acao");
		
			if(acao.equalsIgnoreCase("carregar")) {
				
				RequestDispatcher viDispatcher = request.getRequestDispatcher("upload.jsp");
				request.setAttribute("listaUserImagen", daoUsuario.getUsuarios());
				viDispatcher.forward(request, response);
				
			}else if(acao.equalsIgnoreCase("download")) {
				
				String iduser = request.getParameter("iduser");
				BeanCursoJsp imagem = daoUsuario.buscaImagem(iduser);
				
				if(imagem != null) {
					
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + imagem.getTipofile());
					
					
					//Pega somente a imagem pura da tela
					String imagemPura = imagem.getImagem().split(",")[1];
					
					//docodeBase64 -> Converte base64 em byte[]
					byte [] imageBytes = new Base64().decodeBase64(imagemPura);
					
					//Coloca os bytes em um Objeto de entrada -> is
					InputStream is = new ByteArrayInputStream(imageBytes);
					
					
					//INICIO -> escrever dados na resposta
					int read = 0;
					
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
					
					//FIM -> escrever dados na resposta
					
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
		//Usar variavel fileUpload para salvar no banco de dados
		String fileUpload = request.getParameter("fileUpload");
		//Neste momento faz o insert no banco de dados

		daoUsuario.gravarImagem(fileUpload);
		
		response.getWriter().write("Upload efetuado com sucesso!");
		
		}
		catch(Exception e) {
			response.getWriter().write("Erro: " + e);
		}
	}

}
