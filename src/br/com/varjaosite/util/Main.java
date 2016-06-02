package br.com.varjaosite.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;

import br.com.topsys.util.TSCryptoUtil;

public class Main {

	public static void main(String[] args) throws InvalidKeyException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException {
		
		System.out.println(TSCryptoUtil.desCriptografar("1q6056431r75s4823o6849121s86454p69616s6r"));

		String valorDecodificado = decode("eyJjbGllbnRlIjo3OCwiY29kaWdvIjo2OTM0Njl9");

		System.out.println(valorDecodificado);

		System.out.println(valorDecodificado.substring(11, valorDecodificado.indexOf(",")));
		System.out.println(valorDecodificado.substring(valorDecodificado.indexOf(",") + 10, valorDecodificado.length() - 1));

	}

	public static String decode(String s) {
		return StringUtils.newStringUtf8(Base64.decodeBase64(s));
	}
}
