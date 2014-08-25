package br.com.varjaosite.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Web;

public class WebDAO {

	public void inserir(Web model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("web_id_seq"));

		broker.setPropertySQL("webdao.inserir",
				
		model.getId(), model.getNumeroPagina(), model.getTamanho(), model.getMidia().getArquivo(), model.getUrl(), model.getMidia().getId(), model.getConteudo());

		broker.execute();

	}

	public void alterar(Web model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("webdao.alterar",

		model.getNumeroPagina(),

		model.getTamanho(),

		model.getMidia().getArquivo(),

		model.getUrl(),

		model.getMidia().getId(),
		
		model.getConteudo(),

		model.getId());

		broker.execute();

	}

}
