package Livraria.bancoDados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import Livraria.interfaces.ReservaRepositorio;

public class ReservaQuery extends ConexaoBD implements ReservaRepositorio {

    public ReservaQuery(ConexaoBD dataBase) {
        this.dataBase = dataBase;
    }

    public void realizarReserva(String nomeLivro, String nomeUsuario) {
        Connection conexao = dataBase.conectar();
        if (conexao != null) {
            try {
                String sqlUsuario = "SELECT id_usuario FROM usuarios WHERE nome LIKE ?";
                PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario);
                stmtUsuario.setString(1, "%" + nomeUsuario + "%");
                ResultSet resultadoUsuario = stmtUsuario.executeQuery();

                if (!resultadoUsuario.next()) {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int usuarioId = resultadoUsuario.getInt("id_usuario");

                String sqlLivro = "SELECT id_livro FROM livros WHERE nome LIKE ?";
                PreparedStatement stmtLivro = conexao.prepareStatement(sqlLivro);
                stmtLivro.setString(1, "%" + nomeLivro + "%");
                ResultSet resultadoLivro = stmtLivro.executeQuery();

                if (!resultadoLivro.next()) {
                    JOptionPane.showMessageDialog(null, "Livro não encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int livroId = resultadoLivro.getInt("id_livro");

                String sqlReservaVerificar = "SELECT * FROM reservas WHERE id_livro = ?";
                PreparedStatement stmtReservaVerificar = conexao.prepareStatement(sqlReservaVerificar);
                stmtReservaVerificar.setInt(1, livroId);
                ResultSet resultadoReserva = stmtReservaVerificar.executeQuery();

                if (resultadoReserva.next()) {
                    JOptionPane.showMessageDialog(null, "O livro já está reservado!", "Aviso",
                    JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String sqlReserva = "INSERT INTO reservas (id_livro, id_usuario) VALUES (?, ?)";
                PreparedStatement stmtReserva = conexao.prepareStatement(sqlReserva);
                stmtReserva.setInt(1, livroId);
                stmtReserva.setInt(2, usuarioId);
                stmtReserva.executeUpdate();

                JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar reserva: " + e.getMessage(), "Erro",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void removerReserva(String nomeLivro, String nomeUsuario) {
        Connection conexao = dataBase.conectar();
        if (conexao != null) {
            try {
                // Primeiro, busque o ID do usuário
                String sqlUsuario = "SELECT id_usuario FROM usuarios WHERE nome LIKE ?";
                PreparedStatement stmtUsuario = conexao.prepareStatement(sqlUsuario);
                stmtUsuario.setString(1, "%" + nomeUsuario + "%");
                ResultSet resultadoUsuario = stmtUsuario.executeQuery();
                
                if (!resultadoUsuario.next()) {
                    JOptionPane.showMessageDialog(null, "Usuário não encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int usuarioId = resultadoUsuario.getInt("id_usuario");

                String sqlLivro = "SELECT id_livro FROM livros WHERE nome LIKE ?";
                PreparedStatement stmtLivro = conexao.prepareStatement(sqlLivro);
                stmtLivro.setString(1, "%" + nomeLivro + "%");
                ResultSet resultadoLivro = stmtLivro.executeQuery();
    
                if (!resultadoLivro.next()) {
                    JOptionPane.showMessageDialog(null, "Livro não encontrado!", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int livroId = resultadoLivro.getInt("id_livro");

                String sql = "DELETE FROM reservas WHERE id_livro = ? AND id_usuario = ?";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, livroId);
                stmt.setInt(2, usuarioId);
                int rows = stmt.executeUpdate();
    
                if (rows > 0) {
                    System.out.println("Reserva removida com sucesso!");
                    JOptionPane.showMessageDialog(null, "Reserva removida com sucesso!");
                } else {
                    System.out.println("Reserva não encontrada.");
                    JOptionPane.showMessageDialog(null, "Reserva não encontrada.", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                System.out.println("Erro ao remover reserva: " + e.getMessage());
                JOptionPane.showMessageDialog(null, "Erro ao remover reserva: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                dataBase.desconectar(conexao);
            }
        }
    }
    
}
