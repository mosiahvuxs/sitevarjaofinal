package br.com.varjaosite.model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PdfWeb implements Serializable {

	private String titulo;

	private Date dataPublicacao;

	private String fonte;

	private String programa;

	private String url;

	private String conteudo;

	public PdfWeb() {

	}

	public PdfWeb(Midia model) {

		this.titulo = model.getTitulo();
		this.dataPublicacao = model.getDataCadastro();
		this.fonte = model.getSecao().getVeiculo().getDescricao();
		this.programa = model.getSecao().getDescricao();
		this.url = model.getWeb().getUrl();
		this.conteudo = model.getWeb().getConteudo();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFonte() {
		return fonte;
	}

	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

}
