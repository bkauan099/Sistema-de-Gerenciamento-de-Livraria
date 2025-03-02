package Livraria.interfaces;

public interface ReservaRepositorio {
    void realizarReserva(String nomeLivro, String nomeUsuario);
    void removerReserva(String nomeLivro, String nomeUsuario);

}
