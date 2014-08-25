package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.TipoVeiculo;

public class TipoVeiculoDAO {

	@SuppressWarnings("unchecked")
	public List<TipoVeiculo> pesquisar(TipoVeiculo model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT ID, DESCRICAO FROM TIPO_VEICULOS WHERE 1 = 1");

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

		return broker.getCollectionBean(TipoVeiculo.class, "id", "descricao");
	}

	public TipoVeiculo obter(TipoVeiculo model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipoveiculodao.obter", model.getId());

		return (TipoVeiculo) broker.getObjectBean(TipoVeiculo.class, "id", "descricao", "flagAtivo");
	}

	public void excluir(TipoVeiculo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipoveiculodao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(TipoVeiculo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("tipoveiculodao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getId());

		broker.execute();

	}

	public TipoVeiculo inserir(TipoVeiculo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("tipo_veiculos_id_seq"));

		broker.setPropertySQL("tipoveiculodao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo());

		broker.execute();

		return model;

	}

}
