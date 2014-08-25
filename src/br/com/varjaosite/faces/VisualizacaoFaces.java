package br.com.varjaosite.faces;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.primefaces.model.StreamedContent;

import br.com.topsys.exception.TSApplicationException;
import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.varjaosite.dao.ClienteDAO;
import br.com.varjaosite.dao.MidiaDAO;
import br.com.varjaosite.dao.MidiaEnvioDAO;
import br.com.varjaosite.model.Cliente;
import br.com.varjaosite.model.Midia;
import br.com.varjaosite.model.MidiaEnvio;
import br.com.varjaosite.model.PdfWeb;
import br.com.varjaosite.model.Usuario;
import br.com.varjaosite.util.Constantes;
import br.com.varjaosite.util.Utilitarios;

@ViewScoped
@ManagedBean(name = "visualizacaoFaces")
public class VisualizacaoFaces extends TSMainFaces {

	private Midia midia;
	private StreamedContent file;
	
	public VisualizacaoFaces() throws UnsupportedEncodingException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException {

		this.carregar();

	}

	private String carregar() throws InvalidKeyException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException {

		String clienteId = TSFacesUtil.getRequestParameter("cliente");

		String midiaId = TSFacesUtil.getRequestParameter("midia");

		String codigoIntegracao = TSFacesUtil.getRequestParameter("codigoIntegracao");

		String verificado = TSFacesUtil.getRequestParameter("verificado");

		if (!TSUtil.isEmpty(clienteId) && !TSUtil.isEmpty(midiaId) && TSUtil.isNumeric(TSCryptoUtil.desCriptografar(clienteId)) && TSUtil.isNumeric(TSCryptoUtil.desCriptografar(midiaId))) {

			String clienteIdDescriptografado = TSCryptoUtil.desCriptografar(clienteId);

			String midiaIdDescriptografado = TSCryptoUtil.desCriptografar(midiaId);

			Cliente cliente = new Cliente();

			if (!TSUtil.isEmpty(codigoIntegracao) && codigoIntegracao.equals("true")) {

				cliente.setCodigoIntegracao(new Long(clienteIdDescriptografado));

				cliente = new ClienteDAO().obterPorCodigoIntegracao(cliente);

			} else {

				cliente.setId(new Long(clienteIdDescriptografado));

				cliente = new ClienteDAO().obter(cliente);
			}

			if (!TSUtil.isEmpty(cliente) && !TSUtil.isEmpty(cliente.getId())) {

				boolean verificarSenha = true;

				if (!TSUtil.isEmpty(verificado) && verificado.equals("true")) {

					verificarSenha = false;

				} else {

					verificarSenha = cliente.getFlagExigeSenha();
				}

				if (!verificarSenha) {

					if (!this.carregaMidia(Long.valueOf(midiaIdDescriptografado), cliente, codigoIntegracao)) {

						this.redirecionarIndex();

					} else {

						if (this.midia.getFlagAtivo()) {

							MidiaEnvio midiaEnvio = new MidiaEnvio();

							midiaEnvio.setUsuario(new Usuario());

							midiaEnvio.setCliente(cliente);

							midiaEnvio.setMidia(this.midia);

							try {

								new MidiaEnvioDAO().setarVisualizacao(midiaEnvio);

							} catch (TSApplicationException e) {

								e.printStackTrace();
							}

							/*if (this.midia.getTipoMidia().getId().equals(Constantes.VIDEO)) {

								try {

									TSFacesUtil.getFacesContext().getExternalContext().redirect(this.midia.getArquivoFormatado());

								} catch (IOException e) {

									e.printStackTrace();
								}

							}*/

						} else {
							
							this.redirecionarInativo();

						}

					}

				} else {

					this.redirecionarLogin(clienteId, midiaId);
				}

			} else {

				this.redirecionarIndex();
			}

		} else {

			this.redirecionarIndex();

		}

		return null;
	}

	private boolean carregaMidia(Long midiaId, Cliente cliente, String codigoIntegracao) {

		this.midia = new Midia();

		if (!TSUtil.isEmpty(codigoIntegracao) && codigoIntegracao.equals("true")) {

			this.midia.setCodigoIntegracao(midiaId);

			this.midia = new MidiaDAO().obterPorCodigoIntegracao(this.midia);

		} else {

			this.midia.setId(midiaId);

			this.midia = new MidiaDAO().obter(this.midia);
		}

		if (!TSUtil.isEmpty(this.midia) && !TSUtil.isEmpty(this.midia.getId())) {

			this.getMidia().setCliente(cliente);

			this.tratarArquivo();

			super.addObjectInSession(Constantes.USUARIO_CONECTADO, cliente);

			return true;
		}

		return false;
	}

	private void tratarArquivo() {

		if (Constantes.AUDIO.equals(this.midia.getTipoMidia().getId())) {

			this.midia.setArquivo(this.midia.getAudio().getArquivo());

		} else if (Constantes.VIDEO.equals(this.midia.getTipoMidia().getId())) {

			this.midia.setArquivo(this.midia.getVideo().getArquivo());

		} else if (Constantes.IMPRESSO.equals(this.midia.getTipoMidia().getId())) {

			this.midia.setArquivo(this.midia.getImpresso().getArquivo());

		} else if (Constantes.WEB.equals(this.midia.getTipoMidia().getId())) {

			this.midia.setArquivo(this.midia.getWeb().getArquivo());
		}
	}

	private void redirecionarIndex() {

		try {

			TSFacesUtil.getFacesContext().getExternalContext().redirect(TSFacesUtil.getRequest().getContextPath());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	private void redirecionarInativo() {

		try {

			TSFacesUtil.getFacesContext().getExternalContext().redirect(TSFacesUtil.getRequest().getContextPath() + "/inativo.xhtml");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	private void redirecionarLogin(String clienteId, String midiaId) {

		try {

			TSFacesUtil.getResponse().sendRedirect(TSFacesUtil.getRequest().getContextPath() + "/login.xhtml?cliente=" + clienteId + "&midia=" + midiaId);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String download() {

		try {

			File arquivo = new File(Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(this.midia.getData()) + this.midia.getArquivo());

			byte[] b = Utilitarios.getBytesDownload(arquivo, this.midia.getData(), this.midia.getArquivo());

			if (!TSUtil.isEmpty(b) && b.length > 0) {

				HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

				res.setHeader("Content-disposition", "attachment;filename=" + this.getMidia().getArquivo());

				res.getOutputStream().write(b);

				res.getCharacterEncoding();

				FacesContext.getCurrentInstance().responseComplete();

			} else {

				super.addErrorMessage("Arquivo não encontrado.");
			}

		} catch (IOException e) {

			TSFacesUtil.addErrorMessage("Erro ao gerar arquivo.");
		}

		return null;

	}

	public String gerarPdf() {

		try {

			new br.com.varjaosite.util.JasperUtil().gerarPdf("doc_web.jasper", new PdfWeb(this.midia));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return null;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public StreamedContent getFile() {
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

}
