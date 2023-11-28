package one.digitalinovation.laboojava.console;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.negocio.ClienteNegocio;
import one.digitalinovation.laboojava.negocio.PedidoNegocio;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;
import one.digitalinovation.laboojava.utilidade.LeitoraDados;

import java.util.Optional;

/**
 * Classe responsável por controlar a execução da aplicação.
 * @author thiago leite
 */
public class Start {

    private static Cliente clienteLogado = null;

    private static Banco banco = new Banco();

    private static ClienteNegocio clienteNegocio = new ClienteNegocio(banco);
    private static PedidoNegocio pedidoNegocio = new PedidoNegocio(banco);
    private static ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

    /**
     * Método utilitário para inicializar a aplicação.
     * @param args Parâmetros que podem ser passados para auxiliar na execução.
     */
    public static void main(String[] args) {

        System.out.println("Bem vindo ao e-Compras");

        String opcao = "";

        while(true) {

            while (clienteLogado == null) {

                System.out.println("Digite o cpf para logar:");

                String cpf = "";
                cpf = LeitoraDados.lerDado();

                clienteLogado = clienteNegocio.logarCliente(cpf);

                if (clienteLogado == null) {
                    System.out.println("Deseja cadastrar o usuário?");
                    String resposta = LeitoraDados.lerDado();
                    if (resposta.equalsIgnoreCase("s")) {
                        clienteNegocio.cadastrarCliente(LeitoraDados.lerCliente(cpf));
                    }
                }
            }

            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Excluir Livro");
            System.out.println("3 - Consultar Livro");
            System.out.println("4 - Cadastrar Caderno");
            System.out.println("5 - Excluir Caderno");
            System.out.println("6 - Consultar Caderno");
            System.out.println("7 - Fazer pedido");
            System.out.println("8 - Excluir pedido");
            System.out.println("9 - Consultar Pedido");
            System.out.println("10 - Listar produtos");
            System.out.println("11 - Listar pedidos");
            System.out.println("12 - Deslogar");
            System.out.println("13 - Sair");

            opcao = LeitoraDados.lerDado();

            switch (opcao) {
                case "1":
                    Livro livro = LeitoraDados.lerLivro();
                    produtoNegocio.salvar(livro);
                    break;
                case "2":
                    produtoNegocio.listarProdutoTipo(new Livro());
                    System.out.println("Digite o código do livro");
                    String codigoLivro = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoLivro);
                    break;
                case "3":
                    produtoNegocio.consultarProduto(LeitoraDados.lerLivroNome());
                    break;
                case "4":
                    Caderno caderno = LeitoraDados.lerCaderno();
                    produtoNegocio.salvar(caderno);
                    break;
                case "5":
                    produtoNegocio.listarProdutoTipo(new Caderno());
                    System.out.println("Digite o código do caderno");
                    String codigoCaderno = LeitoraDados.lerDado();
                    produtoNegocio.excluir(codigoCaderno);
                    break;
                case "6":
                    produtoNegocio.consultarProduto(LeitoraDados.lerCadernoTipo());
                    break;
                case "7":
                    Pedido pedido = LeitoraDados.lerPedido(banco);
                    Optional<Cupom> cupom = LeitoraDados.lerCupom(banco);

                    if (cupom.isPresent()) {
                        pedidoNegocio.salvar(pedido, cupom.get());
                    } else {
                        pedidoNegocio.salvar(pedido);
                    }
                    break;
                case "8":
                    pedidoNegocio.listarTodos();
                    System.out.println("Digite o código do pedido");
                    String codigoPedido = LeitoraDados.lerDado();
                    pedidoNegocio.excluir(codigoPedido);
                    break;
                case "9":
                    pedidoNegocio.listarTodos();
                    System.out.println("Digite o código do pedido");
                    String codigo = LeitoraDados.lerDado();
                    pedidoNegocio.consultar(codigo);
                    break;
                case "10":
                    produtoNegocio.listarTodos();
                    break;
                case "11":
                    pedidoNegocio.listarTodos();
                    break;
                case "12":
                    System.out.println(String.format("Volte sempre %s!", clienteLogado.getNome()));
                    clienteLogado = null;
                    break;
                case "13":
                    System.out.println("Aplicação encerrada.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }
}
