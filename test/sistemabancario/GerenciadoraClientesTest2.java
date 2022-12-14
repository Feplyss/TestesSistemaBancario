package sistemabancario;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GerenciadoraClientesTest2 {

	private GerenciadoraClientes gerClientes;

	private int idCliente01 = 1;
	private int idCliente02 = 2;

	@Before
	public void setUp() {
		// Cenário
		Cliente cliente01 = new Cliente(idCliente01, "Terencio", 30, "terencio@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Joaquim", 23, "joaquim@gmail.com", 2, true);

		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();
	}

	@Test
	public void testPesquisaCliente() {
		// Execução 1
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);

		// Análise do resultado 1
		assertThat(cliente.getId(), is(1));
		assertThat(cliente.getEmail(), is("terencio@gmail.com"));

		// Execução 2
		Cliente cliente2 = gerClientes.pesquisaCliente(idCliente02);

		// Análise do resultado 2
		assertThat(cliente2.getId(), is(2));
		assertThat(cliente2.getEmail(), is("joaquim@gmail.com"));
	}
	
	@Test
	public void testPesquisaClienteInexistente() {
		// Execução 1
		Cliente cliente = gerClientes.pesquisaCliente(10);

		// Análise do resultado 1
		assertNull(cliente);
	}

	@Test
	public void testRemoveCliente() {
		// Execução 1
		boolean clienteRemovido = gerClientes.removeCliente(idCliente02);

		// Análise do resultado 1
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(2));

		// Execução 2
		boolean clienteRemovido2 = gerClientes.removeCliente(idCliente01);

		// Análise do resultado 2
		assertThat(clienteRemovido2, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(0));
		assertNull(gerClientes.pesquisaCliente(1));
	}
	
	@Test
	public void testRemoveClienteInexistente() {
		// Execução 1
		boolean clienteRemovido = gerClientes.removeCliente(10);

		// Análise do resultado 1
		assertThat(clienteRemovido, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}
}
