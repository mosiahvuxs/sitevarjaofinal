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

import br.com.topsys.util.TSUtil;
import br.com.varjaosite.model.Cliente;

@WebFilter("*.xhtml")
public class VarjaoFilter implements Filter {

	public VarjaoFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		HttpServletResponse response = (HttpServletResponse) resp;

		String uri = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);

		int indiceFimUri = uri.lastIndexOf("?") == -1 ? uri.length() : uri.lastIndexOf("?");

		uri = uri.substring(uri.lastIndexOf("/") + 1, indiceFimUri);

		Cliente cliente = (Cliente) request.getSession().getAttribute(br.com.varjaosite.util.Constantes.USUARIO_CONECTADO);

		if (uri.equals("clipping.xhtml") && TSUtil.isEmpty(cliente)) {

			response.sendRedirect(request.getContextPath() + "/login.xhtml");

		}

		if (!response.isCommitted()) {

			chain.doFilter(request, response);

		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
