package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Grupo;
import br.com.varjaosite.model.Veiculo;

public class VeiculoDAO {

	@SuppressWarnings("unchecked")
	public List<Veiculo> pesquisar(Veiculo model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT V.ID, UPPER(V.DESCRICAO), C.ID, C.DESCRICAO FROM VEICULOS V INNER JOIN CIDADES C ON V.CIDADE_ID = C.ID INNER JOIN TIPO_VEICULOS TV ON V.TIPO_VEICULO_ID = TV.ID WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND SEM_ACENTOS(V.DESCRICAO) ILIKE ?");
		}

		if (!TSUtil.isEmpty(model.getTipoVeiculo()) && !TSUtil.isEmpty(model.getTipoVeiculo().getId())) {

			sql.append(" AND V.TIPO_VEICULO_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getCidade()) && !TSUtil.isEmpty(model.getCidade().getId())) {

			sql.append(" AND V.CIDADE_ID = ?");
		}

		sql.append(" AND V.FLAG_ATIVO = ?");

		sql.append(" ORDER BY V.DESCRICAO");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");
		}

		if (!TSUtil.isEmpty(model.getTipoVeiculo()) && !TSUtil.isEmpty(model.getTipoVeiculo().getId())) {

			broker.set(model.getTipoVeiculo().getId());
		}

		if (!TSUtil.isEmpty(model.getCidade()) && !TSUtil.isEmpty(model.getCidade().getId())) {

			broker.set(model.getCidade().getId());
		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Veiculo.class, "id", "descricao", "cidade.id", "cidade.descricao");
	}

	public Veiculo obter(Veiculo model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("veiculodao.obter", model.getId());

		return (Veiculo) broker.getObjectBean(Veiculo.class, "id", "descricao", "flagAtivo", "tipoVeiculo.id", "cidade.id");
	}

	public void excluir(Veiculo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("veiculodao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Veiculo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("veiculodao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getTipoVeiculo().getId(), model.getCidade().getId(), model.getId());

		broker.execute();

	}

	public Veiculo inserir(Veiculo model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("veiculos_id_seq"));

		broker.setPropertySQL("veiculodao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo(), model.getTipoVeiculo().getId(), model.getCidade().getId());

		broker.execute();

		return model;

	}

	@SuppressWarnings("unchecked")
	public List<Veiculo> popularCombo(Veiculo model, Grupo grupo) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT V.ID, UPPER(V.DESCRICAO), C.ID, C.DESCRICAO FROM VEICULOS V INNER JOIN CIDADES C ON V.CIDADE_ID = C.ID INNER JOIN TIPO_VEICULOS TV ON V.TIPO_VEICULO_ID = TV.ID WHERE C.FLAG_ATIVO AND V.FLAG_ATIVO");

		if (!TSUtil.isEmpty(grupo) && !TSUtil.isEmpty(grupo.getId())) {

			sql.append(" AND C.ID IN (SELECT GC.CIDADE_ID FROM GRUPOS_CIDADES GC WHERE GC.GRUPO_ID = ?)");
		}

		if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getTipoVeiculo()) && !TSUtil.isEmpty(model.getTipoVeiculo().getId())) {

			sql.append(" AND V.TIPO_VEICULO_ID = ?");
		}

		sql.append(" ORDER BY V.DESCRICAO");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(grupo) && !TSUtil.isEmpty(grupo.getId())) {

			broker.set(grupo.getId());
		}

		if (!TSUtil.isEmpty(model) && !TSUtil.isEmpty(model.getTipoVeiculo()) && !TSUtil.isEmpty(model.getTipoVeiculo().getId())) {

			broker.set(model.getTipoVeiculo().getId());
		}

		return broker.getCollectionBean(Veiculo.class, "id", "descricao", "cidade.id", "cidade.descricao");
	}

}
