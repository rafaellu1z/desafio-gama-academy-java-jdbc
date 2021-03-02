package beans;
import java.io.IOException;
import dao.ClientesDB;
import java.util.List;
import java.io.IOException;
import java.util.List;
import dao.ClientesDAO;
import dao.ClientesDB;
import util.*;

/*   Classe Main
 * 
 */
public class TestaCliente {

    public static ClientesDAO dao = new ClientesDB();
        
    /**
     * Este metodo e reponsavel pela montagem do menu de opçoes do usuario: INSERIR | REMOVER | LISTAR | FINALIZAR
     */
    public static void montaMenu() {
        System.out.println("-------------------------");
        System.out.println("INSERIR NOVO CLIENTE      : 1");
        System.out.println("REMOVER CLIENTE           : 2");
        System.out.println("LISTAR CLIENTES           : 3");
        System.out.println("Localiza CLIENTE por CPF  : 4");
        System.out.println("FINALIZAR                 : 0");
        System.out.println("-------------------------");
        System.out.print("ESCOLHA A OPERACAO: ");
    }

    /**
     * Este metodo e responsavel por retornar a opçao de menu do usuario
     */
    public static int leOperacao() throws IOException, NumberFormatException {
        // Utiliza o metodo estatico da classe Teclado para ler a opçao digitada
        // pelo usuario
        String op = Teclado.le();
        // Transforma a opçao, que e uma String em int
        int operacao = Integer.parseInt(op);
        return operacao;
    }

    /**
     * Este metodo e reponsavel por ler um cliente digitado pelo usuario, fazendo todas as "perguntas / interaçoes"
     * necessarias para obtençao dos dados digitados.
     */
    public static Cliente leCliente() {
        // Leitura dos dados do Cliente do teclado
        Cliente cliente = null;
        try {
            System.out.print("Nome do cliente: ");
            // Leitura do nome
            String nome = Teclado.le();
            System.out.print("CPF do cliente : ");
         // Leitura do CPF
            String cpf = Teclado.le();
            System.out.print("Telefone do cliente: ");
         // Leitura do telefone
            String telefone = Teclado.le();
            
            int id = 0;
            cliente = new Cliente(nome, telefone, cpf, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // retorna o Cliente lido
        return cliente;
    }

    /**
     * Este metodo e reponsavel por ler o ID de um cliente digitado pelo usuario, fazendo todas as "perguntas /
     * interaçoes" necessarias para obtençao dos dados
     */
    public static String leCPFCliente() {
        // Leitura do ID do Cliente a ser removido
        String cpf = "";
        try {
            // O cpf deve obrigatoriamente ser uma String, por isto criamos um
            // while
            // enquanto o usuario nao digitar um inteiro
            while (cpf.equals("")) {
                System.out.print("CPF do cliente : ");
                String strId = Teclado.le();
                // Se o id e valido, interrompemos o while
                if (strId != null && strId.length() > 0) {
                    cpf = strId;
                } // end if
            } // end while
        } catch (Exception e) {
            e.printStackTrace();
        }
        // retorna o Cliente lido
        return cpf;
    }

    public static void listarClientes(List clientes) {
        if (clientes != null) {
            for (int i = 0; i < clientes.size(); i++) {
                System.out.println(((Cliente) clientes.get(i)));
            }
        }
    }
    public static void mostrarCliente(Cliente cliente) {
    	if (cliente == null) {
    		System.out.println("CPF não consta no banco de dados\n");
    	} else {
        	System.out.println("CPF:"+cliente.getCpf()+"\n");
    	    System.out.println("Nome:"+cliente.getNome()+"\n");
    	    System.out.println("Tel:"+cliente.getTelefone()+"\n");
    	}
    }
    /**
     * Este metodo executa uma tarefa de acordo com a operaçao passada como parametro.
     */
    public static void executarTarefa(int operacao) throws TrataException {
        switch (operacao) {
        case 1:
            System.out.println("Inserindo cliente");
            Cliente cliente = leCliente();
            dao.salvar(cliente);
            break;
        case 2:
            String clienteID = leCPFCliente();
            dao.excluir(clienteID);
            break;
        case 3:
            System.out.println("Listando clientes");
            List clientes = dao.getAllClientes();
            listarClientes(clientes);
            break;
        case 4:
        	String clienteCPF = leCPFCliente();
            System.out.println("Localiza Cliente por CPF");
            Cliente clienteUnico = dao.getClienteByCPF(clienteCPF);
            mostrarCliente(clienteUnico);
            break;   
        default:   
        	System.out.println("Escolha a opçao correta");
        	break;
        }
    }

    public static void main(String[] args) throws TrataException {
        int TERMINAR = 0;
        int operacao = 1;
        do {
            try {
                montaMenu();
                operacao = leOperacao();
                if (operacao == TERMINAR) {
                	System.out.println("Obrigado por usar o sistema");
                    break;
                } else {
                    executarTarefa(operacao);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Operacao invalida");
            }
        } while (operacao != TERMINAR);
    }
}