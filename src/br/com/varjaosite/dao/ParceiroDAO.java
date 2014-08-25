package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Parceiro;
import br.com.varjaosite.util.Utilitarios;

public class ParceiroDAO {

	@SuppressWarnings("unchecked")
	public List<Parceiro> pesquisar(Parceiro model) {

		StringBuilder sql = new StringBuilder();

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		sql.append("SELECT ID, UPPER(NOME) FROM PARCEIROS WHERE FLAG_ATIVO = ?");

		if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getNome()))) {

			sql.append(" AND NOME ILIKE ? ");

		}

		sql.append(" ORDER BY NOME");

		broker.setSQL(sql.toString());

		broker.set(model.getFlagAtivo());

		if (!TSUtil.isEmpty(model.getNome())) {

			broker.set("%" + model.getNome() + "%");

		}

		return broker.getCollectionBean(Parceiro.class, "id", "nome");

	}

	public Parceiro inserir(Parceiro model) throws TSApplicationException {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();
		
		model.setId(broker.getSequenceNextValue("parceiros_id_seq"));
		
		broker.setPropertySQL("parceirodao.inserir", model.getId(), model.getNome(), model.getFlagAtivo(), model.getLogomarca(), model.getContato(), model.getEmail(),
				                  model.getEndereco().getLogradouro(), model.getEndereco().getBairro(), model.getEndereco().getNumero(),
				                  model.getEndereco().getCep(), model.getEndereco().getComplemento(), model.getEndereco().getCidade().getId(),
				                  model.getEndereco().getEstado().getId());
		
		broker.execute();

		return model;

	}

	public void alterar(Parceiro model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("parceirodao.alterar", model.getNome(), model.getFlagAtivo(), model.getLogomarca(), model.getContato(), model.getEmail(),
				                                    model.getEndereco().getLogradouro(), model.getEndereco().getBairro(), model.getEndereco().getNumero(), 
				                                    model.getEndereco().getCep(), model.getEndereco().getComplemento(), model.getEndereco().getCidade().getId(), 
				                                    model.getEndereco().getEstado().getId(), model.getId());

		broker.execute();

	}

	public void excluir(Parceiro model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("parceirodao.excluir", model.getId());

		broker.execute();
		
	}

	public Parceiro obter(Parceiro model) {
		
		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("parceirodao.obter", model.getId());

		return (Parceiro) broker.getObjectBean(Parceiro.class, "id", "nome", "flagAtivo", "logomarca", "contato", "email", "endereco.logradouro", 
				                                               "endereco.bairro", "endereco.numero", "endereco.cep", "endereco.complemento", 
				                                               "endereco.cidade.id", "endereco.estado.id");
	}

}
