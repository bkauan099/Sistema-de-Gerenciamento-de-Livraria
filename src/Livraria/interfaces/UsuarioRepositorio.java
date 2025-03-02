package Livraria.interfaces;
import Livraria.Cadastro.*;
public interface UsuarioRepositorio {
    void cadastrarUsuario(Usuario usuario);
    void removerUsuario(String nome);

}
