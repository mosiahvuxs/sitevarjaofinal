package br.com.varjaosite.model;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Estado implements Serializable{

	private Long id;
	
	private String descricao;
	
	private List<Cidade> cidades;

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

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
	
	

}
