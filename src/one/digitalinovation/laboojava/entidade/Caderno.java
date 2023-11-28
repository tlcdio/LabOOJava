package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materias;

import java.util.Objects;

/**
 * Classe que representa um caderno, qual é uma especialização de um produto da loja.
 * @author jhon klebson
 */
public class Caderno extends Produto{

    /**
     * Tipo do livro por quantidade de materias.
     */
    private Materias tipo;

    public Materias getTipo() {
        return tipo;
    }

    public void setTipo(Materias tipo) {
        this.tipo = tipo;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public double calcularFrete() {
        return (getPreco() * getQuantidade()) + tipo.getFator();
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "\ntipo='" + tipo + '\'' +
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
        Caderno caderno = (Caderno) obj;
        return tipo == caderno.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tipo);
    }
}
