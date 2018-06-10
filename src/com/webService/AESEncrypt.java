package com.webService;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypt
{
  static final String algorithmStr = "AES/ECB/PKCS5Padding";
  private static final Object TAG = "AES";
  private static KeyGenerator keyGen;
  private static Cipher cipher;
  static boolean isInited = false;
  private static final String keyBytes = "hanwin0000000000";

  private static void init()
  {
    try
    {
      keyGen = KeyGenerator.getInstance("AES");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }

    keyGen.init(128);
    try
    {
      cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    }

    isInited = true;
  }

  private static byte[] genKey() {
    if (!(isInited)) {
      init();
    }

    return keyGen.generateKey().getEncoded();
  }

  private static byte[] encrypt(byte[] content, byte[] keyBytes) {
    byte[] encryptedText = null;
    if (!(isInited)) {
      init();
    }

    Key key = new SecretKeySpec(keyBytes, "AES");
    try
    {
      cipher.init(1, key);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    }
    try
    {
      encryptedText = cipher.doFinal(content);
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    }
    return encryptedText; }

  private static byte[] encrypt(String content, String password) {
    byte[] keyStr;
    try {
      keyStr = getKey(password);
      SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      byte[] byteContent = content.getBytes("UTF-8");
      cipher.init(1, key);
      byte[] result = cipher.doFinal(byteContent);
      return result;
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
    return null; }

  private static byte[] decrypt(byte[] content, String password) {
    byte[] keyStr;
    try {
      keyStr = getKey(password);
      SecretKeySpec key = new SecretKeySpec(keyStr, "AES");
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(2, key);
      byte[] result = cipher.doFinal(content);
      return result;
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
    return null;
  }

  private static byte[] getKey(String password) {
    byte[] rByte = null;
    if (password != null)
      rByte = password.getBytes();
    else
      rByte = new byte[24];

    return rByte;
  }

  public static String parseByte2HexStr(byte[] buf)
  {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < buf.length; ++i) {
      String hex = Integer.toHexString(buf[i] & 0xFF);
      if (hex.length() == 1)
        hex = '0' + hex;

      sb.append(hex.toUpperCase());
    }
    return sb.toString();
  }

  public static byte[] parseHexStr2Byte(String hexStr)
  {
    if (hexStr.length() < 1)
      return null;
    byte[] result = new byte[hexStr.length() / 2];
    for (int i = 0; i < hexStr.length() / 2; ++i) {
      int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
      int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);

      result[i] = (byte)(high * 16 + low);
    }
    return result;
  }

  public static String encode(String content)
  {
    return parseByte2HexStr(encrypt(content, "hanwin0000000000"));
  }

  public static String decode(String content)
  {
    byte[] b;
    try
    {
      b = decrypt(parseHexStr2Byte(content), "hanwin0000000000");

      return new String(b, "Utf-8"); } catch (Exception ex) {
    }
    return "";
  }
}