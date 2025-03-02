package Livraria.interfaces;

import java.util.List;

import Livraria.Cadastro.Livros;

public interface LivroRepositorio {

    void cadastrarLivro(Livros livro);
    List<Livros> consultarLivro(String consulta);
    void removerLivro(String titulo);
    void atualizarLivro(String nomeAtual, String novoTitulo, String novaCategoria, String novoAutor);
}
