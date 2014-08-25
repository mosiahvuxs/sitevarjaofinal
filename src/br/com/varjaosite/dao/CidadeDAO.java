package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Cidade;

public class CidadeDAO {

	@SuppressWarnings("unchecked")
	public List<Cidade> pesquisar(Cidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("cidadedao.pesquisar", model.getEstado().getId());

		return broker.getCollectionBean(Cidade.class, "id", "descricao");
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> find(Cidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT C.ID, UPPER(C.DESCRICAO) CIDADE, C.FLAG_ATIVO, E.ID, UPPER(E.DESCRICAO) ESTADO FROM CIDADES C, ESTADOS E WHERE C.ESTADO_ID = E.ID");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND SEM_ACENTOS(C.DESCRICAO) ILIKE ?");
		}

		if (!TSUtil.isEmpty(model.getEstado()) && !TSUtil.isEmpty(model.getEstado().getId())) {

			sql.append(" AND C.ESTADO_ID = ?");
		}

		sql.append(" AND C.FLAG_ATIVO = ?");

		sql.append(" ORDER BY C.DESCRICAO");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");

		}

		if (!TSUtil.isEmpty(model.getEstado()) && !TSUtil.isEmpty(model.getEstado().getId())) {

			broker.set(model.getEstado().getId());
		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Cidade.class, "id", "descricao", "flagAtivo", "estado.id", "estado.descricao");
	}

	public Cidade obter(Cidade model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("cidadedao.obter", model.getId());

		return (Cidade) broker.getObjectBean(Cidade.class, "id", "descricao", "flagAtivo", "estado.id", "estado.descricao");
	}

	public void excluir(Cidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("cidadedao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Cidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("cidadedao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getEstado().getId(), model.getId());

		broker.execute();

	}

	public Cidade inserir(Cidade model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("cidades_id_seq"));

		broker.setPropertySQL("cidadedao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo(), model.getEstado().getId());

		broker.execute();

		return model;

	}

}
