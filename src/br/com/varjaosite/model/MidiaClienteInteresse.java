package br.com.varjaosite.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MidiaClienteInteresse implements Serializable {

	private Long id;

	private Midia midia;

	private Cliente cliente;

	private Interesse interesse;
	
	private boolean selecionado;
	
	public MidiaClienteInteresse() {
	
	}
	
	public MidiaClienteInteresse(Midia midia) {
		
		this.midia = midia;
	}

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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Interesse getInteresse() {
		return interesse;
	}

	public void setInteresse(Interesse interesse) {
		this.interesse = interesse;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

}
