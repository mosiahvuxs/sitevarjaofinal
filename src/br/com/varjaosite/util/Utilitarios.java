package br.com.varjaosite.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

import br.com.topsys.util.TSCryptoUtil;
import br.com.topsys.util.TSDateUtil;
import br.com.topsys.util.TSUtil;
import br.com.topsys.web.util.TSFacesUtil;

public final class Utilitarios {

	private Utilitarios() {

	}

	public static String decodificar(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}

	public static String tratarString(String valor) {
		if (!TSUtil.isEmpty(valor)) {
			valor = valor.trim().replaceAll("  ", " ");
		}

		return valor;
	}

	public static Long tratarLong(Long valor) {
		if ((!TSUtil.isEmpty(valor)) && (valor.equals(Long.valueOf(0L)))) {
			valor = null;
		}

		return valor;
	}

	public static String removerAcentos(String palavra) {
		if (TSUtil.isEmpty(palavra)) {
			return null;
		}

		return Normalizer.normalize(new StringBuilder(palavra), Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static String gerarHash(String valor) {
		if (!TSUtil.isEmpty(valor)) {
			valor = TSCryptoUtil.gerarHash(valor, "md5");
		}

		return valor;
	}

	public static String gerarSenha() {
		Calendar rightNow = Calendar.getInstance();

		int diaAtual = rightNow.get(5);
		int mesAtual = rightNow.get(2) + 1;
		int anoAtual = rightNow.get(1);
		int horaAtual = rightNow.get(11);
		int minutoAtual = rightNow.get(12);
		int segAtual = rightNow.get(13);
		int miliAtual = rightNow.get(14);

		String senha = anoAtual + mesAtual + diaAtual + horaAtual + minutoAtual + segAtual + miliAtual + "";

		return senha;
	}

	@SuppressWarnings("resource")
	public static byte[] getBytes(File file) {

		int len = (int) file.length();

		byte[] sendBuf = new byte[len];

		FileInputStream inFile = null;

		try {

			inFile = new FileInputStream(file);

			inFile.read(sendBuf, 0, len);

		} catch (FileNotFoundException fnfex) {

			fnfex.printStackTrace();

		} catch (IOException ioex) {

			ioex.printStackTrace();
		}

		return sendBuf;
	}

	@SuppressWarnings("resource")
	public static byte[] getBytesDownload(File file, Date data, String arquivo) {

		int len = (int) file.length();

		byte[] sendBuf = new byte[len];

		FileInputStream inFile = null;

		try {

			inFile = new FileInputStream(Constantes.PASTA_ARQUIVOS_UPLOAD + TSUtil.getAnoMes(data) + arquivo);

			inFile.read(sendBuf, 0, len);

		} catch (FileNotFoundException fnfex) {

			fnfex.printStackTrace();

		} catch (IOException ioex) {

			ioex.printStackTrace();
		}

		return sendBuf;
	}

	public static boolean isValidDate(String data) {

		if (!TSUtil.isEmpty(data)) {

			SimpleDateFormat dateFormat = new SimpleDateFormat(TSDateUtil.DD_MM_YYYY);

			if (data.trim().length() != dateFormat.toPattern().length()) {

				return false;
			}

			dateFormat.setLenient(false);

			try {

				dateFormat.parse(data.trim());

			} catch (ParseException pe) {

				return false;
			}

		} else {

			return false;
		}

		return true;
	}

	public static String lerArquivo(String nomeArquivo) {

		try {

			return new String(getBytes(new File(TSFacesUtil.getServletContext().getRealPath("/") + nomeArquivo)), "UTF-8");

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		return null;

	}

	public static void downloadFile(String filename, String fileLocation, String contentType, FacesContext facesContext) {

		ExternalContext context = facesContext.getExternalContext();
		String path = fileLocation;
		String fullFileName = path + filename;
		File file = new File(fullFileName);

		HttpServletResponse response = (HttpServletResponse) context.getResponse();
		response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
		response.setContentLength((int) file.length());
		response.setContentType(contentType);

		try {

			FileInputStream in = new FileInputStream(Constantes.PASTA_ARQUIVOS_UPLOAD + file.getName());

			OutputStream out = response.getOutputStream();

			byte[] buf = new byte[(int) file.length()];
			int count;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			in.close();
			out.flush();
			out.close();
			facesContext.responseComplete();
		} catch (IOException ex) {
			TSFacesUtil.addErrorMessage("Erro ao baixar arquivo");
		}
	}

	public static String caminhoArquivo() {

		if (TSFacesUtil.getRequest().getServerName().contains("localhost")) {

			return Constantes.PASTA_ARQUIVOS_UPLOAD;

		} else {

			return Constantes.URL_SITE_PRODUCAO;
		}

	}
	
	public static Integer valorEmSegundos(String duracao) {

		if (duracao.length() == 8) {

			Integer horas = Integer.valueOf(duracao.substring(0, 2));

			Integer minutos = Integer.valueOf(duracao.substring(3, 5));

			Integer segundos = Integer.valueOf(duracao.substring(6, 8));

			Integer totalSegundos = (horas * 3600) + (minutos * 60) + segundos;

			return totalSegundos;

		}

		return 0;
	}
}