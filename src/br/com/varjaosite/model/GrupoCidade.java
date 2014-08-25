package br.com.varjaosite.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GrupoCidade implements Serializable {

	private Long id;

	private Grupo grupo;

	private Cidade cidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

}
