package one.digitalinovation.laboojava.entidade;

/**
 * Classe que representa a entidade cupom. Este pode ser utilizado no ato do fechamento do pedido
 * para obter um desconto, caso desejado.
 * @author thiago leite
 */
public class Cupom {

    /**
     * Código descritivo do cupom.
     */
    private String codigo;

    /**
     * Valor em porcentagem do desconto.
     */
    private int desconto;

    /**
     * Construtor do cupom
     * @param codigo Código do cupom
     * @param desconto Porcentagem de desconto
     */
    public Cupom(String codigo, int desconto) {
        this.codigo = codigo;
        this.desconto = desconto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

}
