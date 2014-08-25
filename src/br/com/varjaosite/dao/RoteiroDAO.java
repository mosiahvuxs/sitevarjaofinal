package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Roteiro;

public class RoteiroDAO {

	@SuppressWarnings("unchecked")
	public List<Roteiro> pesquisar(Roteiro model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT R.ID, R.DATA, R.FLAG_ATIVO, R.TEXTO, R.SECAO_ID, S.DESCRICAO FROM ROTEIROS R, SECOES S WHERE R.SECAO_ID = S.ID");

		if (!TSUtil.isEmpty(model.getSecao()) && !TSUtil.isEmpty(model.getSecao().getId())) {

			sql.append(" AND R.SECAO_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getData()) && !TSUtil.isEmpty(model.getDataFinal())) {

			sql.append(" AND R.DATA BETWEEN ? AND ?");
		}

		if (!TSUtil.isEmpty(model.getTexto())) {

			sql.append(" AND SEM_ACENTOS(R.TEXTO) ILIKE ?");
		}

		sql.append(" AND R.FLAG_ATIVO = ?");

		sql.append(" ORDER BY R.DATA DESC");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getSecao()) && !TSUtil.isEmpty(model.getSecao().getId())) {

			broker.set(model.getSecao().getId());
		}

		if (!TSUtil.isEmpty(model.getData()) && !TSUtil.isEmpty(model.getDataFinal())) {

			broker.set(model.getData());
			
			broker.set(model.getDataFinal());
		}

		if (!TSUtil.isEmpty(model.getTexto())) {

			broker.set("%" + model.getTexto() + "%");
		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Roteiro.class, "id", "data", "flagAtivo", "texto", "secao.id", "secao.descricao");
	}

	public Roteiro obter(Roteiro model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("roteirodao.obter", model.getId());

		return (Roteiro) broker.getObjectBean(Roteiro.class, "id", "data", "secao.id", "texto", "flagAtivo");
	}

	public void alterar(Roteiro model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("roteirodao.alterar",

		model.getData(), model.getSecao().getId(), model.getTexto(), model.getFlagAtivo(), model.getId());

		broker.execute();

	}

	public Roteiro inserir(Roteiro model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("roteiros_id_seq"));

		broker.setPropertySQL("roteirodao.inserir", model.getId(), model.getData(), model.getSecao().getId(), model.getTexto(), model.getFlagAtivo());

		broker.execute();

		return model;

	}
	
	public void excluir(Roteiro model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("roteirodao.excluir", model.getId());

		broker.execute();

	}

}
