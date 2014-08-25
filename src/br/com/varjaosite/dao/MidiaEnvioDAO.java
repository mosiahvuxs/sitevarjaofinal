package br.com.varjaosite.dao;

import java.sql.Timestamp;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.varjaosite.model.MidiaEnvio;

public class MidiaEnvioDAO {

	public void inserir(MidiaEnvio model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		model.setId(broker.getSequenceNextValue("midia_envios_id_seq"));

		broker.setPropertySQL("midiaenviodao.inserir",

		model.getId(), model.getMidia().getId(), new Timestamp(System.currentTimeMillis()), model.getUsuario().getId(), model.getCliente().getId(), Boolean.FALSE, null, Boolean.TRUE, Boolean.TRUE, new Timestamp(System.currentTimeMillis()));

		broker.execute();

	}

	public void setarVisualizacao(MidiaEnvio model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("midiaenviodao.setarVisualizacao", new Timestamp(System.currentTimeMillis()), model.getMidia().getId(), model.getCliente().getId());

		broker.execute();

	}

}
