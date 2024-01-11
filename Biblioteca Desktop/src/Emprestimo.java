import java.time.LocalDate;

public class Emprestimo {
    private int id_emprestimo;
    private int id_livro;
    private int id_utilizador;
    private int id_bibliotecario;
    private LocalDate data_emprestimo;
    private LocalDate data_devolucao;

    public int getId_emprestimo() {
        return id_emprestimo;
    }
    public int getId_livro() {
        return id_livro;
    }
    public int getId_utilizador() {
        return id_utilizador;
    }
    public int getId_bibliotecario() {
        return id_bibliotecario;
    }
    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }
    public LocalDate getData_devolucao() {
        return data_devolucao;
    }
    public void setId_emprestimo(int id_emprestimo) {
        this.id_emprestimo = id_emprestimo;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }
    public void setId_utilizador(int id_utilizador) {
        this.id_utilizador = id_utilizador;
    }
    public void setId_bibliotecario(int id_bibliotecario) {
        this.id_bibliotecario = id_bibliotecario;
    }
    public void setData_emprestimo(LocalDate data_emprestimo) {
        this.data_emprestimo = data_emprestimo;
    }
    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public Emprestimo(int id_emprestimo, int id_livro, int id_utilizador, int id_bibliotecario,
                      LocalDate data_emprestimo, LocalDate data_devolucao) {
        this.id_emprestimo = id_emprestimo;
        this.id_livro = id_livro;
        this.id_utilizador = id_utilizador;
        this.id_bibliotecario = id_bibliotecario;
        this.data_emprestimo = data_emprestimo;
        this.data_devolucao = data_devolucao;
    }

    
}
