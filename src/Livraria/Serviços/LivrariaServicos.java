package Livraria.Serviços;

import Livraria.bancoDados.ConexaoBD;
import Livraria.bancoDados.LivroQuery;
import Livraria.bancoDados.ReservaQuery;
import Livraria.bancoDados.UsuarioQuery;

public class LivrariaServicos {
    private LivroServico livroServico;
    private UsuarioServico usuarioServico;
    private ReservaServico reservaServico;

    public LivrariaServicos(ConexaoBD conexao) { 
        this.livroServico = new LivroServico(new LivroQuery(conexao));
        this.usuarioServico = new UsuarioServico(new UsuarioQuery(conexao));
        this.reservaServico = new ReservaServico(new ReservaQuery(conexao));
    }

    public void cadastrarLivro(String titulo, String categoria, String autor) {
        livroServico.cadastrarLivro(titulo, categoria, autor);
    }

    public String consultarLivros(String consulta) {
        return livroServico.consultarLivros(consulta);
    }

    public void cadastrarUsuario(String nome, String email, String telefone) {
        usuarioServico.cadastrarUsuario(nome, email, telefone);
    }

    public void realizarReserva(String nomeLivro, String nomeUsuario) {
        reservaServico.realizarReserva(nomeLivro, nomeUsuario);
    }
    public void removerLivro(String titulo) {
        livroServico.removerLivro(titulo);
    }
    
    public void removerUsuario(String nome) {
        usuarioServico.removerUsuario(nome);
    }
    
    public void removerReserva(String nomeLivro, String nomeUsuario) {
        reservaServico.removerReserva(nomeLivro, nomeUsuario);
    }

    public void atualizarLivro(String nomeAtual, String novoTitulo, String novaCategoria, String novoAutor) {
        livroServico.atualizarLivro(nomeAtual, novoTitulo, novaCategoria, novoAutor);
    }

    
}
