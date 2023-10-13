package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Produto;

import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Produto}.
 * @author thiago leite
 */
public class ProdutoNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter armazenar e ter acesso os produtos
     */
    public ProdutoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    /**
     * Salva um novo produto(livro ou caderno) na loja.
     * @param novoProduto Livro ou caderno que pode ser vendido
     */
    public void salvar(Produto novoProduto) {

        Optional<Produto> produtoRepetido = consultarProduto(novoProduto);

        if (produtoRepetido.isEmpty()) {
            String codigo = "PR%04d";
            codigo = String.format(codigo, bancoDados.getProdutos().length);
            novoProduto.setCodigo(codigo);
            bancoDados.adicionarProduto(novoProduto);
            System.out.println("Produto cadastrado com sucesso.");
        }
        else {
            System.out.println("Produto já cadastrado.");
        }
    }

    /**
     * Exclui um produto pelo código de cadastro.
     * @param codigo Código de cadastro do produto
     * @author jhon klebson
     */
    public void excluir(String codigo) {

        Optional<Produto> produtoParaRemover = consultarCodigo(codigo);
        if (produtoParaRemover.isPresent()) {
            Produto produto = produtoParaRemover.get();
            bancoDados.removerProduto(produto);
            System.out.println("Produto excluído com sucesso.");
        }
        else {
            System.out.println("Produto inexistente.");
        }
    }

    /**
     * Obtem um produto a partir de seu código de cadastro.
     * @param codigo Código de cadastro do produto
     * @return Optional indicando a existência ou não do Produto
     */
    public Optional<Produto> consultarCodigo(String codigo) {

        for (Produto produto: bancoDados.getProdutos()) {

            if (produto.getCodigo().equalsIgnoreCase(codigo)) {
                return  Optional.of(produto);
            }
        }

        return Optional.empty();
    }

    /**
     * Obtem um produto a partir de seu tipo livro/caderno.
     * @param produtoConsultado Tipo de produto a ser consultado
     * @return Optional indicando a existência ou não do Produto
     * @author jhon klebson
     */
    public Optional<Produto> consultarProduto(Produto produtoConsultado) {

        for (Produto produto: bancoDados.getProdutos()) {

            if (produto.equals(produtoConsultado)) {
                System.out.println(produto);
                return  Optional.of(produtoConsultado);
            }
        }

        return Optional.empty();
    }

    /**
     * Lista todos os produtos cadastrados.
     * @author jhon klebson
     */
    public void listarTodos() {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto: bancoDados.getProdutos()) {
                System.out.println(produto.toString());
            }
        }
    }

    /**
     * Lista todos os produtos cadastrados por tipo livro/caderno.
     * @param tipo Tipo do produto a ser listado.
     * @author jhon klebson
     */
    public void listarProdutoTipo(Produto tipo) {

        if (bancoDados.getProdutos().length == 0) {
            System.out.println("Não existem produtos cadastrados");
        } else {

            for (Produto produto: bancoDados.getProdutos()) {
                if (produto.getClass().equals(tipo.getClass())) {
                    System.out.println(produto);
                }
            }
        }
    }
}
