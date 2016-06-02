package br.com.varjaosite.util;

import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

public class EncriptaDecriptaDES {

	public static void main(String[] args) {

		String texto = "12345678912345";

		texto = Base64.encodeBase64String(texto.getBytes());

		System.out.println("String codificada " + texto.toLowerCase());

		//
		// Decodifica uma string anteriormente codificada usando o método
		// decodeBase64 e
		// passando o byte[] da string codificada
		//
		byte[] decoded = Base64.decodeBase64(texto.getBytes());

		//
		// Imprime o array decodificado
		//
		//System.out.println(Arrays.toString(decoded));

		//
		// Converte o byte[] decodificado de volta para a string original e
		// imprime
		// o resultado.
		//
		String decodedString = new String(decoded);
		System.out.println(texto.toLowerCase() + " = " + decodedString);
		
		//System.out.println(StringUtils.newStringUtf8(Base64.decodeBase64(decodedString)));

	}

}
