package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Genero;

import java.util.Objects;

/**
 * Classe que representa um livro, qual é uma especialização de um produto da loja.
 * @author thiago leite
 */
public class Livro extends Produto {

    /**
     * Nome do livro.
     */
    private String nome;

    /**
     * Gênero do livro.
     */
    private Genero genero;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public double calcularFrete() {
        return (getPreco() * getQuantidade()) * (1 + genero.getFator());
    }

    @Override
    public String toString() {
        return "Livro{" +
                "\nnome='" + nome + '\'' +
                ", \ngenero=" + genero + '\'' +
                ", \ncodigo='" + getCodigo() + '\'' +
                ", \npreço='" + getPreco() + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Livro livro = (Livro) obj;
        return nome.equalsIgnoreCase(livro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
