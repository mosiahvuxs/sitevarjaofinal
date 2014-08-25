package br.com.varjaosite.dao;

import java.util.List;

import br.com.topsys.database.TSDataBaseBrokerIf;
import br.com.topsys.database.factory.TSDataBaseBrokerFactory;
import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Midia;
import br.com.varjaosite.model.MidiaClienteInteresse;

public class MidiaClienteInteresseDAO {

	public void inserir(MidiaClienteInteresse model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		model.setId(broker.getSequenceNextValue("midia_cliente_interesses_id_seq"));

		broker.setPropertySQL("midiaclienteinteressedao.inserir",

		model.getId(), model.getCliente().getId(), model.getInteresse().getId(), model.getMidia().getId());

		broker.execute();

	}

	public void excluir(Midia model, TSDataBaseBrokerIf broker) throws TSApplicationException {

		broker.setPropertySQL("midiaclienteinteressedao.excluir", model.getId());

		broker.execute();

	}

	@SuppressWarnings("unchecked")
	public List<MidiaClienteInteresse> pesquisar(MidiaClienteInteresse model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT MCI.ID, MCI.CLIENTE_ID, C.NOME, C.EMAIL, MCI.INTERESSE_ID, MCI.MIDIA_ID, M.CHAMADA, M.TITULO, M.DATA, TM.ID, TM.DESCRICAO, A.ARQUIVO AUDIO, V.ARQUIVO VIDEO, I.ARQUIVO IMPRESSO FROM MIDIA_CLIENTE_INTERESSES MCI INNER JOIN CLIENTES C ON MCI.CLIENTE_ID = C.ID INNER JOIN MIDIAS M ON MCI.MIDIA_ID = M.ID INNER JOIN TIPO_MIDIAS TM ON M.TIPO_MIDIA_ID = TM.ID LEFT OUTER JOIN AUDIOS A ON A.MIDIA_ID = M.ID LEFT OUTER JOIN VIDEOS V ON V.MIDIA_ID = M.ID LEFT OUTER JOIN IMPRESSOS I ON I.MIDIA_ID = M.ID WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getId())) {

			sql.append(" AND MCI.CLIENTE_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getId())) {

			sql.append(" AND MCI.MIDIA_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getTipoMidia()) && !TSUtil.isEmpty(model.getMidia().getTipoMidia().getId())) {

			sql.append(" AND M.TIPO_MIDIA_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getData()) && !TSUtil.isEmpty(model.getMidia().getDataFinal())) {

			sql.append(" AND M.DATA BETWEEN ? AND ?");
		}

		sql.append(" ORDER BY C.NOME, M.DATA_CADASTRO DESC");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(model.getCliente().getId())) {

			broker.set(model.getCliente().getId());
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getId())) {

			broker.set(model.getMidia().getId());
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getTipoMidia()) && !TSUtil.isEmpty(model.getMidia().getTipoMidia().getId())) {

			broker.set(model.getMidia().getTipoMidia().getId());
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getData()) && !TSUtil.isEmpty(model.getMidia().getDataFinal())) {

			broker.set(model.getMidia().getData());
			broker.set(model.getMidia().getDataFinal());
		}

		return broker.getCollectionBean(MidiaClienteInteresse.class, "id", "cliente.id", "cliente.nome", "cliente.email", "interesse.id", "midia.id", "midia.chamada", "midia.titulo", "midia.data", "midia.tipoMidia.id", "midia.tipoMidia.descricao", "midia.audio.arquivo", "midia.video.arquivo", "midia.impresso.arquivo");
	}

	@SuppressWarnings("unchecked")
	public List<MidiaClienteInteresse> findEnvioMidias(MidiaClienteInteresse model) {

		TSDataBaseBrokerIf broker = TSDataBaseBrokerFactory.getDataBaseBrokerIf();

		StringBuilder sql = new StringBuilder();

		sql.append("SELECT M.ID, M.DATA, M.FLAG_ATIVO, M.PAUTA_ID, M.AVALIACAO_ID, AVA.DESCRICAO, M.TIPO_MIDIA_ID, TM.DESCRICAO, M.USUARIO_ID, M.TITULO, M.CHAMADA, M.FLAG_ENVIADO, I.ID, I.NUMERO_PAGINA, I.CONTEUDO, I.TAMANHO, I.ARQUIVO, I.URL, A.ID, A.ARQUIVO, A.DURACAO, A.REPRESENTANTE_ID, V.ID, V.ARQUIVO, V.DURACAO, V.REPRESENTANTE_ID FROM PUBLIC.MIDIAS M LEFT OUTER JOIN IMPRESSOS I ON I.MIDIA_ID = M.ID LEFT OUTER JOIN AUDIOS A ON A.MIDIA_ID = M.ID LEFT OUTER JOIN VIDEOS V ON V.MIDIA_ID = M.ID INNER JOIN TIPO_MIDIAS TM ON M.TIPO_MIDIA_ID = TM.ID INNER JOIN AVALIACOES AVA ON AVA.ID = M.AVALIACAO_ID WHERE 1 = 1");

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getAvaliacao()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getAvaliacao().getId()))) {

			sql.append(" AND M.AVALIACAO_ID = ?");
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getPauta()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getPauta().getId()))) {

			sql.append(" AND M.PAUTA_ID = ?");
		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getMidia().getTitulo()))) {

			sql.append(" AND SEM_ACENTOS(M.TITULO) ILIKE ?");
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getTipoMidia()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getTipoMidia().getId()))) {

			sql.append(" AND M.TIPO_MIDIA_ID = ?");
		}

		sql.append(" AND M.DATA BETWEEN ? AND ?");

		sql.append(" AND M.FLAG_ENVIADO = ?");

		sql.append(" AND M.FLAG_ATIVO = ?");

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getCliente().getId()))) {

			sql.append(" AND EXISTS (SELECT MCI.MIDIA_ID FROM MIDIA_CLIENTE_INTERESSES MCI WHERE MCI.CLIENTE_ID = ? AND MCI.MIDIA_ID = M.ID)");
		}

		sql.append(" ORDER BY M.DATA_CADASTRO DESC");

		broker.setSQL(sql.toString());

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getAvaliacao()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getAvaliacao().getId()))) {

			broker.set(model.getMidia().getAvaliacao().getId());
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getPauta()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getPauta().getId()))) {

			broker.set(model.getMidia().getPauta().getId());
		}

		if (!TSUtil.isEmpty(TSUtil.tratarString(model.getMidia().getTitulo()))) {

			broker.set("%" + model.getMidia().getTitulo() + "%");
		}

		if (!TSUtil.isEmpty(model.getMidia()) && !TSUtil.isEmpty(model.getMidia().getTipoMidia()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getMidia().getTipoMidia().getId()))) {

			broker.set(model.getMidia().getTipoMidia().getId());
		}

		broker.set(model.getMidia().getData());

		broker.set(model.getMidia().getDataFinal());

		broker.set(model.getMidia().getFlagEnviado());

		broker.set(model.getMidia().getFlagAtivo());

		if (!TSUtil.isEmpty(model.getCliente()) && !TSUtil.isEmpty(TSUtil.tratarLong(model.getCliente().getId()))) {

			broker.set(model.getCliente().getId());
		}

		return broker.getCollectionBean(MidiaClienteInteresse.class, "midia.id", "midia.data", "midia.flagAtivo", "midia.pauta.id", "midia.avaliacao.id", "midia.avaliacao.descricao", "midia.tipoMidia.id", "midia.tipoMidia.descricao", "midia.usuario.id", "midia.titulo", "midia.chamada", "midia.flagEnviado", "midia.impresso.id", "midia.impresso.numeroPagina", "midia.impresso.conteudo", "midia.impresso.tamanho", "midia.impresso.arquivo", "midia.impresso.url", "midia.audio.id", "midia.audio.arquivo", "midia.audio.duracao", "midia.audio.representante.id", "midia.video.id", "midia.video.arquivo", "midia.video.duracao", "midia.video.representante.id");
	}

}
