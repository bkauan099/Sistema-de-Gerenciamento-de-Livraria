package Livraria.Serviços;

import java.util.List;
import Livraria.Cadastro.Livros;
import Livraria.interfaces.LivroRepositorio;

public class LivroServico{
    private LivroRepositorio livroRepositorio;

    public LivroServico(LivroRepositorio livroRepositorio) {
        this.livroRepositorio = livroRepositorio;
    }

    public void cadastrarLivro(String titulo, String categoria, String autor) {
        Livros livro = new Livros(titulo, categoria, autor);
        livroRepositorio.cadastrarLivro(livro);
    }

    public String consultarLivros(String consulta) {
        List<Livros> livrosEncontrados = livroRepositorio.consultarLivro(consulta);
        if (livrosEncontrados.isEmpty()) {
            return "Nenhum livro encontrado.";
        } else {
            StringBuilder resultado = new StringBuilder();
            for (Livros livro : livrosEncontrados) {
                resultado.append("Título: ").append(livro.getTitulo())
                        .append(", Categoria: ").append(livro.getCategoria())
                        .append(", Autor: ").append(livro.getAutor()).append("\n");
            }
            return resultado.toString();
        }
    }
    public void removerLivro(String titulo) {
        livroRepositorio.removerLivro(titulo);
    }
    public void atualizarLivro(String nomeAtual, String novoTitulo, String novaCategoria, String novoAutor) {
        livroRepositorio.atualizarLivro(nomeAtual, novoTitulo, novaCategoria, novoAutor);
    }
}
