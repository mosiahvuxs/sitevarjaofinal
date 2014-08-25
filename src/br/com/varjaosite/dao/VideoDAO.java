package br.com.varjaosite.dao;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.Video;

public class VideoDAO {

	public void inserir(Video model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("videos_id_seq"));

		broker.setPropertySQL("videodao.inserir", model.getId(), model.getMidia().getId(), model.getMidia().getArquivo(), model.getDuracao(), model.getRepresentante().getId());

		broker.execute();

	}

	public void alterar(Video model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("videodao.alterar", model.getMidia().getId(), model.getMidia().getArquivo(), model.getDuracao(), model.getId(), model.getRepresentante().getId());

		broker.execute();

	}

}
