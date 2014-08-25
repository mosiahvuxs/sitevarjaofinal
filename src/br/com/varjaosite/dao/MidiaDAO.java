package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Cliente;
import br.com.varjaosite.model.Interesse;
import br.com.varjaosite.model.Midia;
import br.com.varjaosite.model.MidiaClienteInteresse;
import br.com.varjaosite.model.MidiaEnvio;
import br.com.varjaosite.util.Constantes;

public class MidiaDAO {

	public Midia inserir(Midia model, List<Cliente> clientes) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		model.setId(broker.getSequenceNextValue("midias_id_seq"));

		broker.setPropertySQL("midiadao.inserir",

		model.getId(), model.getData(), model.getFlagAtivo(), model.getSecao().getId(), model.getPauta().getId(),

		model.getAvaliacao().getId(), model.getTipoMidia().getId(), model.getUsuario().getId(),

		model.getTitulo(), model.getChamada(), model.getFlagEnviado());

		broker.execute();

		if (model.getTipoMidia().getId().equals(Constantes.AUDIO)) {

			model.getAudio().setMidia(model);

			new AudioDAO().inserir(model.getAudio(), broker);

		} else if (model.getTipoMidia().getId().equals(Constantes.VIDEO)) {

			model.getVideo().setMidia(model);

			new VideoDAO().inserir(model.getVideo(), broker);

		} else if (model.getTipoMidia().getId().equals(Constantes.IMPRESSO)) {

			model.getImpresso().setMidia(model);

			new ImpressoDAO().inserir(model.getImpresso(), broker);

		} else if (model.getTipoMidia().getId().equals(Constantes.WEB)) {

			model.getWeb().setMidia(model);

			new WebDAO().inserir(model.getWeb(), broker);
		}

		if (!TSUtil.isEmpty(clientes)) {

			for (Cliente item : clientes) {

				MidiaClienteInteresse mci = new MidiaClienteInteresse();

				if (item.getSelecionado()) {

					mci.setCliente(item);

					mci.setMidia(model);

					mci.setInteresse(new Interesse());

					new MidiaClienteInteresseDAO().inserir(mci, broker);
				}

			}

			for (Cliente cliente : clientes) {

				if (!TSUtil.isEmpty(cliente.getInteresses())) {

					for (Interesse interesse : cliente.getInteresses()) {

						if (interesse.getSelecionado()) {

							MidiaClienteInteresse mci = new MidiaClienteInteresse();

							mci.setCliente(cliente);

							mci.setMidia(model);

							mci.setInteresse(interesse);

							new MidiaClienteInteresseDAO().inserir(mci, broker);
						}
					}
				}
			}
		}

		broker.endTransaction();

		return model;

	}

	public void alterar(Midia model, List<Cliente> clientes) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.beginTransaction();

		broker.setPropertySQL("midiadao.alterar",

		model.getData(), model.getFlagAtivo(), model.getSecao().getId(), model.getPauta().getId(), model.getAvaliacao().getId(),

		model.getTipoMidia().getId(), model.getUsuario().getId(),

		model.getTitulo(), model.getChamada(), model.getFlagEnviado(), model.getId());

		broker.execute();

		if (model.getTipoMidia().getId().equals(Constantes.AUDIO)) {

			model.getAudio().setMidia(model);

			new AudioDAO().alterar(model.getAudio(), broker);

		} else if (model.getTipoMidia().getId().equals(Constantes.VIDEO)) {

			model.getVideo().setMidia(model);

			new VideoDAO().alterar(model.getVideo(), broker);

		} else if (model.getTipoMidia().getId().equals(Constantes.IMPRESSO)) {

			model.getImpresso().setMidia(model);

			new ImpressoDAO().alterar(model.getImpresso(), broker);

		} else if (model.getTipoMidia().getId().equals(Constantes.WEB)) {

			model.getWeb().setMidia(model);

			new WebDAO().alterar(model.getWeb(), broker);
		}

		MidiaClienteInteresseDAO midiaClienteInteresseDAO = new MidiaClienteInteresseDAO();

		midiaClienteInteresseDAO.excluir(model, broker);

		if (!TSUtil.isEmpty(clientes)) {

			for (Cliente item : clientes) {

				MidiaClienteInteresse mci = new MidiaClienteInteresse();

				if (item.getSelecionado()) {

					mci.setCliente(item);

					mci.setMidia(model);

					mci.setInteresse(new Interesse());

					new MidiaClienteInteresseDAO().inserir(mci, broker);
				}

			}

			for (Cliente cliente : clientes) {

				if (!TSUtil.isEmpty(cliente.getInteresses())) {

					for (Interesse interesse : cliente.getInteresses()) {

						if (interesse.getSelecionado()) {

							MidiaClienteInteresse mci = new MidiaClienteInteresse();

							mci.setCliente(cliente);

							mci.setMidia(model);

							mci.setInteresse(interesse);

							new MidiaClienteInteresseDAO().inserir(mci, broker);
						}
					}
				}
			}
		}

		broker.endTransaction();

	}

	@SuppressWarnings("unchecked")
	public List<Midia> pesquisar(MidiaEnvio model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT IMP.NUMERO_PAGINA, AU.DURACAO, VI.DURACAO, M.DATA_CADASTRO, M.ID, AU.ARQUIVO AUDIO, VI.ARQUIVO VIDEO, IMP.ARQUIVO IMPRESSO, W.ARQUIVO ONLINE, M.TITULO, M.CHAMADA, A.ID AVALIACAO_ID, A.DESCRICAO, M.TIPO_MIDIA_ID, TM.DESCRICAO, M.DATA, IMP.CONTEUDO, W.CONTEUDO, W.URL, S.DESCRICAO, V.DESCRICAO FROM MIDIA_ENVIOS ME INNER JOIN MIDIAS M ON ME.MIDIA_ID = M.ID INNER JOIN AVALIACOES A ON A.ID = M.AVALIACAO_ID INNER JOIN TIPO_MIDIAS TM ON TM.ID = M.TIPO_MIDIA_ID LEFT OUTER JOIN AUDIOS AU ON AU.MIDIA_ID = M.ID LEFT OUTER JOIN VIDEOS VI ON VI.MIDIA_ID = M.ID LEFT OUTER JOIN IMPRESSOS IMP ON IMP.MIDIA_ID = M.ID LEFT OUTER JOIN WEB W ON W.MIDIA_ID = M.ID LEFT OUTER JOIN SECOES S ON M.SECAO_ID = S.ID LEFT OUTER JOIN VEICULOS V ON V.ID = S.VEICULO_ID WHERE M.FLAG_ATIVO");

		if (!TSUtil.isEmpty(model.getMidia().getAvaliacao()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getAvaliacao().getId()))) {

			sql.append(" AND M.AVALIACAO_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getMidia().getTipoMidia()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getTipoMidia().getId()))) {

			sql.append(" AND M.TIPO_MIDIA_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getMidia().getTitulo())) {

			sql.append(" AND SEM_ACENTOS(M.TITULO) ILIKE SEM_ACENTOS(?)");
		}

		if (!TSUtil.isEmpty(model.getMidia().getChamada())) {

			sql.append(" AND SEM_ACENTOS(M.CHAMADA) ILIKE SEM_ACENTOS(?)");
		}

		sql.append(" AND CAST(M.DATA AS DATE) BETWEEN TO_DATE(?, 'DD/MM/YYYY') AND TO_DATE(?, 'DD/MM/YYYY')");

		sql.append(" AND ME.CLIENTE_ID = ?");

		sql.append(" GROUP BY IMP.NUMERO_PAGINA, AU.DURACAO, VI.DURACAO, M.ID, AU.ARQUIVO, VI.ARQUIVO, IMP.ARQUIVO, W.ARQUIVO, M.TITULO, M.CHAMADA, A.ID, A.DESCRICAO, M.TIPO_MIDIA_ID, TM.DESCRICAO, M.DATA, IMP.CONTEUDO, W.CONTEUDO, W.URL,S.DESCRICAO, V.DESCRICAO");
		
		sql.append(" ORDER BY A.ORDEM, M.DATA DESC");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getMidia().getAvaliacao()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getAvaliacao().getId()))) {

			broker.set(model.getMidia().getAvaliacao().getId());
		}

		if (!TSUtil.isEmpty(model.getMidia().getTipoMidia()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getTipoMidia().getId()))) {

			broker.set(model.getMidia().getTipoMidia().getId());
		}

		if (!TSUtil.isEmpty(model.getMidia().getTitulo())) {

			broker.set("%" + model.getMidia().getTitulo() + "%");
		}

		if (!TSUtil.isEmpty(model.getMidia().getChamada())) {

			broker.set("%" + model.getMidia().getChamada() + "%");
		}

		broker.set(TSParseUtil.dateToString(model.getDataEnvio(), TSDateUtil.DD_MM_YYYY));

		broker.set(TSParseUtil.dateToString(model.getDataEnvioFinal(), TSDateUtil.DD_MM_YYYY));

		broker.set(model.getCliente().getId());

		return broker.getCollectionBean(Midia.class, "impresso.numeroPagina", "audio.duracao", "video.duracao", "dataCadastro", "id", "audio.arquivo", "video.arquivo", "impresso.arquivo", "web.arquivo", "titulo", "chamada", "avaliacao.id", "avaliacao.descricao", "tipoMidia.id", "tipoMidia.descricao", "data", "impresso.conteudo", "web.conteudo", "web.url", "secao.descricao", "secao.veiculo.descricao");
	}

	public Midia obter(Midia model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("midiadao.obter", model.getId());

		return (Midia) broker.getObjectBean(Midia.class, "flagAtivo", "impresso.numeroPagina", "audio.id", "audio.duracao", "video.id", "video.duracao", "impresso.id", "web.id", "secao.id", "secao.valor", "impresso.tamanho", "web.pixels", "dataCadastro", "id", "audio.arquivo", "video.arquivo", "impresso.arquivo", "web.arquivo", "titulo", "chamada", "avaliacao.id", "avaliacao.descricao", "tipoMidia.id", "tipoMidia.descricao", "data", "impresso.conteudo", "web.conteudo", "web.url", "secao.descricao", "secao.veiculo.descricao");
	}

	public Midia obterPorCodigoIntegracao(Midia model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("midiadao.obterPorCodigoIntegracao", model.getCodigoIntegracao());
		
		return (Midia) broker.getObjectBean(Midia.class, "flagAtivo", "impresso.numeroPagina", "audio.id", "audio.duracao", "video.id", "video.duracao", "impresso.id", "web.id", "secao.id", "secao.valor", "impresso.tamanho", "web.pixels", "dataCadastro", "id", "audio.arquivo", "video.arquivo", "impresso.arquivo", "web.arquivo", "titulo", "chamada", "avaliacao.id", "avaliacao.descricao", "tipoMidia.id", "tipoMidia.descricao", "data", "impresso.conteudo", "web.conteudo", "web.url", "secao.descricao", "secao.veiculo.descricao");
	}

	public void excluir(Midia model) throws TSApplicationException {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		broker.setPropertySQL("midiadao.excluir", model.getId());

		broker.execute();
	}

}
