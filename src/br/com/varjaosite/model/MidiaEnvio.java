package br.com.varjaosite.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public class MidiaEnvio implements Serializable {

	private Long id;

	private Midia midia;

	private Timestamp data;

	private Usuario usuario;

	private Cliente cliente;

	private Boolean flagVisualizado;

	private Timestamp dataVisualizacao;

	private Boolean flagEnviado;

	private Boolean flagLiberado;

	private Date dataEnvio;
	
	private Date dataEnvioFinal;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		MidiaEnvio other = (MidiaEnvio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Boolean getFlagVisualizado() {
		return flagVisualizado;
	}

	public void setFlagVisualizado(Boolean flagVisualizado) {
		this.flagVisualizado = flagVisualizado;
	}

	public Timestamp getDataVisualizacao() {
		return dataVisualizacao;
	}

	public void setDataVisualizacao(Timestamp dataVisualizacao) {
		this.dataVisualizacao = dataVisualizacao;
	}

	public Boolean getFlagEnviado() {
		return flagEnviado;
	}

	public void setFlagEnviado(Boolean flagEnviado) {
		this.flagEnviado = flagEnviado;
	}

	public Boolean getFlagLiberado() {
		return flagLiberado;
	}

	public void setFlagLiberado(Boolean flagLiberado) {
		this.flagLiberado = flagLiberado;
	}

	public Date getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(Date dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public Date getDataEnvioFinal() {
		return dataEnvioFinal;
	}

	public void setDataEnvioFinal(Date dataEnvioFinal) {
		this.dataEnvioFinal = dataEnvioFinal;
	}

}
