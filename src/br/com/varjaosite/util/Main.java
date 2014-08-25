package br.com.varjaosite.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

public class Main {

	public static void main(String[] args) {

		String valorDecodificado = decode("eyJjbGllbnRlIjo3OCwiY29kaWdvIjo2OTM0Njl9");

		System.out.println(valorDecodificado);

		System.out.println(valorDecodificado.substring(11, valorDecodificado.indexOf(",")));
		System.out.println(valorDecodificado.substring(valorDecodificado.indexOf(",") + 10, valorDecodificado.length() - 1));

	}

	public static String decode(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}
}
