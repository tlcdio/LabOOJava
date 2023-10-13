package one.digitalinovation.laboojava.utilidade;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.*;
import one.digitalinovation.laboojava.entidade.constantes.Genero;
import one.digitalinovation.laboojava.entidade.constantes.Materias;
import one.digitalinovation.laboojava.negocio.ProdutoNegocio;

import java.util.Optional;
import java.util.Scanner;

/**
 * Classe utilitária para auxiliar na leitura de entradas de dados via teclado.
 * @author thiago leite
 */
public final class LeitoraDados {

	/**
	 * Classe do Java para manipular entradas via teclado.
	 */
	private static Scanner scanner;
	
	static {
		scanner = new Scanner(System.in);
	}

	/**
	 * Ler um dado específico
	 * @return Dado lido
	 */
	public static String lerDado() {

		return scanner.nextLine();
	}

	/**
	 * Ler os dados do livro a ser cadastrado.
	 * @return Um livro a partir dos dados de entrada
	 */
	public static Livro lerLivro() {

		System.out.println("Cadastrando livro...");
		Livro livro = lerLivroNome();

		System.out.println("Digite o gênero: DRAMA, SUSPENSE, ROMANCE");
		String genero = lerDado();
		livro.setGenero(Genero.valueOf(genero.toUpperCase()));

		System.out.println("Digite o preço(padrão 0.0)");
		String preco = lerDado();
		livro.setPreco(Double.parseDouble(preco));

		return livro;
	}

	/**
	 * Ler apenas o nome do livro.
	 * @return Um livro a partir do nome.
	 * @author jhon klebson
	 */
	public static Livro lerLivroNome() {

		Livro livro = new Livro();

		System.out.println("Digite o nome do livro");
		String nome = lerDado();
		livro.setNome(nome);

		return livro;
	}

	/**
	 * Ler os dados do caderno a ser cadastrado.
	 * @return Um caderno a partir dos dados de entrada
	 * @author jhon klebson
	 */
	public static Caderno lerCaderno() {

		System.out.println("Cadastrando caderno...");
		Caderno caderno = lerCadernoTipo();

		System.out.println("Digite o preço(padrão 0.0)");
		String preco = lerDado();
		caderno.setPreco(Double.parseDouble(preco));

		return caderno;
	}

	/**
	 * Ler o tipo do caderno.
	 * @return Um caderno a partir do seu tipo.
	 * @author jhon klebson
	 */
	public static Caderno lerCadernoTipo() {

		Caderno caderno = new Caderno();

		System.out.println("Digite o tipo de caderno:");
		System.out.println("M2 - 2 matérias");
		System.out.println("M5 - 5 matérias");
		System.out.println("M10 - 10 matérias");
		String tipo = lerDado();
		caderno.setTipo(Materias.valueOf(tipo.toUpperCase()));

		return caderno;
	}

	/**
	 * Ler os dados do pedido e retorna um objeto a partir destes.
	 * @return Um pedido a partir dos dados de entrada
	 */
	public static Pedido lerPedido(Banco banco) {

		ProdutoNegocio produtoNegocio = new ProdutoNegocio(banco);

		System.out.println("Cadastrando pedido...");
		Pedido pedido = new Pedido();

		String opcao = "s";
		do {
			produtoNegocio.listarTodos();
			System.out.println("Digite o código do produto(livro/Caderno)");
			String codigo = lerDado();

			Optional<Produto> resultado = produtoNegocio.consultarCodigo(codigo);
			if (resultado.isPresent()) {

				Produto produto = resultado.get();

				System.out.println("Digite a quantidade");
				String quantidade = lerDado();
				produto.setQuantidade(Integer.parseInt(quantidade));

				pedido.getProdutos().add(produto);
			} else {
				System.out.println("Produto inexistente. Escolha um produto válido");
			}

			System.out.println("Deseja selecionar mais um produto? s/n");
			opcao = lerDado();
		} while("s".equals(opcao));

		return pedido;
	}

	/**
	 * Ler os dados do cupom e retorna um objeto a partir destes.
	 * @return O cupom a partir dos dados de entrada
	 */
	public static Optional<Cupom> lerCupom(Banco banco) {

		System.out.println("Caso queira utilizar algum cupom escolha entre: CUPOM2, CUPOM5, CUPOM7. Se não desejar, deixe em branco.");

		String desconto = lerDado();

		for (Cupom cupom: banco.getCupons()) {
			if (cupom.getCodigo().equalsIgnoreCase(desconto)) {
				return Optional.of(cupom);
			}
		}

		return Optional.empty();
	}

	/**
	 * Ler os dados de um cliente para cadastrar.
	 * @param cpf CPF do cliente a ser cadastrado.
	 * @return o cliente com os atributos setados.
	 * @author jhon klebson
	 */
	public static Cliente lerCliente(String cpf) {

		System.out.println("Cadastrando cliente...");
		Cliente cliente = new Cliente();

		System.out.println("Digite o nome");
		String nome = lerDado();
		cliente.setNome(nome);

		cliente.setCpf(cpf);

		return cliente;
	}

}
