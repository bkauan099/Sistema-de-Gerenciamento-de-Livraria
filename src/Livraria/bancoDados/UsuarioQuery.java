package Livraria.bancoDados;

import java.sql.*;
import javax.swing.JOptionPane;
import Livraria.Cadastro.Usuario;
import Livraria.interfaces.UsuarioRepositorio;

public class UsuarioQuery extends ConexaoBD implements UsuarioRepositorio {

    public UsuarioQuery(ConexaoBD dataBase) {
        this.dataBase = dataBase;
    }
    
    public void cadastrarUsuario(Usuario usuario) {
        Connection conexao = dataBase.conectar();
        if (conexao != null) {
            try {
                String sql = "INSERT INTO usuarios (nome, email, telefone) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getEmail());
                stmt.setString(3, usuario.getTelefone());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                dataBase.desconectar(conexao);
            }
        }
    }

    public void removerUsuario(String nomeUsuario) {
        Connection conexao = dataBase.conectar();
        if (conexao != null) {
            try {
                String buscaIdSql = "SELECT id_usuario FROM usuarios WHERE nome = ?";
                PreparedStatement buscaIdStmt = conexao.prepareStatement(buscaIdSql);
                buscaIdStmt.setString(1, nomeUsuario);
                ResultSet resultadoId = buscaIdStmt.executeQuery();

                if (resultadoId.next()) {
                    int idUsuario = resultadoId.getInt("id_usuario");

                    String verificaSql = "SELECT COUNT(*) FROM reservas WHERE id_usuario = ?";
                    PreparedStatement verificaStmt = conexao.prepareStatement(verificaSql);
                    verificaStmt.setInt(1, idUsuario);
                    ResultSet resultado = verificaStmt.executeQuery();
                    resultado.next();

                    if (resultado.getInt(1) == 0) {
                        String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
                        PreparedStatement stmt = conexao.prepareStatement(sql);
                        stmt.setInt(1, idUsuario);
                        int rows = stmt.executeUpdate();

                        if (rows > 0) {
                            JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro: Usuário não encontrado.", "Erro", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro: O usuário possui reservas ativas e não pode ser removido.", "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: Usuário não encontrado.", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao remover usuário: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                dataBase.desconectar(conexao);
            }
        }
    }
}