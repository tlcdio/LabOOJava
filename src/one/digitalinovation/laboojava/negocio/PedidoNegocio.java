package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;
import one.digitalinovation.laboojava.entidade.Cupom;
import one.digitalinovation.laboojava.entidade.Pedido;
import one.digitalinovation.laboojava.entidade.Produto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Pedido}.
 * @author thiago leite
 */
public class PedidoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os pedidos
     */
    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return  total * (1 - (cupom.getDesconto()/100));
        } else {
            return  total;
        }
    }

    /**
     * Salva um novo pedido sem cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     */
    public void salvar(Pedido novoPedido) {
        salvar(novoPedido, null);
    }

    /**
     * Salva um novo pedido com cupom de desconto.
     * @param novoPedido Pedido a ser armazenado
     * @param cupom Cupom de desconto a ser utilizado
     */
    public void salvar(Pedido novoPedido, Cupom cupom) {

        String codigo = "PE%4d%2d%04d";
        LocalDate hoje = LocalDate.now();
        codigo = String.format(codigo, hoje.getYear(), hoje.getMonthValue(), bancoDados.getPedidos().length);

        //Setar código no pedido
        novoPedido.setCodigo(codigo);

        //Setar cliente no pedido
        novoPedido.setCliente(bancoDados.getCliente());

        //Calcular e set total
        novoPedido.setTotal(calcularTotal(novoPedido.getProdutos(), cupom));

        //Adicionar no banco
        bancoDados.adicionarPedido(novoPedido);
        System.out.println("Pedido registrado com sucesso.");
    }

    /**
     * Exclui um pedido a partir de seu código de rastreio.
     * @param codigo Código do pedido
     */
    public void excluir(String codigo) {

        Optional<Pedido> pedidoExiste = consultar(codigo);

        if (pedidoExiste.isPresent()) {
            bancoDados.removerPedido(pedidoExiste);
            System.out.println("Pedido excluído com sucesso.");
        }
        else {
            System.out.println("Pedido inexistente.");
        }
    }

    /**
     * Lista todos os pedidos realizados.
     * @author jhon klebson
     */
    public void listarTodos() {

        if (bancoDados.getPedidos().length == 0) {
            System.out.println("Não existem pedidos cadastrados");
        } else {

            for (Pedido pedido: bancoDados.getPedidos()) {
                System.out.println("Pedido {codigo='" + pedido.getCodigo() + "'}");
            }
        }
    }

    /**
     * Consulta o Pedido pelo código.
     * @param codigo Código de um pedido
     * @return O pedido que possuir o código passado.
     */
    public Optional<Pedido> consultar(String codigo) {

        for (Pedido pedido: bancoDados.getPedidos()) {

            if (pedido.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println(pedido);
                return  Optional.of(pedido);
            }
        }
        return Optional.empty();
    }
}
