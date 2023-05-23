package back;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cifrado {

	private static String password = "12345678";
	private static String DES = "DES";
	private static String AES = "AES";
	private static String TripleDES = "TripleDES";

	public static String encriptar(String texto) throws Exception {

		java.security.Key key = new SecretKeySpec(password.getBytes(), DES);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(texto.getBytes());
		return Base64.getEncoder().encodeToString(bytes);

	}

	public static String desencriptar(String texto) throws Exception {

		java.security.Key key = new SecretKeySpec(password.getBytes(), DES);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(texto));

		return new String(bytes, StandardCharsets.UTF_8);
	}

}