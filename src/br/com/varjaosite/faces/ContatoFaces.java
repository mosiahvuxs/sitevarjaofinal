package br.com.varjaosite.faces;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.util.TSEmailUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.varjaosite.util.Constantes;

@ViewScoped
@ManagedBean(name = "contatoFaces")
public class ContatoFaces extends TSMainFaces {

	private String nome;
	private String email;
	private String telefone;
	private String assunto;
	private String mensagem;

	public ContatoFaces() {

		this.clearFields();
	}

	@Override
	protected void clearFields() {

		this.setNome(null);
		this.setEmail(null);
		this.setTelefone(null);
		this.setAssunto(null);
		this.setMensagem(null);
	}

	private boolean validaCampos() {

		boolean validado = true;

		if (TSUtil.isEmpty(TSUtil.tratarString(this.getNome()))) {

			super.addErrorMessage("Nome: obrigatório.");

			validado = false;
		}

		if (TSUtil.isEmpty(TSUtil.tratarString(this.getAssunto()))) {

			super.addErrorMessage("Assunto: obrigatório.");

			validado = false;
		}

		if (TSUtil.isEmpty(TSUtil.tratarString(this.getEmail())) || !TSUtil.isEmailValid(this.getEmail())) {

			super.addErrorMessage("Email: inválido.");

			validado = false;

		}

		if (TSUtil.isEmpty(TSUtil.tratarString(this.getTelefone())) || this.getTelefone().length() < 8) {

			super.addErrorMessage("Telefone: inválido.");

			validado = false;
		}

		if (TSUtil.isEmpty(TSUtil.tratarString(this.getMensagem()))) {

			super.addErrorMessage("Mensagem: obrigatório.");

			validado = false;
		}

		return validado;
	}

	public String enviar() {

		if (this.validaCampos()) {

			TSEmailUtil.enviar(this.getEmail(), "Contato - Varjão", this.mensagem, Constantes.EMAIL_CONTATO, "text/plain", Constantes.SMTP);

			super.addInfoMessage("Mensagem enviada com sucesso.");

			this.clearFields();

		}

		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
