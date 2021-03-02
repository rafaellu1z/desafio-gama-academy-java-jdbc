package dao;


	import beans.Cliente;
	import util.TrataException;
	import java.util.List;

/*---------------------------------------------------------------------------*
*              você deve alterar qui somente onde exisiter "xxxxxx"          *
*              Substitua cada "xxxxxx"  pelo nome do método correspondente:  *
*                   Você encontratrá na classe: ClientesDB                 *                                                         *
*----------------------------------------------------------------------------*/

	public interface ClientesDAO {

	    /**
	     * @param cliente
	     *            Cliente a ser inserido na fonte de dados em questao
	     * @throws TrataException
	     */
	    public abstract void salvar(Cliente cliente) throws TrataException;

	    /**
	     * @param CPF
	     *            do Cliente a ser excluido da fonte de dados em questao
	     * @throws TrataException
	     */
	    public abstract void excluir(String cpf) throws TrataException;

	    /**
	     * @return java.util.List contendo todos os clientes contidos na fonte da dados em questao
	     * @throws TrataException
	     */
	    public abstract List getAllClientes() throws TrataException;

	    /**
	     * @param String
	     *            CPF do cliente a ser pesquisado no "banco de dados"
	     * @return Cliente
	     * @throws TrataException
	     */
	    public abstract Cliente getClienteByCPF(String cpf) throws TrataException;
}
