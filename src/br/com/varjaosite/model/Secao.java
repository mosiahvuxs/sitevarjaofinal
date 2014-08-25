package br.com.varjaosite.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings("serial")
public class Secao implements Serializable {

	private Long id;

	private String descricao;

	private Boolean flagAtivo = Boolean.TRUE;

	private List<SecaoRepresentante> secaoRepresentantes;
	
	private TipoVeiculo tipoVeiculo;

	private Veiculo veiculo;
	
	private BigDecimal valor = BigDecimal.ZERO;

	public Secao() {

	}

	public Secao(Boolean flagAtivo) {

		this.flagAtivo = flagAtivo;
	}

	public Secao(Veiculo veiculo, Boolean flagAtivo) {

		this.veiculo = veiculo;
		this.flagAtivo = flagAtivo;
	}

	public Secao(Long id) {
		this.id = id;
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

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public List<SecaoRepresentante> getSecaoRepresentantes() {
		return secaoRepresentantes;
	}

	public void setSecaoRepresentantes(List<SecaoRepresentante> secaoRepresentantes) {
		this.secaoRepresentantes = secaoRepresentantes;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoVeiculo getTipoVeiculo() {
		return tipoVeiculo;
	}

	public void setTipoVeiculo(TipoVeiculo tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

}
