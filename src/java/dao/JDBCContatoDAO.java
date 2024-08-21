package dao;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import dao.uploadDao;
import dao.dto;
import java.io.InputStream;
import javax.swing.JFileChooser;

public class JDBCContatoDAO extends uploadDao {

    private FileInputStream fis;
    private int tamanho;
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<dto> lista = new ArrayList<>();

    private void adicionarAgoraVait(dto objdto) {
        String insert = "insert into alunos(nome,foto) values (?,?)";
        try {
            con = new uploadDao().conexaoBD();
            pstm = con.prepareStatement(insert);
            pstm.setString(1, objdto.getNome());
            pstm.setBlob(2, fis, tamanho);
        } catch (Exception e) {
        }
    }

    private void carregarFoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Escolha uma imagem para carregar");

        // Filtra apenas arquivos de imagem
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif"));

        int escolha = fileChooser.showOpenDialog(null);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            try {
                // Carrega a imagem como FileInputStream
                fis = new FileInputStream(arquivoSelecionado);
                // Obtém o tamanho do arquivo
                tamanho = (int) arquivoSelecionado.length();
                System.out.println("Imagem carregada com sucesso: " + arquivoSelecionado.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("Erro ao carregar a imagem.");
            }
        } else {
            System.out.println("Nenhuma imagem foi selecionada.");
        }
    }

  



    
   public void adicionarAgoraVai(dto objdto, InputStream imagem, int tamanho) throws ClassNotFoundException {
        String insert = "INSERT INTO alunos (nome, foto) VALUES (?, ?)";
        try {
            con = new uploadDao().conexaoBD();
            pstm = con.prepareStatement(insert);
            pstm.setString(1, objdto.getNome());
            pstm.setBlob(2, imagem, tamanho); // Passa o InputStream e o tamanho da imagem
            pstm.executeUpdate(); // Executa a query
        } catch (SQLException e) {
            e.printStackTrace(); // Exibe erros no console para debug
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}




/*String comando = "Insert into contato (nome, telefone, celular, dataNascimento, endereco, cidade, estado, imagem)"
                + " values (?,?,?,?,?,?,?,?)";

        File arquivo = new File(
                "C:/Users/WILLIANBALDEZ.AP/Documents/Desenvolvimento/Agenda/WebContent/Public/FOTO.jpg");

        PreparedStatement p;

        try {

            FileInputStream inputStream = new FileInputStream(arquivo);

            p = this.conexao.prepareStatement(comando);

            p.setString(1, contato.getNome());

            p.setString(2, contato.getTelefone());

            p.setString(3, contato.getCelular());

            p.setDate(4, new java.sql.Date(contato.getDataNascimento()
                    .getTime()));

            p.setString(5, contato.getEndereco());

            p.setString(6, contato.getCidade());

            p.setString(7, contato.getEstado());

            p.setBinaryStream(8, inputStream, (int) arquivo.length());

            p.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }*/
