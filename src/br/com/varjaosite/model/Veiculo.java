package br.com.varjaosite.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Veiculo implements Serializable {

	private Long id;

	private String descricao;

	private Boolean flagAtivo = Boolean.TRUE;

	private TipoVeiculo tipoVeiculo;

	private Secao secao;
	
	private Cidade cidade;

	public Veiculo() {

	}

	public Veiculo(Boolean flagAtivo) {

		this.flagAtivo = flagAtivo;
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

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
