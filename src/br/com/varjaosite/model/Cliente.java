package br.com.varjaosite.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.varjaosite.util.Constantes;

@SuppressWarnings("serial")
public class Cliente implements Serializable {

	private Long id;

	private String nome;

	private String logomarca;

	private Boolean flagAtivo = Boolean.TRUE;

	private Parceiro parceiro;

	private TipoCliente tipoCliente;

	private String contato;

	private String email;

	private Endereco endereco;

	private Boolean flagImpresso = Boolean.FALSE;

	private Boolean flagAudio = Boolean.FALSE;

	private Boolean flagVideo = Boolean.FALSE;

	private Boolean flagValorImpresso = Boolean.FALSE;

	private Boolean flagValorAudio = Boolean.FALSE;

	private Boolean flagValorVideo = Boolean.FALSE;

	private Boolean flagEnviarAvaliacao = Boolean.FALSE;

	private Boolean flagExigeSenha = Boolean.FALSE;

	private Boolean flagEnvioCompleto = Boolean.FALSE;

	private List<Interesse> interesses;

	private List<Assunto> assuntos;

	private Autenticacao autenticacao;

	private Boolean selecionado;

	private Boolean flagWeb = Boolean.FALSE;

	private List<ClienteEmailAvaliacao> listaClienteEmailAvaliacao;

	private Timestamp dataCadastro;

	private String logoMarcaFormatada;

	private Boolean flagValorWeb = Boolean.FALSE;

	private Long codigoIntegracao;

	public Cliente() {

	}

	public Cliente(Autenticacao autenticacao) {

		this.autenticacao = autenticacao;
	}

	public Cliente(Long id) {

		this.id = id;

	}

	public Cliente(Boolean value) {

		this.flagAtivo = value;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

	public Parceiro getParceiro() {
		return parceiro;
	}

	public void setParceiro(Parceiro parceiro) {
		this.parceiro = parceiro;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getFlagImpresso() {
		return flagImpresso;
	}

	public void setFlagImpresso(Boolean flagImpresso) {
		this.flagImpresso = flagImpresso;
	}

	public Boolean getFlagAudio() {
		return flagAudio;
	}

	public void setFlagAudio(Boolean flagAudio) {
		this.flagAudio = flagAudio;
	}

	public Boolean getFlagVideo() {
		return flagVideo;
	}

	public void setFlagVideo(Boolean flagVideo) {
		this.flagVideo = flagVideo;
	}

	public Boolean getFlagValorImpresso() {
		return flagValorImpresso;
	}

	public void setFlagValorImpresso(Boolean flagValorImpresso) {
		this.flagValorImpresso = flagValorImpresso;
	}

	public Boolean getFlagValorAudio() {
		return flagValorAudio;
	}

	public void setFlagValorAudio(Boolean flagValorAudio) {
		this.flagValorAudio = flagValorAudio;
	}

	public Boolean getFlagValorVideo() {
		return flagValorVideo;
	}

	public void setFlagValorVideo(Boolean flagValorVideo) {
		this.flagValorVideo = flagValorVideo;
	}

	public Boolean getFlagEnviarAvaliacao() {
		return flagEnviarAvaliacao;
	}

	public void setFlagEnviarAvaliacao(Boolean flagEnviarAvaliacao) {
		this.flagEnviarAvaliacao = flagEnviarAvaliacao;
	}

	public Boolean getFlagExigeSenha() {
		return flagExigeSenha;
	}

	public void setFlagExigeSenha(Boolean flagExigeSenha) {
		this.flagExigeSenha = flagExigeSenha;
	}

	public Boolean getFlagEnvioCompleto() {
		return flagEnvioCompleto;
	}

	public void setFlagEnvioCompleto(Boolean flagEnvioCompleto) {
		this.flagEnvioCompleto = flagEnvioCompleto;
	}

	public String getLogomarca() {
		return logomarca;
	}

	public void setLogomarca(String logomarca) {
		this.logomarca = logomarca;
	}

	public List<Interesse> getInteresses() {
		return interesses;
	}

	public void setInteresses(List<Interesse> interesses) {
		this.interesses = interesses;
	}

	public List<Assunto> getAssuntos() {
		return assuntos;
	}

	public void setAssuntos(List<Assunto> assuntos) {
		this.assuntos = assuntos;
	}

	public Autenticacao getAutenticacao() {
		return autenticacao;
	}

	public void setAutenticacao(Autenticacao autenticacao) {
		this.autenticacao = autenticacao;
	}

	public Boolean getSelecionado() {
		return selecionado;
	}

	public void setSelecionado(Boolean selecionado) {
		this.selecionado = selecionado;
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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Boolean getFlagWeb() {
		return flagWeb;
	}

	public void setFlagWeb(Boolean flagWeb) {
		this.flagWeb = flagWeb;
	}

	public List<ClienteEmailAvaliacao> getListaClienteEmailAvaliacao() {
		return listaClienteEmailAvaliacao;
	}

	public void setListaClienteEmailAvaliacao(List<ClienteEmailAvaliacao> listaClienteEmailAvaliacao) {
		this.listaClienteEmailAvaliacao = listaClienteEmailAvaliacao;
	}

	public Timestamp getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Timestamp dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getLogoMarcaFormatada() {

		if (!TSUtil.isEmpty(this.logomarca)) {

			if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

				this.logoMarcaFormatada = Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(this.dataCadastro) + this.logomarca;

			} else {

				this.logoMarcaFormatada = Constantes.URL_SITE_PRODUCAO + Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(this.dataCadastro) + this.logomarca;
			}

		}

		return logoMarcaFormatada;
	}

	public void setLogoMarcaFormatada(String logoMarcaFormatada) {
		this.logoMarcaFormatada = logoMarcaFormatada;
	}

	public Boolean getFlagValorWeb() {
		return flagValorWeb;
	}

	public void setFlagValorWeb(Boolean flagValorWeb) {
		this.flagValorWeb = flagValorWeb;
	}

	public Long getCodigoIntegracao() {
		return TSUtil.tratarLong(codigoIntegracao);
	}

	public void setCodigoIntegracao(Long codigoIntegracao) {
		this.codigoIntegracao = codigoIntegracao;
	}

}
