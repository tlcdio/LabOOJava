package one.digitalinovation.laboojava.entidade.constantes;

/**
 * Quantidade de materias dos cadernos vendidos.
 * @author jhon klebson
 */
public enum Materias {

    M2(2),
    M5(5),
    M10(10);

    private int fator;

    /**
     * Construtor.
     * @param fator Valor por tipo que influencia no cálculo do frete.
     */
    Materias(int fator) {this.fator = fator;};

    public int getFator() {
        return fator;
    }
}
