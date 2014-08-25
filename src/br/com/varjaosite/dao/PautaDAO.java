package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Pauta;

public class PautaDAO {

	@SuppressWarnings("unchecked")
	public List<Pauta> pesquisar(Pauta model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, UPPER(DESCRICAO) FROM PAUTAS WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND SEM_ACENTOS(DESCRICAO) ILIKE ?");
		}

		sql.append(" AND FLAG_ATIVO = ?");

		sql.append(" ORDER BY DESCRICAO");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");
			
		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Pauta.class, "id", "descricao");
	}
	
	public Pauta obter(Pauta model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("pautadao.obter", model.getId());

		return (Pauta) broker.getObjectBean(Pauta.class, "id", "descricao", "flagAtivo");
	}

	public void excluir(Pauta model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("pautadao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Pauta model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("pautadao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

	}

	public Pauta inserir(Pauta model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("pautas_id_seq"));

		broker.setPropertySQL("pautadao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;

	}
	

}
