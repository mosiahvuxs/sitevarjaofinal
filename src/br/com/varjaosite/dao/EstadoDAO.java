package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.varjaosite.model.Estado;

public class EstadoDAO {

	@SuppressWarnings("unchecked")
	public List<Estado> pesquisar() {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("estadodao.pesquisar");
		
		return broker.getCollectionBean(Estado.class, "id", "descricao");
		
	}

}
