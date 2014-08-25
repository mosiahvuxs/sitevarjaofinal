package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.TipoCliente;
import br.com.varjaosite.util.Utilitarios;

public class TipoClienteDAO {

	@SuppressWarnings("unchecked")
	public List<TipoCliente> pesquisar(TipoCliente model) {
		
		StringBuilder sql = new StringBuilder();

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		sql.append("SELECT ID, DESCRICAO FROM TIPO_CLIENTES WHERE FLAG_ATIVO = ?");

		if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getDescricao()))) {

			sql.append(" AND SEM_ACENTOS(DESCRICAO) ILIKE ? ");

		}

		sql.append(" ORDER BY DESCRICAO");

		broker.setSQL(sql.toString());

		broker.set(model.getFlagAtivo());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");

		}

		return broker.getCollectionBean(TipoCliente.class, "id", "descricao");
	}

	public TipoCliente obter(TipoCliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("tipoclientedao.obter", model.getId());
		
		return (TipoCliente) broker.getObjectBean(TipoCliente.class, "id", "descricao", "flagAtivo");
	}

	public void excluir(TipoCliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("tipoclientedao.excluir", model.getId());
		
		broker.execute();
		
	}

	public void alterar(TipoCliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		broker.setPropertySQL("tipoclientedao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getId());
		
		broker.execute();
		
	}

	public TipoCliente inserir(TipoCliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		model.setId(broker.getSequenceNextValue("tipo_clientes_id_seq"));
		
		broker.setPropertySQL("tipoclientedao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo());
		
		broker.execute();
		
		return model;
		
	}

}
