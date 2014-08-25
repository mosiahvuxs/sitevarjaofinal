package br.com.varjaosite.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Impresso;

public class ImpressoDAO {

	public void inserir(Impresso model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("impressos_id_seq"));

		broker.setPropertySQL("impressodao.inserir",

		model.getId(), model.getNumeroPagina(), model.getTamanho(), model.getMidia().getArquivo(), model.getUrl(), model.getId(), model.getMidia().getId());

		broker.execute();

	}

	public void alterar(Impresso model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("impressodao.alterar",

		model.getNumeroPagina(),

		model.getTamanho(),

		model.getMidia().getArquivo(),

		model.getUrl(),

		model.getMidia().getId(),

		model.getId());

		broker.execute();

	}

}
