package dao;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        Part filePart = request.getPart("foto"); // Recupera o arquivo do formulário
        InputStream fileContent = filePart.getInputStream(); // Captura o conteúdo do arquivo como InputStream

        dto objdto = new dto();
        objdto.setNome(nome);

        JDBCContatoDAO dao = new JDBCContatoDAO();
        try {
            dao.adicionarAgoraVai(objdto, fileContent, (int) filePart.getSize()); // Chama o método para adicionar no banco de dados
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect("success.jsp"); // Redireciona para a página de sucesso
    }
}
