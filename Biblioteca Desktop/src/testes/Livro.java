package testes;

public class Livro {
    public void verificarIsbn(String isbnStr) {
        // verifica se o isbn é inserido corretamente
        if (isbnStr.isEmpty()) { // verifica se não contém dados
            System.out.println("Isbn sem dados!"); // avisa que está vazio
        } else if (isbnStr.matches("\\d{13}")) { // verifica se têm 13 números
            System.out.println("ISBN inserido corretamente!");
        } else {
            if (isbnStr.matches(".*\\D.*")) { // caso contrário, verifica se tem dados não númericos
                System.out.println("O ISBN só pode conter números!"); // avisa que só pode ter letras
            } else {
                System.out.println("Confirme se tem 13 números!"); // caso contrário, diz para confirmar se têm realmente 13 dígitos
            }
        }
    }

    public void verificarTitulo(String titulo) {
        // verifica se o título é inserido corretamente
        if (titulo.isEmpty()) { // verifica se não contém dados
            System.out.println("Título sem dados!");
        } else { // caso contrário, verifica se contém dados
            System.out.println("Título inserido corretamente!");
        }
    }

    public void verificarAutor(String autor) {
        // verifica se o autor é inserido corretamente
        if (autor.isEmpty()) { // verifica se não contém dados
            System.out.println("Autor sem dados!"); // avisa que está sem dados
        } else { // caso contrário, verifica se contém dados
            System.out.println("Autor inserido corretamente!");
        }
    }

    public void verificarEditora(String editora) {
        // verifica se a editora é inserida corretamente
        if (editora.isEmpty()) { // verifica se a editora é inserida corretamente
            System.out.println("Editora sem dados!");
        } else { // caso contrário, verifica se contém dados
            System.out.println("Editora inserido corretamente!");
        }
    }

    public void verificarAnoPubli(String anoPubliStr) {
        // criação de componentes de acordo como serão inseridos na base de dados
        int anoPubli = 0;

        // Vê se o anoPubli é inserido corretamente
        if (anoPubliStr.isEmpty()) { // verifica se contém dados
            System.out.println("Ano de publicação sem dados!");
        } else { // caso contrário...
            try {
                // Tenta converter a string anoPubliStr para um número inteiro
                anoPubli = Integer.parseInt(anoPubliStr);

                // Verificar se está dentro de um intervalo específico (por exemplo, 0 a 2024)
                if (anoPubli >= 0 && anoPubli <= 2024) {
                    System.out.println("Ano de publicação inserido corretamente!");
                } else { // caso não estiver, dentro do intervalo especificado
                    System.out.println("O ano de publicação deve estar entre 0 e 2024!");
                }
            } catch (NumberFormatException e) { // se caso ocorrer um erro em tentar tornar uma string em um número
                System.out.println("Confirme se inseriu um número válido para o ano de publicação!");
            }
        }
    }

    public void verificarGenero(String genero) {
        // verifica se o gênero é inserido corretamente
        if (genero.isEmpty()) { // verifica se o gênero está vazio
            System.out.println("Gênero sem dados!");
        } else { // caso contrário...
            System.out.println("Gênero inserido corretamente!");
        }
    }

    public void verificarDisponibilidade(String disponibilidadeStr) {
        boolean disponibilidade = true; // está disponível por defeito

        // verifica se o estado da disponibilidade
        if (disponibilidadeStr.equals("Disponível")) { // verifica se está disponíveç
            System.out.println("Declarou que o livro se encontra disponivel.");
            disponibilidade = true; // afirma que está disponível
        } else if (disponibilidadeStr.equals("Emprestado")) { // verifica se está emprestado
            System.out.println("Declarou que o livro foi emprestado.");
            disponibilidade = false;
        }
    }

    public void verificarDescricao(String descricao) {
        // verifica se a descrição foi incerido corretamente
        if (descricao.isEmpty()) { // verifica se a descrição está vazia
            System.out.println("Descrição sem dados!");
        } else { // caso contrário...
            System.out.println("Descrição inserido corretamente!");// dado válido, pode ser inserido
        }
    }
}