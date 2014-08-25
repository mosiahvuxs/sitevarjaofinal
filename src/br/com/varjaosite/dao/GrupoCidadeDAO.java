package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Cidade;
import br.com.varjaosite.model.Grupo;
import br.com.varjaosite.model.GrupoCidade;

public class GrupoCidadeDAO {

	public void inserir(GrupoCidade model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("grupocidadedao.inserir",

		model.getGrupo().getId(), model.getCidade().getId());

		broker.execute();

	}

	public void excluir(Grupo model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("grupocidadedao.excluir", model.getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<Cidade> obter(Grupo model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("grupocidadedao.obter", model.getId());

		return broker.getCollectionBean(Cidade.class, "id", "descricao");
	}
}
