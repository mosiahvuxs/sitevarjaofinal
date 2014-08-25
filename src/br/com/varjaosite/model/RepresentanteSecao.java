package br.com.varjaosite.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RepresentanteSecao implements Serializable{
	
	private Long id;
	
	private Representante representante;
	
	private Secao secao;
	
	public RepresentanteSecao() {
	
	}
	
	public RepresentanteSecao(Secao secao) {
		
		this.secao = secao;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

}
