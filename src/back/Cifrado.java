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

//	public static void main(String[] args) {
//		try {
//			String texto = "Encriptado con algoritmo DES";
//			byte[] textoEncriptado = encriptar("12345678", texto, "DES");
//			String textoEncriptadoBase64 = Base64.getEncoder().encodeToString(textoEncriptado);
//			System.out.println(textoEncriptadoBase64);
//			textoEncriptado = Base64.getDecoder().decode(textoEncriptadoBase64);
//			String textoOriginal = desencriptar("12345678", textoEncriptado, "DES");
//			System.out.println(textoOriginal);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			String texto = "Encriptado con algoritmo AES";
//			byte[] textoEncriptado = encriptar("123456781234567812345678", texto, "AES");
//			String textoOriginal = desencriptar("123456781234567812345678", textoEncriptado, "AES");
//			System.out.println(textoOriginal);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try {
//			String texto = "Encriptado con algoritmo TripleDES";
//			byte[] textoEncriptado = encriptar("123456781234567812345678", texto, "TripleDES");
//			String textoOriginal = desencriptar("123456781234567812345678", textoEncriptado, "TripleDES");
//			System.out.println(textoOriginal);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public static byte[] encriptar(String password, String texto, String algoritmo) throws Exception {
//		java.security.Key key = new SecretKeySpec(password.getBytes(), algoritmo);
//		Cipher cipher = Cipher.getInstance(algoritmo);
//		cipher.init(Cipher.ENCRYPT_MODE, key);
//		return cipher.doFinal(texto.getBytes());
//	}
//
//	public static String desencriptar(String password, byte[] encriptado, String algoritmo) throws Exception {
//		java.security.Key key = new SecretKeySpec(password.getBytes(), algoritmo);
//		Cipher cipher = Cipher.getInstance(algoritmo);
//		cipher.init(Cipher.DECRYPT_MODE, key);
//		byte[] bytes = cipher.doFinal(encriptado);
//		return new String(bytes);
//	}

//	
//	public static String encriptar(String texto) throws Exception {
//		java.security.Key key = new SecretKeySpec(password.getBytes(), DES);
//		Cipher cipher = Cipher.getInstance(DES);
//		
//		cipher.init(Cipher.ENCRYPT_MODE, key);
//		return new String(cipher.doFinal(texto.getBytes()));
//		
//	}
//
//	public static String desencriptar(String texto) throws Exception {
//		
//		byte [] encriptado = texto.getBytes();
//		java.security.Key key = new SecretKeySpec(password.getBytes(), DES);
//		Cipher cipher = Cipher.getInstance(DES);
//		cipher.init(Cipher.DECRYPT_MODE, key);
//		byte[] bytes = cipher.doFinal(encriptado);
//		return new String(bytes);
//	}

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