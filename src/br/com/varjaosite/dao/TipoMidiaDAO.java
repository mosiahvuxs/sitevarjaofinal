package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.TipoMidia;

public class TipoMidiaDAO {

	@SuppressWarnings("unchecked")
	public List<TipoMidia> pesquisar(TipoMidia model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO FROM TIPO_MIDIAS WHERE 1 = 1");

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

		return broker.getCollectionBean(TipoMidia.class, "id", "descricao");
	}

	public TipoMidia obter(TipoMidia model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipomidiadao.obter", model.getId());

		return (TipoMidia) broker.getObjectBean(TipoMidia.class, "id", "descricao", "flagAtivo");
	}

	public void excluir(TipoMidia model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipomidiadao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(TipoMidia model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipomidiadao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

	}

	public TipoMidia inserir(TipoMidia model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("tipo_midias_id_seq"));

		broker.setPropertySQL("tipomidiadao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;

	}

}
