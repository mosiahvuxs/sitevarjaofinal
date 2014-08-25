package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Representante;

public class RepresentanteDAO {

	@SuppressWarnings("unchecked")
	public List<Representante> pesquisar(Representante model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, UPPER(NOME) FROM REPRESENTANTES WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getNome())) {

			sql.append(" AND SEM_ACENTOS(NOME) ILIKE ?");
		}

		sql.append(" AND FLAG_ATIVO = ?");

		sql.append(" ORDER BY NOME");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getNome())) {

			broker.set("%" + model.getNome() + "%");
		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Representante.class, "id", "nome");
	}

	public Representante obter(Representante model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("representantedao.obter", model.getId());

		return (Representante) broker.getObjectBean(Representante.class, "id", "nome", "flagAtivo");
	}

	public void excluir(Representante model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("representantedao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Representante model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("representantedao.alterar", model.getNome(), model.getFlagAtivo(), model.getId());

		broker.execute();

	}

	public Representante inserir(Representante model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("representantes_id_seq"));

		broker.setPropertySQL("representantedao.inserir", model.getId(), model.getNome(), model.getFlagAtivo());

		broker.execute();

		return model;

	}


}
