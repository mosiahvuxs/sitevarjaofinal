package br.com.varjaosite.faces;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.topsys.constant.TSConstant;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.varjaosite.dao.ClienteDAO;
import br.com.varjaosite.model.Autenticacao;
import br.com.varjaosite.model.Cliente;
import br.com.varjaosite.util.Constantes;

@ViewScoped
@ManagedBean(name = "loginFaces")
public class LoginFaces extends TSMainFaces {

	private Cliente cliente;

	private String clienteId;

	private String midiaId;

	public LoginFaces() {

		String idCliente = TSFacesUtil.getRequestParameter("cliente");

		String idMidia = TSFacesUtil.getRequestParameter("midia");
		
		this.initObjetos();

		if (!TSUtil.isEmpty(idCliente) && !TSUtil.isEmpty(idMidia)) {

			String clienteIdDescriptografado;

			String midiaIdDescriptografado;

			try {

				clienteIdDescriptografado = TSCryptoUtil.desCriptografar(idCliente);

				midiaIdDescriptografado = TSCryptoUtil.desCriptografar(idMidia);

				if (TSUtil.isNumeric(clienteIdDescriptografado) && TSUtil.isNumeric(midiaIdDescriptografado)) {

					this.clienteId = idCliente;

					this.midiaId = idMidia;

				} else {

					this.sair();
				}

			} catch (InvalidKeyException e) {

				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {

				e.printStackTrace();
			} catch (NoSuchPaddingException e) {

				e.printStackTrace();
			} catch (BadPaddingException e) {

				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {

				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {

				e.printStackTrace();
			}

		}

	}

	private void initObjetos() {

		this.setCliente(new Cliente(new Autenticacao()));
		this.setMidiaId(null);
		this.setClienteId(null);
	}

	private boolean validaCampos() {

		boolean validado = true;

		if (TSUtil.isEmpty(this.cliente.getAutenticacao().getLogin())) {

			super.addErrorMessage("Login: obrigatório.");

			validado = false;
		}

		if (TSUtil.isEmpty(this.cliente.getAutenticacao().getSenha())) {

			super.addErrorMessage("Senha: obrigatório.");

			validado = false;
		}

		return validado;
	}

	public String autenticar() {

		if (this.validaCampos()) {

			Cliente cliente = new Cliente(this.cliente.getAutenticacao());

			cliente.getAutenticacao().setSenha(TSCryptoUtil.gerarHash(cliente.getAutenticacao().getSenha(), TSConstant.CRIPTOGRAFIA_MD5));

			this.cliente = new ClienteDAO().obterPorLoginSenha(cliente);

			if (TSUtil.isEmpty(this.cliente)) {

				this.cliente = cliente;

				super.addErrorMessage("Dados inválidos.");

			} else {

				if (TSUtil.isEmpty(clienteId) && TSUtil.isEmpty(this.midiaId)) {

					super.addObjectInSession(Constantes.USUARIO_CONECTADO, this.cliente);

					return Constantes.CLIPPING;

				} else {

					this.redirecionarVisualizacaoClipping(this.clienteId, this.midiaId);
				}

			}
		}

		return null;
	}

	private void redirecionarVisualizacaoClipping(String clienteId, String midiaId) {

		try {

			TSFacesUtil.getResponse().sendRedirect(TSFacesUtil.getRequest().getContextPath() + "/visualizacao.xhtml?cliente=" + clienteId + "&midia=" + midiaId + "&verificado=true");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String sair() {

		this.clearFields();

		TSFacesUtil.removeObjectInSession(Constantes.USUARIO_CONECTADO);

		try {

			TSFacesUtil.getFacesContext().getExternalContext().redirect(TSFacesUtil.getRequest().getContextPath());

		} catch (IOException e) {

			e.printStackTrace();
		}

		TSFacesUtil.getRequest().getSession().invalidate();

		return null;

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getClienteId() {
		return clienteId;
	}

	public void setClienteId(String clienteId) {
		this.clienteId = clienteId;
	}

	public String getMidiaId() {
		return midiaId;
	}

	public void setMidiaId(String midiaId) {
		this.midiaId = midiaId;
	}

}
