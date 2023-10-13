package one.digitalinovation.laboojava.negocio;

import one.digitalinovation.laboojava.basedados.Banco;
import one.digitalinovation.laboojava.entidade.Cliente;

import java.util.Optional;

/**
 * Classe para manipular a entidade {@link Cliente}.
 * @author thiago leite
 */
public class ClienteNegocio {

    /**
     * {@inheritDoc}.
     */
    private Banco bancoDados;

    /**
     * Construtor.
     * @param banco Banco de dados para ter acesso aos clientes cadastrados
     */
    public ClienteNegocio(Banco banco) {
        bancoDados = banco;
    }

    /**
     * Procura o usuário na base de dados.
     *
     * @param cpf CPF do usuário que deseja logar na aplicação
     */
    public Cliente logarCliente(String cpf) {

        Optional<Cliente> resultado = consultar(cpf);

        if (resultado.isPresent()) {

            Cliente cliente = resultado.get();
            System.out.println(String.format("Olá %s! Você está logado.", cliente.getNome()));
            return cliente;
        } else {
            System.out.println("Usuário não cadastrado.");
            return null;
        }
    }

    /**
     * Consulta o cliente pelo seu CPF.
     * @param cpf CPF de um cliente
     * @return O cliente que possuir o CPF passado.
     */
    public Optional<Cliente> consultar(String cpf) {

        for (Cliente cliente: bancoDados.getClientes()) {

            if (cliente.getCpf().equalsIgnoreCase(cpf)) {
                return  Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    /**
     * Cadastra um novo cliente.
     * @param cliente Novo cliente que terá acesso a aplicação
     * @author jhon klebson
     */
    public void cadastrarCliente(Cliente cliente) {
        bancoDados.adicionarCliente(cliente);
        System.out.println("Cliente cadastrado com sucesso.");
    }

    /**
     * Exclui um cliente específico.
     * @param cpf CPF do cliente
     * @author jhon klebson
     */
    public void excluirCliente(String cpf) {

        Optional<Cliente> clienteExiste = consultar(cpf);

        if (clienteExiste.isPresent()) {
            bancoDados.removerCliente(clienteExiste);
            System.out.println("Cliente excluído com sucesso.");
        }
        else {
            System.out.println("Cliente inexistente.");
        }
    }

}
