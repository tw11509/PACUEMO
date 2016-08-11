package com.init;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class GlobalService_mine {

	public static final String EMAIL_USERNAME = "tw50318@gmail.com"; // User
	public static final String EMAIL_PASSWORD = "pwlxioghjpbpmqfy"; 
	
	public static String getMD5EncodingString(String str) {

		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		byte[] messageDigest = md.digest(str.getBytes());
		BigInteger number = new BigInteger(1, messageDigest);
		String hashtext = number.toString(16);
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}

		return hashtext;
	}

	public static String encryptString(String content, String key) {

		KeyGenerator keygen = null;
		SecretKey secretKey = null;
		byte[] encodeFormat = null;
		Cipher c = null;
		byte[] cipherByte = null;
		String encryptedString = null;

		try {

			keygen = KeyGenerator.getInstance("AES");

			keygen.init(128, new SecureRandom(key.getBytes()));

			secretKey = keygen.generateKey();

			encodeFormat = secretKey.getEncoded();

			SecretKeySpec aeskey = new SecretKeySpec(encodeFormat, "AES");

			c = Cipher.getInstance("AES");

			byte[] byteContent = content.getBytes("UTF-8");

			c.init(Cipher.ENCRYPT_MODE, aeskey);

			cipherByte = c.doFinal(byteContent);

			encryptedString = DatatypeConverter.printBase64Binary(cipherByte);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();

		} catch (BadPaddingException e) {

			e.printStackTrace();

		}

		return encryptedString;
	}

	public static String decryptString(String stringToDecrypt, String key) {

		KeyGenerator keygen = null;
		SecretKey secretKey = null;
		byte[] encodeFormat = null;
		Cipher c = null;
		byte[] cipherByte = null;
		String decryptedString = null;

		try {

			keygen = KeyGenerator.getInstance("AES");

			keygen.init(128, new SecureRandom(key.getBytes()));

			secretKey = keygen.generateKey();

			encodeFormat = secretKey.getEncoded();

			SecretKeySpec aeskey = new SecretKeySpec(encodeFormat, "AES");

			c = Cipher.getInstance("AES");

			byte[] byteContent = DatatypeConverter.parseBase64Binary(stringToDecrypt);

			c.init(Cipher.DECRYPT_MODE, aeskey);

			cipherByte = c.doFinal(byteContent);

			decryptedString = new String(cipherByte);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();

		} catch (NoSuchPaddingException e) {

			e.printStackTrace();

		} catch (InvalidKeyException e) {

			e.printStackTrace();

		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();

		} catch (BadPaddingException e) {

			e.printStackTrace();

		}

		return decryptedString;
	}

	public static void main(String[] args) {

		String str = "ifgdfgdfgfddfg";
		System.out.println(getMD5EncodingString(str).length());
		System.out.println(getMD5EncodingString(str));

		System.out.println(encryptString(str, str));
		System.out.println(decryptString(encryptString(str, str), str));

	}

}
