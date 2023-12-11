import java.sql.*;

public class Livro {
    private int id_livro;
    private String ISBN;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPubli;
    private String genero;
    private boolean disponibilidade;

    public Livro () {}

    // Construtor
    public Livro(int id_livro, String ISBN, String titulo, String autor, String editora, int anoPubli, String genero, boolean disponibilidade) {
        this.id_livro = id_livro;
        this.ISBN = ISBN;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPubli = anoPubli;
        this.genero = genero;
        this.disponibilidade = disponibilidade;
    }

    // Métodos getter e setter para cada atributo
    public int getID_livro() {
        return id_livro;
    }

    public void setID_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPubli() {
        return anoPubli;
    }

    public void setAnoPubli(int anoPubli) {
        this.anoPubli = anoPubli;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
}
