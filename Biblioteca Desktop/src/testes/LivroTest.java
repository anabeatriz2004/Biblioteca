package testes;

import org.junit.Test;
import static org.junit.Assert.*;

public class LivroTest {
    Livro livro = new Livro();

    @Test
    public void testeIsbnVazio() { livro.verificarIsbn(""); }

    @Test
    public void testeComIsbnValido() {
        livro.verificarIsbn("1234567890123");
    }

    @Test
    public void testeIsbnComLetras() {
        livro.verificarIsbn("abc1234567890");
    }

    @Test
    public void testeAutorVazio() {
        livro.verificarAutor("");
    }

    @Test
    public void testeAutorValido() {
        livro.verificarAutor("Eça de Queirós");
    }

    @Test
    public void testeEditoraVazio() {
        livro.verificarEditora("");
    }

    @Test
    public void testeEditoraValido() {
        livro.verificarEditora("Porto Editora");
    }

    @Test
    public void testeAnoPubliVazio() {
        livro.verificarAnoPubli("");
    }

    @Test
    public void testeAnoPubliComLetras() {
        livro.verificarAnoPubli("abc");
    }

    @Test
    public void testeAnoPubliValido() {
        livro.verificarAnoPubli("2024");
    }

    @Test
    public void testeGeneroVazio() {
        livro.verificarGenero("");
    }

    @Test
    public void testeGeneroValido() {
        livro.verificarGenero("Ficção");
    }

    @Test
    public void testeDisponibilidadeVazio() {
        livro.verificarDisponibilidade("");
    }

    @Test
    public void testeDisponibilidadeValido() {
        livro.verificarDisponibilidade("");
    }

    @Test
    public void testeDescricaoVazio() { livro.verificarDescricao(""); }

    @Test
    public void testeDescricaoValido() {
        livro.verificarDescricao("A fantasy trilogy that follows the quest to destroy the One Ring and defeat the Dark Lord Sauron.");
    }
}
