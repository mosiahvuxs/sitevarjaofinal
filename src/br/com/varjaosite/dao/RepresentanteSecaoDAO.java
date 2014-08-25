package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.RepresentanteSecao;

public class RepresentanteSecaoDAO {

	@SuppressWarnings("unchecked")
	public List<RepresentanteSecao> pesquisar(RepresentanteSecao model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT SR.ID, S.ID, S.DESCRICAO, R.ID, R.NOME FROM SECOES_REPRESENTANTES SR INNER JOIN SECOES S ON SR.SECAO_ID = S.ID INNER JOIN REPRESENTANTES R ON SR.REPRESENTANTE_ID = R.ID WHERE S.FLAG_ATIVO AND R.FLAG_ATIVO");
		
		if (!TSUtil.isEmpty(model.getRepresentante()) && !TSUtil.isEmpty(model.getRepresentante().getId())) {

			sql.append(" AND R.ID = ?");
		}
		
		if (!TSUtil.isEmpty(model.getSecao()) && !TSUtil.isEmpty(model.getSecao().getId())) {

			sql.append(" AND S.ID = ?");
		}
		
		sql.append(" ORDER BY S.DESCRICAO, R.NOME");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getRepresentante()) && !TSUtil.isEmpty(model.getRepresentante().getId())) {

			broker.set(model.getRepresentante().getId());
		}
		
		if (!TSUtil.isEmpty(model.getSecao()) && !TSUtil.isEmpty(model.getSecao().getId())) {

			broker.set(model.getSecao().getId());
		}

		return broker.getCollectionBean(RepresentanteSecao.class, "id", "secao.id", "secao.descricao", "representante.id", "representante.nome");
	}

}
