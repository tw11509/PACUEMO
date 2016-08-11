package com.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class GlobalService
{
	public static final int RECORDS_PER_PAGE = 3;
	public static final String HOST = "127.0.0.1";
	public static final String USERID = "sa";
	public static final String PASSWORD = "sa123456";
	public static final String SYSTEM_NAME = "PaCueMo";
	public static final String JNDI_DB_NAME = "java:comp/env/jdbc/DB_Jack";
	public static final int IMAGE_FILENAME_LENGTH = 20;
	public static final String DB_URL = "jdbc:sqlserver://" + GlobalService.HOST + ":1433;databaseName=MagicJack";
	public static final String KEY = "KittySnoopyMicky";  //decryptString()�Ϊ�Key :  16, 24, 32 �Ӧr����Key(�w�]�u���16�줸�A�Y�n��24�B32�A�n�hOracle�o�})
	public static final String DRIVER_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public String getSystemName()
	{   //  systemName 

		return SYSTEM_NAME;
	}

	public static String getMD5Endocing(String message)
	{
		final StringBuffer buffer = new StringBuffer();
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(message.getBytes());
			byte[] digest = md.digest();

			for (int i = 0 ; i < digest.length ; ++i)
			{
				final byte b = digest[i];
				final int value = (b & 0x7F) + (b < 0 ? 128 : 0);
				buffer.append(value < 16 ? "0" : "");
				buffer.append(Integer.toHexString(value));
			}
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
			return null;
		}
		return buffer.toString();
	}

	/**
	 * ��MD5�t��k�N�ɮ�file�ഫ��128�줸(16�Ӧ줸��)����ơC
	 * 
	 * @param message
	 *            : �n�[�K���r��
	 * @return : 128�줸��ƪ�16�i���ܪk�Һc�����r��
	 */
	public static String getMD5Endocing(File file)
			throws NoSuchAlgorithmException, IOException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		FileInputStream fis = new FileInputStream(file);
		byte[] ba = new byte[1024];
		int len = 0;
		while ((len = fis.read(ba)) != -1)
		{
			md.update(ba, 0, len);
		}
		byte[] digest = md.digest();
		final StringBuffer buffer = new StringBuffer();
		for (int i = 0 ; i < digest.length ; ++i)
		{
			byte b = digest[i];
			int value = (b & 0x7F) + (b < 0 ? 128 : 0);
			buffer.append(value < 16 ? "0" : "");
			buffer.append(Integer.toHexString(value));
		}
		return buffer.toString();
	}

	public static String encryptString(String message)
	{
		//  DES : Data Encryption Standard, �@�ع�٦��[�K�t��k�C
		//           �����p���F����1976�~�w���p����ƳB�z�з�(FIPS)�A����
		//           ���_�h�����O7�Ӧ줸�աB�[�K�϶�(Cipher Block)�T�w��8�Ӧ줸�աC
		//           DES�ثe�w�Q�����O�@�ؤ��w�����t��k�C
		//  AES : Advanced Encryption Standard, �@�ع�٦��[�K�t��k�C
		//           (�����p���F����2001�~�ǤJFIPS 140-2�з�)�A���غt��k
		//           ��Cipher Block�T�w��16�Ӧ줸�աC���_�h�����O16�Ӧ줸�աB
		//           24�Ӧ줸�թ�32�Ӧ줸��(�Y128�Ӧ줸�B192�Ӧ줸��256�Ӧ줸)�C
		//  ECB : Electronic CookBook, �@�ظ�ƪ��[�K�覡�A�o�إ[�K�覡�Ĩ�
		//           �C�Ӱ϶�(�p8�ө�16�Ӧ줸��)�W�ߥ[�K�A�Y�[�K�����϶��ɻP�䥦�϶�
		//           �L���C�W�����Y���u�I�]�����I�C
		//           �u�I���i�H�Ѧh�ӳB�z���ӥ���B�z���ӫܤj����ơC���I���p�G���
		//           �����e�����ƥX�{�������A�ӥB���Ƹ�ƪ����׭�n�P�[�K�϶������A
		//           �h�o�ǭ��ƥX�{�������g�L�[�K��|�X�{�ۦP�����G�C
		//  PKCS5Padding: �p�G�n�[�K����Ƥ��O8��(�pDES�[�K�t��k)��
		//           16��(�pAES�[�K�t��k)�줸�ժ���ƭ��A�h�����b���[�K��ƪ�
		//           ���ݥ[�J�Y�z�Ӧ줸�ըӴꦨ��ƭ��CPKCS5Padding�O�@��
		//           �ɨ������줸�ժ���k�C
		String encryptedString = "";
		try
		{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encryptedString = DatatypeConverter.printBase64Binary(cipher.doFinal(message.getBytes()));
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return encryptedString;
	}

	/**
	 * ����k�i��[�K���r��(Ciphertext)�ѱK�Akey�����[�K�ɪ����_
	 * �Ǧ^�Ȭ��ѱK�᪺�r��(Plaintext)
	 * 
	 */
	public static String decryptString(String key, String stringToDecrypt)
	{
		String decryptedString = "";
		try
		{
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			byte[] b = DatatypeConverter.parseBase64Binary(stringToDecrypt);
			decryptedString = new String(cipher.doFinal(b));
		}
		catch (InvalidKeyException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (NoSuchPaddingException e)
		{
			e.printStackTrace();
		}
		catch (IllegalBlockSizeException e)
		{
			e.printStackTrace();
		}
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		}
		return decryptedString;
	}

	public static int getlist(File f)
	{//���j�D���ؿ��ɭӼ�
		int size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		for (int i = 0 ; i < flist.length ; i++)
		{
			if (flist[i].isDirectory())
			{
				size = size + getlist(flist[i]);
				size--;
			}
		}
		return size;

	}

	// ����k�վ�fileName�����פp��ε���maxLength�C
	// �p�GfileName�����פp��ε���maxLength�A�����Ǧ^fileName
	// �_�h�O�d�̫�@�ӥy�I�P��᪺���ɦW�A�Y�u�D�ɦW�ϱofileName���`����
	// ����maxLength�C
	public static String adjustFileName(String fileName, int maxLength)
	{
		int length = fileName.length();
		if (length <= maxLength)
		{
			return fileName;
		}
		int n = fileName.lastIndexOf(".");
		int sub = fileName.length() - n - 1;
		fileName = fileName.substring(0, maxLength - 1 - sub) + "."
				+ fileName.substring(n + 1);
		return fileName;
	}
}
