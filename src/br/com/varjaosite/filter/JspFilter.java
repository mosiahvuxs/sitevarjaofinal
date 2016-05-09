package br.com.varjaosite.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.topsys.util.TSCryptoUtil;
import br.com.varjaosite.util.Utilitarios;

@WebFilter("*.jsp")
public class JspFilter implements Filter {

	public JspFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		int indiceFimUri = uri.lastIndexOf("?") == -1 ? uri.length() : uri.lastIndexOf("?");

		uri = uri.substring(uri.lastIndexOf("/") + 1, indiceFimUri);

		String noticia = req.getParameter("noticia");

		if (noticia != null && noticia.length() > 0 && uri.contains("clippingMultimidiaDownloadX.jsp") || uri.contains("clippingImpressoImprimirX.jsp")) {

			String noticiaDecodificada = Utilitarios.decodificar(noticia);

			if (noticiaDecodificada != null && noticiaDecodificada.length() > 0) {

				String cliente = noticiaDecodificada.substring(11, noticiaDecodificada.indexOf(","));

				String midia = noticiaDecodificada.substring(noticiaDecodificada.indexOf(",") + 10, noticiaDecodificada.length() - 1);

				if (cliente != null && midia != null) {

					response.sendRedirect(request.getContextPath() + "/visualizacao.xhtml?cliente=" + TSCryptoUtil.criptografar(cliente) + "&midia=" + TSCryptoUtil.criptografar(midia) + "&codigoIntegracao=true");
				}
			}

		} else {

			response.sendRedirect(request.getContextPath() + "/");
		}

		if (!response.isCommitted()) {

			chain.doFilter(request, response);

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
