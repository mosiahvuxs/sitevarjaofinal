package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Avaliacao;

public class AvaliacaoDAO {

	@SuppressWarnings("unchecked")
	public List<Avaliacao> pesquisar(Avaliacao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, UPPER(DESCRICAO) FROM AVALIACOES WHERE 1 = 1");

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

		return broker.getCollectionBean(Avaliacao.class, "id", "descricao");
	}
	
	public Avaliacao obter(Avaliacao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("avaliacaodao.obter", model.getId());

		return (Avaliacao) broker.getObjectBean(Avaliacao.class, "id", "descricao", "flagAtivo");
	}

	public void excluir(Avaliacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("avaliacaodao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Avaliacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("avaliacaodao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

	}

	public Avaliacao inserir(Avaliacao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("avaliacoes_id_seq"));

		broker.setPropertySQL("avaliacaodao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;

	}
	

}
