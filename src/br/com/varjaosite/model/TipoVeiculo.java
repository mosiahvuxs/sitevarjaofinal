package br.com.varjaosite.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TipoVeiculo implements Serializable {

	private Long id;

	private String descricao;

	private Boolean flagAtivo = Boolean.TRUE;

	public TipoVeiculo() {
		// TODO Auto-generated constructor stub
	}
	
	public TipoVeiculo(Long id) {
		this.id = id;
	}

	public TipoVeiculo(Boolean value) {
		this.flagAtivo = value;
	}

	public Long getId() {
		return id;
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
