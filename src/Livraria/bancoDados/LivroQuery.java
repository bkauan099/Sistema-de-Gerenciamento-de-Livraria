package Livraria.bancoDados;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Livraria.Cadastro.Livros;
import Livraria.interfaces.LivroRepositorio;

public class LivroQuery extends ConexaoBD implements LivroRepositorio {

    public LivroQuery(ConexaoBD dataBase) {
        this.dataBase = dataBase;
    }

    public void cadastrarLivro(Livros livro) {
        Connection conexao = dataBase.conectar();
        if (conexao != null) {
            try {
                String sql = "INSERT INTO livros (nome, categoria, autor) VALUES (?, ?, ?)";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                stmt.setString(1, livro.getTitulo());
                stmt.setString(2, livro.getCategoria());
                stmt.setString(3, livro.getAutor());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                dataBase.desconectar(conexao);
            }
        }
    }

    public void removerLivro(String nomeLivro) {
        Connection conexao = dataBase.conectar();
        if (conexao != null) {
            try {
                String buscaIdSql = "SELECT id_livro FROM livros WHERE nome = ?";
                PreparedStatement buscaIdStmt = conexao.prepareStatement(buscaIdSql);
                buscaIdStmt.setString(1, nomeLivro);
                ResultSet resultadoId = buscaIdStmt.executeQuery();

                if (resultadoId.next()) {
                    int idLivro = resultadoId.getInt("id_livro");
                    String verificaSql = "SELECT COUNT(*) FROM reservas WHERE id_livro = ?";
                    PreparedStatement verificaStmt = conexao.prepareStatement(verificaSql);
                    verificaStmt.setInt(1, idLivro);
                    ResultSet resultado = verificaStmt.executeQuery();
                    resultado.next();

                    if (resultado.getInt(1) == 0) {
                        String sql = "DELETE FROM livros WHERE id_livro = ?";
                        PreparedStatement stmt = conexao.prepareStatement(sql);
                        stmt.setInt(1, idLivro);
                        int rows = stmt.executeUpdate();

                        if (rows > 0) {
                            JOptionPane.showMessageDialog(null, "Livro removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Erro: Livro não encontrado.", "Erro", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro: O livro está associado a uma reserva e não pode ser removido.", "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: Livro não encontrado.", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao remover livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                dataBase.desconectar(conexao);
            }
        }
    }

    public List<Livros> consultarLivro(String consulta) {
        Connection conexao = dataBase.conectar();
        List<Livros> livroEncontrados = new ArrayList<>();
        if (conexao != null) {
            try {
                String sql = "SELECT * FROM livros WHERE nome LIKE ? OR autor LIKE ? OR categoria LIKE ?";
                PreparedStatement stmt = conexao.prepareStatement(sql);
                String busca = "%" + consulta + "%";
                stmt.setString(1, busca);
                stmt.setString(2, busca);
                stmt.setString(3, busca);
                ResultSet resultado = stmt.executeQuery();

                while (resultado.next()) {
                    Livros livro = new Livros(
                            resultado.getString("nome"),
                            resultado.getString("categoria"),
                            resultado.getString("autor")
                    );
                    livroEncontrados.add(livro);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao consultar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                dataBase.desconectar(conexao);
            }
        }
        return livroEncontrados;
    }

    public void atualizarLivro(String nomeAtual, String novoTitulo, String novaCategoria, String novoAutor) {
        Connection conexao = dataBase.conectar();
        if (conexao != null) {
            try {
                String buscaIdSql = "SELECT id_livro FROM livros WHERE nome = ?";
                PreparedStatement buscaIdStmt = conexao.prepareStatement(buscaIdSql);
                buscaIdStmt.setString(1, nomeAtual);
                ResultSet resultadoId = buscaIdStmt.executeQuery();

                if (resultadoId.next()) {
                    int idLivro = resultadoId.getInt("id_livro");
                    String sql = "UPDATE livros SET nome = ?, categoria = ?, autor = ? WHERE id_livro = ?";
                    PreparedStatement stmt = conexao.prepareStatement(sql);
                    stmt.setString(1, novoTitulo);
                    stmt.setString(2, novaCategoria);
                    stmt.setString(3, novoAutor);
                    stmt.setInt(4, idLivro);

                    int rows = stmt.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Livro atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro: Livro não encontrado.", "Erro", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Erro: Livro não encontrado.", "Erro", JOptionPane.WARNING_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao atualizar livro: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            } finally {
                dataBase.desconectar(conexao);
            }
        }
    }
}