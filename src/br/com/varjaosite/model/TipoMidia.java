package br.com.varjaosite.model;

import java.io.Serializable;

import br.com.topsys.util.TSUtil;

@SuppressWarnings("serial")
public class TipoMidia implements Serializable {

	private Long id;

	private String descricao;
	
	private Boolean flagAtivo = Boolean.TRUE;

	public TipoMidia() {

	}

	public TipoMidia(Long id) {

		this.id = id;
	}

	public TipoMidia(Boolean value) {

		this.flagAtivo = value;
	}

	public Long getId() {
		return TSUtil.tratarLong(id);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getFlagAtivo() {
		return flagAtivo;
	}

	public void setFlagAtivo(Boolean flagAtivo) {
		this.flagAtivo = flagAtivo;
	}

}
