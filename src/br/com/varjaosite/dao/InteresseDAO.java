package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Interesse;
import br.com.varjaosite.util.Utilitarios;

public class InteresseDAO {

	public void inserirComBroker(Interesse model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("interesses_id_seq"));

		broker.setPropertySQL("interessedao.inserirComBroker", model.getId(), model.getDescricao(), model.getCliente().getId());
		
		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<Interesse> pesquisar(Interesse model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("interessedao.pesquisar", Utilitarios.tratarLong(model.getCliente().getId()));
		
		return broker.getCollectionBean(Interesse.class, "id", "descricao", "cliente.id");
		
	}

	public void excluirComBroker(Interesse model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("interessedao.excluirComBroker", model.getCliente().getId());
		
		broker.execute();
		
	}

}
