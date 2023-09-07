package one.digitalinovation.laboojava.entidade;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa a entidade pedido, qual é a compra dos produtos por um
 * cliente.
 * 
 * @author thiago leite
 */
public class Pedido {

    private String codigo;
    private Cliente cliente;
    private List<Produto> produtos;
    private Double total;

    public Pedido() { // cosntrutor

        this.produtos = new ArrayList<>();
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setProdutos(List<Produto> list) {
        produtos = list;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCodigo() {
        return codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public Double getTotal() {
        return total;
    }

    public String getProdutosComprados() {
        
        StringBuilder produtos = new StringBuilder();
        produtos.append("[");
        for (Produto produto : getProdutos()) {
            produtos.append(produto.toString());
            produtos.append("Qtd:");
            produtos.append(produto.getQuantidade());
            produtos.append(" ");

        }
        produtos.append("] ");
        return produtos.toString();

    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo='" + codigo + '\'' +
                ", cliente=" + cliente +
                ", produtos=" + getProdutosComprados() +
                ", total=" + total +
                '}';

    }



}
