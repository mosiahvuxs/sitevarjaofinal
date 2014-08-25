package br.com.varjaosite.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;
import br.com.varjaosite.model.PdfWeb;

public class JasperUtil {

	public void gerarPdf(String jasper, PdfWeb model) throws JRException {

		List<PdfWeb> pdfWeb = new ArrayList<PdfWeb>();

		pdfWeb.add(model);

		JRBeanCollectionDataSource lista = new JRBeanCollectionDataSource(pdfWeb);

		jasper = TSFacesUtil.getServletContext().getRealPath("WEB-INF" + File.separator + "relatorios" + File.separator + jasper);

		JasperPrint impressao = JasperFillManager.fillReport(jasper, null, lista);

		if (!TSUtil.isEmpty(impressao) && !TSUtil.isEmpty(impressao.getPages()) && impressao.getPages().size() > 0) {

			ExternalContext econtext = TSFacesUtil.getFacesContext().getExternalContext();

			HttpServletResponse response = (HttpServletResponse) econtext.getResponse();

			response.setContentType(Constantes.MIME_TYPE_PDF);

			response.setHeader("Content-Disposition", "attachment; filename=\"" + model.getTitulo().toLowerCase()  + "-" + TSUtil.gerarId() + ".pdf\"");

			try {

				JasperExportManager.exportReportToPdfStream(impressao, response.getOutputStream());

			} catch (JRException e) {

				TSFacesUtil.addErrorMessage(e.getMessage());

				e.printStackTrace();

			} catch (IOException e) {

				TSFacesUtil.addErrorMessage(e.getMessage());

				e.printStackTrace();

			}

			TSFacesUtil.getFacesContext().responseComplete();

		} else {

			TSFacesUtil.addInfoMessage("Não foi possível gerar o PDF.");
		}

	}

}
