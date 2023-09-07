package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materias;

public class Caderno extends Produto {

    private Materias tipo;

    public Materias getTipo() {
        return tipo;
    }

    public void setTipo(String a) {

        Materias tipo = getTipo();

        switch (a) {
            case "M2":
                tipo = Materias.M2;
                break;
            case "M5":
                tipo = Materias.M5;
                break;
            case "M10":
                tipo = Materias.M10;
                break;
            default:

            System.out.println("Quantidade inválida!");
                return;
              

        }

        this.tipo = tipo;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public double calcularFrete() {
        return (getPreco() * getQuantidade()) * (1 + tipo.getFator());
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "Tipo='" + tipo.name() + '\'' +
                ", codigo='" + getCodigo() + '\'' +
                ", preço='" + getPreco() + '\'' +
                '}';
    }

}
