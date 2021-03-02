package dao;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import util.TrataException;
    import java.util.List;
    import java.sql.*;
    import beans.Cliente;
/*---------------------------------------------------------------------------*
*   Nesta classe implementamos os métodos  abstratos da classe "ClientesDAO" *
*                                                                            *
*              Substitua cada "xxxxxx"  pelo nome do método correspondente:  *
*                   Você encontratrá na classe: ClientesDB                 *                                                         *
*----------------------------------------------------------------------------*/
	public class ClientesDB implements ClientesDAO {

    private final static String SALVAR_CLIENTE = "INSERT INTO clientes (nome,cpf,tel) VALUES (?,?,?)";
    //private final static String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS  clientes (id int(3) NOT NULL AUTO_INCREMENT   PRIMARY KEY, nome VARCHAR(20) NOT NULL, cpf varchar(20) NOT NULL, telefone varchar(20) NOT NULL)";
    private final static String DELETE_CLIENTE = "DELETE FROM clientes WHERE cpf = '";
    private final static String GET_ALL_CLIENTES = "SELECT * FROM clientes order by 2";
    private final static String GET_CLIENTE_BY_CPF = "SELECT * FROM clientes WHERE cpf = ?";
    /* 
    private DataSource ds;
    
    public void setDataSource(DataSource ds) {
    	this.ds = ds;
    }

    /*
     * @see dao.ClientesDAO#excluir(beans.Cliente)
     */
    public void excluir(String cpf) throws TrataException {
    	Connection conn = null;
        Statement stmt = null;
        
        try {

            conn = ConnectionManager.getConexao(); // COLOCAR AQUI O MÉTODO PARA OBTER a conexao com o servidor
            stmt = conn.prepareStatement(DELETE_CLIENTE); // chamar o método para Criar o statement responsavel pelo envio dos SQL's


            int resultado    = stmt.executeUpdate(DELETE_CLIENTE + cpf + "'"); // Executa a query passando como parametro o CPF
            
             if (resultado == 1) {
            	System.out.println("Deletou OK");
            }else {
            	System.out.println("CPF não encontrado");
            }
        } catch (SQLException e) {
        	
            String errorMsg = "Nao foi possivel fechar o statement com o banco de Dados!";
            e.printStackTrace();
            throw new TrataException(errorMsg, e);
        }
    }

    /*
     * @see dao.ClientesDAO#salvar(beans.Cliente)
     */
    public void salvar(Cliente cliente) throws TrataException {
    	 // Criar uma variavel para a Conexao
        Connection conn = null;
        // Criar uma variavel para a PreparedStatement
        PreparedStatement stmt = null;
        try {
            // Obtem da ConnectionManager uma conexao com o banco de dados
            conn = ConnectionManager.getConexao(); // COLOCAR AQUI O MÉTODO PARA OBTER a conexao com o servidor


            // Cria um preparedStatement para o BD conseguir pre-compilar um SQL previamente
            stmt = conn.prepareStatement(SALVAR_CLIENTE);
            // Atribui uma String para a 1a. interrogacao (nome)
            stmt.setString(1, cliente.getNome());
            // Atribui uma String para a 2a. interrogacao (telefone)
            stmt.setString(2, cliente.getTelefone());
            // Atribui uma String para a 3a. interrogacao (cpf)
            stmt.setString(3, cliente.getCpf());
            // Executar a operacao de gravar os dados na base
            
            stmt.executeUpdate();
            
            
        } catch (SQLException e) {
            TrataException.print(e, "Nao foi possivel salvar o cliente na base de dados.");
        } finally {
            // Finalizar o statement e a conexao usando a classe ConnectionManager
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    /*
     * @see dao.ClientesDAO#getAllClientes()
     */
    public List getAllClientes() throws TrataException {
    	Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List clientes = new ArrayList();
        try {

            conn = ConnectionManager.getConexao(); // COLOCAR AQUI O MÉTODO PARA OBTER a conexao com o servidor
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(GET_ALL_CLIENTES);
            while (rs.next()) {
                Cliente cli = new Cliente(rs.getString("nome"), rs.getString("tel"), rs.getString("cpf"), rs.getInt("id"));
                clientes.add(cli);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return clientes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see dao.ClientesDAO#getClienteByID(int)
     */
    public Cliente getClienteByCPF(String cpf) throws TrataException {
    	// Criar uma variavel para a Conexao
        Connection conn = null;
        // Criar uma variavel para a PreparedStatement
        PreparedStatement stmt = null;
        // Criar uma variavel para o ResultSet
        ResultSet rs = null;
        Cliente cliente = null;
        try {
            // Obtem da ConnectionManager uma conexao com o banco de dados
        	conn = ConnectionManager.getConexao();
            // Cria um preparedStatement para o BD conseguir pre-compilar um SQL previamente
            stmt = conn.prepareStatement(GET_CLIENTE_BY_CPF);
            // Atribui uma String para a 1a. interrogacao (cpf)
            stmt.setString(1, cpf);
            // Executar a operacao de obter os dados na base            
            rs = stmt.executeQuery(); // COLOCAR A CHAMADA PARA EXECUÇÃO DO sql


            // Executar a leitura do ResultSet, gerando um objeto Cliente para ser retornado pelo metodo
            while (rs.next()) {
                cliente = new Cliente(rs.getString("nome"), rs.getString("tel"), rs.getString("cpf"), rs.getInt("id"));
            }
        } catch (SQLException e) {
            TrataException.print(e, "Nao foi possivel encontrar o cliente na base de dados.");
        } 
        // Retorna os valores para o metodo
        return cliente;
    
    }

}
