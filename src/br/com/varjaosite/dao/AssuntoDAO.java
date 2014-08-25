package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Assunto;
import br.com.varjaosite.util.Utilitarios;

public class AssuntoDAO {

	public void inserirComBroker(Assunto model, TSDataBaseBrokerIf broker) throws TSApplicationException {
		
		model.setId(broker.getSequenceNextValue("assuntos_id_seq"));

		broker.setPropertySQL("assuntodao.inserirComBroker", model.getId(), model.getDescricao(), model.getCliente().getId());
		
		broker.execute();
		
	}

	@SuppressWarnings("unchecked")
	public List<Assunto> pesquisar(Assunto model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("assuntodao.pesquisar", Utilitarios.tratarLong(model.getCliente().getId()));
		
		return broker.getCollectionBean(Assunto.class, "id", "descricao", "cliente.id");
	}

	public void excluirComBroker(Assunto model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("assuntodao.excluirComBroker", model.getCliente().getId());
		
		broker.execute();
		
	}

}
