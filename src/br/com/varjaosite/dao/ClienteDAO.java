package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Assunto;
import br.com.varjaosite.model.Cliente;
import br.com.varjaosite.model.ClienteEmailAvaliacao;
import br.com.varjaosite.model.Interesse;
import br.com.varjaosite.util.Utilitarios;

public class ClienteDAO {

	public Cliente obterPorLoginSenha(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.obterPorLoginSenha", model.getAutenticacao().getLogin(), model.getAutenticacao().getSenha());

		return (Cliente) broker.getObjectBean(Cliente.class, "id", "nome", "flagAtivo", "logomarca", "parceiro.id", "contato", "email", "autenticacao.login", "autenticacao.senha", "flagImpresso", "flagAudio", "flagVideo", "flagEnvioCompleto", "flagExigeSenha", "endereco.logradouro", "endereco.bairro", "endereco.numero", "endereco.cep", "endereco.complemento", "flagEnviarAvaliacao", "flagValorImpresso", "flagValorAudio", "flagValorVideo", "endereco.cidade.id", "endereco.estado.id", "tipoCliente.id", "flagWeb", "flagValorWeb");
	}

	public Cliente inserir(Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("clientes_id_seq"));

		broker.setPropertySQL("clientedao.inserir", model.getId(), model.getNome(), model.getFlagAtivo(), model.getLogomarca(), model.getParceiro().getId(), model.getContato(), model.getEmail(), model.getAutenticacao().getLogin(), model.getAutenticacao().getSenha(), model.getFlagImpresso(), model.getFlagAudio(), model.getFlagVideo(), model.getFlagEnvioCompleto(), model.getFlagExigeSenha(), model.getEndereco().getLogradouro(), model.getEndereco().getBairro(), model.getEndereco().getNumero(), model.getEndereco().getCep(), model.getEndereco().getComplemento(), model.getFlagEnviarAvaliacao(), model.getFlagValorImpresso(), model.getFlagValorAudio(), model.getFlagValorVideo(), model.getEndereco().getCidade().getId(), model.getEndereco().getEstado().getId(), model.getTipoCliente().getId(), model.getFlagWeb(), model.getFlagValorWeb());

		broker.execute();

		if (!TSUtil.isEmpty(model.getAssuntos())) {

			for (Assunto assunto : model.getAssuntos()) {

				assunto.setCliente(new Cliente(model.getId()));

				new AssuntoDAO().inserirComBroker(assunto, broker);

			}

		}

		if (!TSUtil.isEmpty(model.getInteresses())) {

			for (Interesse interesse : model.getInteresses()) {

				interesse.setCliente(new Cliente(model.getId()));

				new InteresseDAO().inserirComBroker(interesse, broker);

			}

		}

		if (!TSUtil.isEmpty(model.getListaClienteEmailAvaliacao())) {

			ClienteEmailAvaliacaoDAO clienteEmailAvaliacaoDAO = new ClienteEmailAvaliacaoDAO();

			for (ClienteEmailAvaliacao item : model.getListaClienteEmailAvaliacao()) {

				item.setCliente(model);

				clienteEmailAvaliacaoDAO.inserir(item, broker);

			}

		}

		broker.endTransaction();

		return model;

	}

	public void excluir(Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.excluir", model.getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<Cliente> pesquisar(Cliente model) {

		StringBuilder sql = new StringBuilder();

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		sql.append("SELECT ID, UPPER(NOME) FROM CLIENTES WHERE FLAG_ATIVO = ?");

		if (!TSUtil.isEmpty(Utilitarios.tratarString(model.getNome()))) {

			sql.append(" AND SEM_ACENTOS(NOME) ILIKE ? ");

		}

		if (model.getFlagAudio()) {

			sql.append(" AND FLAG_AUDIO = ?");
		}

		if (model.getFlagVideo()) {

			sql.append(" AND FLAG_VIDEO = ?");
		}

		if (model.getFlagImpresso()) {

			sql.append(" AND FLAG_IMPRESSO = ?");
		}

		if (model.getFlagWeb()) {

			sql.append(" AND FLAG_WEB = ?");
		}

		sql.append(" AND FLAG_ATIVO = ?");

		sql.append(" ORDER BY NOME");

		broker.setSQL(sql.toString());

		broker.set(model.getFlagAtivo());

		if (!TSUtil.isEmpty(model.getNome())) {

			broker.set("%" + model.getNome() + "%");

		}

		if (model.getFlagAudio()) {

			broker.set(model.getFlagAudio());
		}

		if (model.getFlagVideo()) {

			broker.set(model.getFlagVideo());
		}

		if (model.getFlagImpresso()) {

			broker.set(model.getFlagImpresso());
		}

		if (model.getFlagWeb()) {

			broker.set(model.getFlagWeb());
		}

		broker.set(model.getFlagAtivo());

		return broker.getCollectionBean(Cliente.class, "id", "nome");
	}

	public Cliente obter(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.obter", model.getId());

		return (Cliente) broker.getObjectBean(Cliente.class, "id", "nome", "flagAtivo", "logomarca", "parceiro.id", "contato", "email", "autenticacao.login", "autenticacao.senha", "flagImpresso", "flagAudio", "flagVideo", "flagEnvioCompleto", "flagExigeSenha", "endereco.logradouro", "endereco.bairro", "endereco.numero", "endereco.cep", "endereco.complemento", "flagEnviarAvaliacao", "flagValorImpresso", "flagValorAudio", "flagValorVideo", "endereco.cidade.id", "endereco.estado.id", "tipoCliente.id", "flagWeb", "flagValorWeb");
	}

	public Cliente obterPorCodigoIntegracao(Cliente model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("clientedao.obterPorCodigoIntegracao", model.getCodigoIntegracao());

		return (Cliente) broker.getObjectBean(Cliente.class, "id", "nome", "flagAtivo", "logomarca", "parceiro.id", "contato", "email", "autenticacao.login", "autenticacao.senha", "flagImpresso", "flagAudio", "flagVideo", "flagEnvioCompleto", "flagExigeSenha", "endereco.logradouro", "endereco.bairro", "endereco.numero", "endereco.cep", "endereco.complemento", "flagEnviarAvaliacao", "flagValorImpresso", "flagValorAudio", "flagValorVideo", "endereco.cidade.id", "endereco.estado.id", "tipoCliente.id", "flagWeb", "flagValorWeb");
	}

	public void alterar(Cliente model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setPropertySQL("clientedao.alterar", model.getNome(), model.getFlagAtivo(), model.getLogomarca(), model.getParceiro().getId(), model.getContato(), model.getEmail(), model.getAutenticacao().getLogin(), model.getAutenticacao().getSenha(), model.getFlagImpresso(), model.getFlagAudio(), model.getFlagVideo(), model.getFlagEnvioCompleto(), model.getFlagExigeSenha(), model.getEndereco().getLogradouro(), model.getEndereco().getBairro(), model.getEndereco().getNumero(), model.getEndereco().getCep(), model.getEndereco().getComplemento(), model.getFlagEnviarAvaliacao(), model.getFlagValorImpresso(), model.getFlagValorAudio(), model.getFlagValorVideo(), model.getEndereco().getCidade().getId(), model.getEndereco().getEstado().getId(), model.getTipoCliente().getId(), model.getFlagWeb(), model.getFlagValorWeb(), model.getId());

		broker.execute();

		new AssuntoDAO().excluirComBroker(new Assunto(model), broker);

		if (!TSUtil.isEmpty(model.getAssuntos())) {

			for (Assunto assunto : model.getAssuntos()) {

				assunto.setCliente(new Cliente(model.getId()));

				new AssuntoDAO().inserirComBroker(assunto, broker);

			}

		}

		new InteresseDAO().excluirComBroker(new Interesse(model), broker);

		if (!TSUtil.isEmpty(model.getInteresses())) {

			for (Interesse interesse : model.getInteresses()) {

				interesse.setCliente(new Cliente(model.getId()));

				new InteresseDAO().inserirComBroker(interesse, broker);

			}

		}

		ClienteEmailAvaliacaoDAO clienteEmailAvaliacaoDAO = new ClienteEmailAvaliacaoDAO();

		clienteEmailAvaliacaoDAO.excluir(model, broker);

		if (!TSUtil.isEmpty(model.getListaClienteEmailAvaliacao())) {

			for (ClienteEmailAvaliacao item : model.getListaClienteEmailAvaliacao()) {

				item.setCliente(model);

				clienteEmailAvaliacaoDAO.inserir(item, broker);

			}

		}

		broker.endTransaction();

	}

}
