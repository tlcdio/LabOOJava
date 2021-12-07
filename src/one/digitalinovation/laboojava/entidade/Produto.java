package one.digitalinovation.laboojava.entidade;

/**
 * Classe que representa a abstração dos produtos que podem ser vendidos pela loja.
 * @author thiago leite
 */
public abstract class Produto {

    /**
     * Código de identiticação do produto.
     */
    private String codigo;

    /**
     * Valor unitário do produto.
     */
    private double preco;

    /**
     * Quantidade comprada do produto.
     */
    private int quantidade;

    public Produto() {}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Calcula o preço do frete para os produtos comprados. Este cálculo pode
     * variar de acordo com o produto
     * @return valor do frete para o determinado produto
     */
    //TODO Método de cálculo de frete
}
