package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Secao;
import br.com.varjaosite.model.SecaoRepresentante;

public class SecaoDAO {

	@SuppressWarnings("unchecked")
	public List<Secao> pesquisar(Secao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT S.ID, UPPER(S.DESCRICAO), S.VEICULO_ID, V.DESCRICAO, S.VALOR FROM SECOES S INNER JOIN VEICULOS V ON S.VEICULO_ID = V.ID WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getDescricao())) {

			sql.append(" AND SEM_ACENTOS(DESCRICAO) ILIKE ?");
		}
		
		
		if (!TSUtil.isEmpty(model.getVeiculo()) && !TSUtil.isEmpty(model.getVeiculo().getId())) {

			sql.append(" AND S.VEICULO_ID = ?");
		}
		
		sql.append(" AND S.FLAG_ATIVO = ?");

		sql.append(" ORDER BY S.DESCRICAO, V.DESCRICAO");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getDescricao())) {

			broker.set("%" + model.getDescricao() + "%");

		}
		
		if (!TSUtil.isEmpty(model.getVeiculo()) && !TSUtil.isEmpty(model.getVeiculo().getId())) {

			broker.set(model.getVeiculo().getId());
		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Secao.class, "id", "descricao", "veiculo.id", "veiculo.descricao", "valor");
	}

	public Secao obter(Secao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("secaodao.obter", model.getId());

		return (Secao) broker.getObjectBean(Secao.class, "id", "descricao", "flagAtivo", "veiculo.id", "valor");
	}

	public void excluir(Secao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("secaodao.excluir", model.getId());

		broker.execute();

	}

	public void alterar(Secao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setPropertySQL("secaodao.alterar", model.getDescricao(), model.getFlagAtivo(), model.getVeiculo().getId(), model.getValor(), model.getId());

		broker.execute();

		new SecaoRepresentanteDAO().excluirComBroker(new SecaoRepresentante(new Secao(model.getId())), broker);
		
		if(!TSUtil.isEmpty(model.getSecaoRepresentantes())){
			
			for (SecaoRepresentante item : model.getSecaoRepresentantes()) {

				item.setSecao(new Secao(model.getId()));

				new SecaoRepresentanteDAO().inserirComBroker(item, broker);

			}					
			
		}
		
		broker.endTransaction();

	}

	public Secao inserir(Secao model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("secoes_id_seq"));

		broker.setPropertySQL("secaodao.inserir", model.getId(), model.getDescricao(), model.getFlagAtivo(), model.getVeiculo().getId(), model.getValor());

		broker.execute();
		
		if(!TSUtil.isEmpty(model.getSecaoRepresentantes())){
			
			for (SecaoRepresentante item : model.getSecaoRepresentantes()) {

				item.setSecao(new Secao(model.getId()));

				new SecaoRepresentanteDAO().inserirComBroker(item, broker);

			}			
			
		}
		
		broker.endTransaction();		

		return model;

	}

}
