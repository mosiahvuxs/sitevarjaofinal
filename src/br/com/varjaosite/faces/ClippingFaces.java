package br.com.varjaosite.faces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSParseUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.faces.TSMainFaces;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.varjaosite.dao.MidiaDAO;
import br.com.varjaosite.model.Avaliacao;
import br.com.varjaosite.model.Cliente;
import br.com.varjaosite.model.Midia;
import br.com.varjaosite.model.MidiaEnvio;
import br.com.varjaosite.model.PdfWeb;
import br.com.varjaosite.model.TipoMidia;
import br.com.varjaosite.util.Constantes;
import br.com.varjaosite.util.Utilitarios;

@SessionScoped
@ManagedBean(name = "clippingFaces")
public class ClippingFaces extends TSMainFaces {

	private MidiaEnvio midiaEnvio;
	private List<Midia> midias;
	private Midia midia;
	private StreamedContent file;
	private String dataInicial;
	private String dataFinal;

	public ClippingFaces() {

		this.initObjetos();
	}

	private void initObjetos() {

		this.setMidiaEnvio(new MidiaEnvio());
		this.getMidiaEnvio().setMidia(new Midia());
		this.getMidiaEnvio().getMidia().setTipoMidia(new TipoMidia());
		this.getMidiaEnvio().getMidia().setAvaliacao(new Avaliacao());
		this.getMidiaEnvio().setCliente((Cliente) TSFacesUtil.getObjectInSession(Constantes.USUARIO_CONECTADO));
		this.setMidias(new ArrayList<Midia>());
		this.setMidia(new Midia());
		this.dataInicial = TSParseUtil.dateToString(TSDateUtil.addDayDate(new Date(), -7), TSDateUtil.DD_MM_YYYY);
		this.dataFinal = TSParseUtil.dateToString(new Date(), TSDateUtil.DD_MM_YYYY);

	}

	public String setarAvaliacao() {

		String avaliacao = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("avaliacao");

		if (!TSUtil.isEmpty(avaliacao)) {

			this.getMidiaEnvio().getMidia().setAvaliacao(new Avaliacao(Long.valueOf(avaliacao)));

		}

		return null;

	}

	@Override
	protected String find() {

		if (!TSUtil.isEmpty(this.getMidiaEnvio().getCliente()) && !TSUtil.isEmpty(this.getMidiaEnvio().getCliente().getId())) {

			if (this.validaDatas()) {

				this.midias = new MidiaDAO().pesquisar(this.getMidiaEnvio());

				if (!TSUtil.isEmpty(this.getMidias())) {

					for (Midia item : this.getMidias()) {

						if (Constantes.AUDIO.equals(item.getTipoMidia().getId())) {

							item.setArquivo(item.getAudio().getArquivo());

						} else if (Constantes.VIDEO.equals(item.getTipoMidia().getId())) {

							item.setArquivo(item.getVideo().getArquivo());

						} else if (Constantes.IMPRESSO.equals(item.getTipoMidia().getId())) {

							item.setArquivo(item.getImpresso().getArquivo());

						} else if (Constantes.WEB.equals(item.getTipoMidia().getId())) {

							item.setArquivo(item.getWeb().getArquivo());
						}
					}
				}

				TSFacesUtil.gerarResultadoLista(this.midias);

			}

		} else {

			super.addErrorMessage("Sessão inválida. Favor logar novamente.");
		}

		return null;
	}

	private boolean validaDatas() {

		boolean validado = true;

		if (!Utilitarios.isValidDate(this.dataInicial)) {

			validado = false;

			super.addErrorMessage("Data inicial inválida.");
		}

		if (!Utilitarios.isValidDate(this.dataFinal)) {

			validado = false;

			super.addErrorMessage("Data final inválida.");
		}

		if (validado) {

			if (TSDateUtil.diferencaDias(this.dataInicial, this.dataFinal, TSDateUtil.DD_MM_YYYY) > 7l) {

				validado = false;

				super.addErrorMessage("Para realizar a operação as datas devem abranger o período máximo de 07 dias.");

			} else {

				this.getMidiaEnvio().setDataEnvio(TSParseUtil.stringToDate(this.getDataInicial(), TSDateUtil.DD_MM_YYYY));

				this.getMidiaEnvio().setDataEnvioFinal(TSParseUtil.stringToDate(this.getDataFinal(), TSDateUtil.DD_MM_YYYY));

			}
		}

		return validado;
	}

	public String download() {

		try {

			File arquivo = new File(Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(this.midia.getData()) + this.midia.getArquivo());

			byte[] b = Utilitarios.getBytes(arquivo);

			if (!TSUtil.isEmpty(b)) {

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

	public String actionListener() {

		this.tratarArquivo();

		return Constantes.PAGINA_CLIPPING_IMPRESSO_AMPLIADO;
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

	public String limparMidia() {

		this.setMidia(new Midia());

		return null;
	}

	public MidiaEnvio getMidiaEnvio() {
		return midiaEnvio;
	}

	public void setMidiaEnvio(MidiaEnvio midiaEnvio) {
		this.midiaEnvio = midiaEnvio;
	}

	public List<Midia> getMidias() {
		return midias;
	}

	public void setMidias(List<Midia> midias) {
		this.midias = midias;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public StreamedContent getFile() {

		if (!TSUtil.isEmpty(this.midia) && !TSUtil.isEmpty(this.midia.getArquivo())) {

			try {

				FileInputStream fis = new FileInputStream(new File(Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(this.midia.getData()) + this.midia.getArquivo()));

				this.file = new DefaultStreamedContent(fis, "application/" + this.midia.getExtensaoArquivo(), this.midia.getArquivo());

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}
		return file;
	}

	public void setFile(StreamedContent file) {
		this.file = file;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

}
