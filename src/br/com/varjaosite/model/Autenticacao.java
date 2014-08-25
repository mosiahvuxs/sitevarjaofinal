package br.com.varjaosite.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Autenticacao implements Serializable{
	
	private String login;
	
	private String senha;
	
	private String confirmaSenha;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
	
	
	
}
