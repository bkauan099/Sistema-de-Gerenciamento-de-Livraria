package Livraria.Serviços;

import Livraria.Cadastro.Usuario;
import Livraria.interfaces.UsuarioRepositorio;

public class UsuarioServico {
    private UsuarioRepositorio usuarioRepositorio;

    public UsuarioServico(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public void cadastrarUsuario(String nome, String email, String telefone) {
        Usuario usuario = new Usuario(nome, email, telefone);
        usuarioRepositorio.cadastrarUsuario(usuario);
    }
    public void removerUsuario(String nome) {
        usuarioRepositorio.removerUsuario(nome);
    }
    
}
