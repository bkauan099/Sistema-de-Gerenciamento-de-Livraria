package Livraria.Cadastro;
public class Livros {

    private String titulo;
    private String categoria;
    private String autor;

    public Livros(String titulo, String categoria, String autor){
        this.titulo = titulo;
        this.categoria = categoria;
        this.autor = autor;
    }

    public String getTitulo(){
        return titulo;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getAutor(){
        return autor;
    }
}
