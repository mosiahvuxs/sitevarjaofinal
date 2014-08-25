package br.com.varjaosite.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SecaoRepresentante implements Serializable{
	
	private Long id;
	
	private Secao secao;
	
	private Representante representante;
	
	public SecaoRepresentante(){
		
	}
	
	public SecaoRepresentante(Representante representante){
		
		this.representante = representante;
		
	}

	public SecaoRepresentante(Secao secao) {
		this.secao = secao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Secao getSecao() {
		return secao;
	}

	public void setSecao(Secao secao) {
		this.secao = secao;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((representante == null) ? 0 : representante.hashCode());
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
		SecaoRepresentante other = (SecaoRepresentante) obj;
		if (representante == null) {
			if (other.representante != null)
				return false;
		} else if (!representante.equals(other.representante))
			return false;
		return true;
	}
	
	
}
