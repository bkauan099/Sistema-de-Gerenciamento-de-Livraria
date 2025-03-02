package Livraria.Serviços;

import Livraria.interfaces.ReservaRepositorio;

public class ReservaServico {
    private ReservaRepositorio reservaRepositorio;

    public ReservaServico(ReservaRepositorio reservaRepositorio) {
        this.reservaRepositorio = reservaRepositorio;
    }

    public void realizarReserva(String nomeLivro, String nomeUsuario) {
        reservaRepositorio.realizarReserva(nomeLivro, nomeUsuario);
    }
    public void removerReserva(String nomeLivro, String nomeUsuario) {
        reservaRepositorio.removerReserva(nomeLivro, nomeUsuario);
    }
    
}
