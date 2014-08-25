package br.com.varjaosite.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Audio;

public class AudioDAO {

	public void inserir(Audio model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("audios_id_seq"));

		broker.setPropertySQL("audiodao.inserir",

		model.getId(), model.getMidia().getId(), model.getMidia().getArquivo(), model.getDuracao(), model.getRepresentante().getId());

		broker.execute();

	}

	public void alterar(Audio model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("audiodao.alterar",

		model.getMidia().getId(), model.getMidia().getArquivo(), model.getDuracao(), model.getId(), model.getRepresentante().getId());

		broker.execute();

	}

}
