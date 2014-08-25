package br.com.varjaosite.model;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import br.com.topsys.file.TSFile;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.varjaosite.util.Constantes;
import br.com.varjaosite.util.Utilitarios;

@SuppressWarnings("serial")
public class Midia implements Serializable {

	private Long id;

	private String chamada;

	private String titulo;

	private Boolean flagAtivo;

	private Boolean flagEnviado;

	private Date data;

	private Pauta pauta;

	private Secao secao;

	private TipoMidia tipoMidia;

	private Usuario usuario;

	private Avaliacao avaliacao;

	private List<MidiaClienteInteresse> midiaClientesInteresses;

	private Impresso impresso;

	private Audio audio;

	private Video video;

	private Veiculo veiculo;

	private String arquivo;

	private Date dataFinal;

	private String enviado;

	private Web web;

	private Cliente cliente;

	private String chamadaFormatada;

	private String tituloFormatado;

	private boolean arquivoEmDisco;

	private String arquivoFormatado;

	private Timestamp dataCadastro;

	private Long codigoIntegracao;

	private boolean exibirValoracao;

	private String valoracao;

	private boolean mp4;

	public Midia() {

	}

	public Midia(Long id) {

		this.id = id;
	}

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Midia other = (Midia) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getChamada() {
		return chamada;
	}

	public void setChamada(String chamada) {
		this.chamada = chamada;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Boolean getFlagEnviado() {
		return flagEnviado;
	}

	public void setFlagEnviado(Boolean flagEnviado) {
		this.flagEnviado = flagEnviado;
	}

	public Pauta getPauta() {
		return pauta;
	}

	public void setPauta(Pauta pauta) {
		this.pauta = pauta;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public TipoMidia getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(TipoMidia tipoMidia) {
		this.tipoMidia = tipoMidia;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Avaliacao getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<MidiaClienteInteresse> getMidiaClientesInteresses() {
		return midiaClientesInteresses;
	}

	public void setMidiaClientesInteresses(List<MidiaClienteInteresse> midiaClientesInteresses) {
		this.midiaClientesInteresses = midiaClientesInteresses;
	}

	public Impresso getImpresso() {
		return impresso;
	}

	public void setImpresso(Impresso impresso) {
		this.impresso = impresso;
	}

	public Audio getAudio() {
		return audio;
	}

	public void setAudio(Audio audio) {
		this.audio = audio;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getEnviado() {

		if (!TSUtil.isEmpty(this.flagEnviado) && this.flagEnviado) {

			this.enviado = "SIM";

		} else {

			this.enviado = "NÃO";
		}

		return enviado;
	}

	public void setEnviado(String enviado) {
		this.enviado = enviado;
	}

	public Web getWeb() {
		return web;
	}

	public void setWeb(Web web) {
		this.web = web;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getChamadaFormatada() {

		if (!TSUtil.isEmpty(this.chamada)) {

			if (this.chamada.length() > 67) {

				this.chamadaFormatada = this.chamada.substring(0, 67).trim() + "...";

			} else {

				this.chamadaFormatada = this.chamada.trim();
			}
		}

		return chamadaFormatada;
	}

	public void setChamadaFormatada(String chamadaFormatada) {
		this.chamadaFormatada = chamadaFormatada;
	}

	public String getTituloFormatado() {

		if (!TSUtil.isEmpty(this.titulo)) {

			if (this.titulo.length() > 67) {

				this.tituloFormatado = this.titulo.substring(0, 67).trim() + "...";

			} else {

				this.tituloFormatado = this.titulo.trim() + "...";
			}
		}

		return tituloFormatado;
	}

	public void setTituloFormatado(String tituloFormatado) {
		this.tituloFormatado = tituloFormatado;
	}

	public boolean isArquivoEmDisco() {

		if (!TSUtil.isEmpty(this.arquivo) && !TSUtil.isEmpty(this.data)) {

			if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

				return new File(Constantes.PASTA_ARQUIVOS + TSParseUtil.dateToString(this.data, TSDateUtil.YYYY) + "\\" + TSParseUtil.dateToString(this.data, TSDateUtil.MM) + "\\" + this.arquivo).exists();

			} else {

				return new File(Constantes.PASTA_ARQUIVOS_UPLOAD + TSParseUtil.dateToString(this.data, TSDateUtil.YYYY) + "/" + TSParseUtil.dateToString(this.data, TSDateUtil.MM) + "/" + this.arquivo).exists();
			}
		}
		return arquivoEmDisco;
	}

	public void setArquivoEmDisco(boolean arquivoEmDisco) {
		this.arquivoEmDisco = arquivoEmDisco;
	}

	public String getArquivoFormatado() {

		if (!TSUtil.isEmpty(this.arquivo)) {

			if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

				this.arquivoFormatado = Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(this.data) + this.arquivo;

			} else {

				this.arquivoFormatado = Constantes.URL_SITE_PRODUCAO + Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(this.data) + this.arquivo;
			}

		}

		return arquivoFormatado;
	}

	public void setArquivoFormatado(String arquivoFormatado) {
		this.arquivoFormatado = arquivoFormatado;
	}

	public String getExtensaoArquivo() {

		if (!TSUtil.isEmpty(this.arquivo)) {

			return TSFile.obterExtensaoArquivo(this.arquivo).replace(".", "");
		}

		return null;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Long getCodigoIntegracao() {
		return TSUtil.tratarLong(codigoIntegracao);
	}

	public void setCodigoIntegracao(Long codigoIntegracao) {
		this.codigoIntegracao = codigoIntegracao;
	}

	public boolean isExibirValoracao() {

		if (!TSUtil.isEmpty(this.cliente) && !TSUtil.isEmpty(this.cliente.getId()) && !TSUtil.isEmpty(this.tipoMidia) && !TSUtil.isEmpty(this.tipoMidia.getId())) {

			if (this.tipoMidia.getId().equals(Constantes.AUDIO)) {

				this.exibirValoracao = this.cliente.getFlagValorAudio();

			} else if (this.tipoMidia.getId().equals(Constantes.VIDEO)) {

				this.exibirValoracao = this.cliente.getFlagValorVideo();

			} else if (this.tipoMidia.getId().equals(Constantes.IMPRESSO)) {

				this.exibirValoracao = this.cliente.getFlagValorImpresso();

			} else if (this.tipoMidia.getId().equals(Constantes.WEB)) {

				this.exibirValoracao = this.cliente.getFlagValorWeb();
			}

		} else {

			this.exibirValoracao = false;
		}

		return exibirValoracao;
	}

	public void setExibirValoracao(boolean exibirValoracao) {

		this.exibirValoracao = exibirValoracao;
	}

	public String getValoracao() {

		if (!TSUtil.isEmpty(this.secao) && !TSUtil.isEmpty(this.secao.getId()) && !TSUtil.isEmpty(this.tipoMidia) && !TSUtil.isEmpty(this.tipoMidia.getId())) {

			if (this.tipoMidia.getId().equals(Constantes.AUDIO) && !TSUtil.isEmpty(this.audio) && !TSUtil.isEmpty(this.audio.getId())) {

				this.valoracao = this.getSecao().getValor().multiply(new BigDecimal(Utilitarios.valorEmSegundos(this.audio.getDuracao()).toString())).toString();

			} else if (this.tipoMidia.getId().equals(Constantes.VIDEO) && !TSUtil.isEmpty(this.video) && !TSUtil.isEmpty(this.video.getId())) {

				this.valoracao = this.getSecao().getValor().multiply(new BigDecimal(Utilitarios.valorEmSegundos(this.video.getDuracao()).toString())).toString();

			} else if (this.tipoMidia.getId().equals(Constantes.IMPRESSO) && !TSUtil.isEmpty(this.impresso) && !TSUtil.isEmpty(this.impresso.getId())) {

				this.valoracao = this.getSecao().getValor().multiply(new BigDecimal(this.impresso.getTamanho().toString())).toString();

			} else if (!TSUtil.isEmpty(this.web) && !TSUtil.isEmpty(this.web.getId())) {

				this.valoracao = this.getSecao().getValor().multiply(new BigDecimal(this.web.getPixels().toString())).toString();
			}
		}

		return valoracao;
	}

	public void setValoracao(String valoracao) {
		this.valoracao = valoracao;
	}

	public boolean isMp4() {

		if (!TSUtil.isEmpty(TSUtil.tratarString(this.getExtensaoArquivo()))) {

			if (this.getExtensaoArquivo().equals("mp4")) {

				this.mp4 = true;

			} else {

				this.mp4 = false;
			}
		}

		return mp4;
	}

	public void setMp4(boolean mp4) {
		this.mp4 = mp4;
	}

}
