package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.SecaoRepresentante;

public class SecaoRepresentanteDAO {

	public void excluirComBroker(SecaoRepresentante model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("secaorepresentantedao.excluirComBroker", model.getSecao().getId());
		
		broker.execute();
		
	}

	public SecaoRepresentante inserirComBroker(SecaoRepresentante model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("secoes_representantes_id_seq"));
		
		broker.setPropertySQL("secaorepresentantedao.inserirComBroker", model.getId(), model.getRepresentante().getId(), model.getSecao().getId());
		
		broker.execute();
		
		return model;
			
	}

	@SuppressWarnings("unchecked")
	public List<SecaoRepresentante> pesquisar(SecaoRepresentante model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("secaorepresentantedao.pesquisar", model.getSecao().getId());
		
		return broker.getCollectionBean(SecaoRepresentante.class, "id", "secao.id", "representante.id", "representante.nome");
	}

}
