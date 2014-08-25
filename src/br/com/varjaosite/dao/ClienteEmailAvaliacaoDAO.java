package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Cliente;
import br.com.varjaosite.model.ClienteEmailAvaliacao;

public class ClienteEmailAvaliacaoDAO {

	public void inserir(ClienteEmailAvaliacao model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("clienteemailavaliacaodao.inserir",

		model.getCliente().getId(), model.getEmail(), model.getAvaliacao().getId());

		broker.execute();

	}

	public void excluir(Cliente model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("clienteemailavaliacaodao.excluir", model.getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<ClienteEmailAvaliacao> pesquisar(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clienteemailavaliacaodao.pesquisar", model.getId());

		return broker.getCollectionBean(ClienteEmailAvaliacao.class, "id", "email", "cliente.id", "avaliacao.id", "avaliacao.descricao");
	}
}
